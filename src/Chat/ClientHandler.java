package Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientHandler {
    private final MyServer myServer;
    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;
    private String name;
    //Пул потоков для обмена данными с сервером. Статический, т.к. для каждого клиент хэндлера поток свой,
    //а пул один на всех. Количество клиентов заранее мы не знаем, поэтому CachedThreadPool.
    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public String getName() {
        return name;
    }

    /**
     * Остановка пула потоков обмена данными с сервером
     */
    public static void shutdownExecutor() { executorService.shutdown(); }

    public ClientHandler(MyServer myServer, Socket socket) {
        this.myServer = myServer;
        this.socket = socket;
        this.name = "";

        try {
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            //Вместо вызова потока теперь передаём задачу на выполнение в пул потоков
            executorService.execute(()-> {
                try {
                    authenticate();
                    readMessages();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    closeConnection();
                }
            });
        } catch (IOException ex) {
            throw new RuntimeException("Client creation error");
        }
    }

    private void alterNick(String newNick) {
        // Если такого ника в БД пользователей нет, то производим смену ника
        if (!myServer.getAuthService().isNickExist(newNick)) {
            if (myServer.getAuthService().changeNick(name, newNick)) {
                // Рассылаем всем в чате сообщение о том, что у пользователя теперь новый ник
                myServer.broadcast(name + " now is " + newNick, true);
                sendMsg("Your new nick is " + newNick);
                // И непосредственно самому объекту пользователь меняем ник
                name = newNick;
            } else {
                sendMsg("Error of change nick. Try again");
            }
            // Если ник уже занят, сообщаем пользователю об этом
        } else {
            sendMsg("Nick " + newNick + " is already in use. Enter another nick.");
        }
    }

    /**
     * Обработка служебных сообщений - тех, которые начинаются с символа "/"
     * @param message Входящее сообщение
     */
    private void serviceMessageProcessing(String message) {
        String[] parts = message.split("\\s");
        String command = parts[0].substring(1);
        switch (command){
            case "w": { //Отправка приватного сообщения
                String realMessage = message.substring(message.indexOf(" ", message.indexOf(" ") + 1));
                myServer.sendDirect(parts[1],name + ": " + realMessage);
                break;
            } case "alternick": { //Смена ника
                alterNick(parts[1]);
                break;
            } case "end": { //Выход из чата
                sendMsg("/end");
                closeConnection();
            } default: { sendMsg("Incorrect command. Enter correct command or write message not beginning from \"/\""); }
        }
    }

    private void closeConnection() {
        myServer.unsubscribe(this);
        myServer.broadcast("User " + name + " left", true);
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void readMessages() throws IOException {
        while (true) {
            if (in.available()>0) {
                String message = in.readUTF();
                System.out.println("From " + name + ":" + message);
                if (message.startsWith("/")) { serviceMessageProcessing(message); } else {
                    myServer.broadcast(name + ": " + message, true);
                }
            }
        }
    }

    private void authenticate() throws IOException {
        while(true) {
            if (in.available()>0){
                String str = in.readUTF();
                if (str.startsWith("/auth")) {
                    String[] parts = str.split("\\s");
                    String nick = myServer.getAuthService().getNickByLoginAndPwd(parts[1], parts[2]);
                    if (nick != null) {
                        if (!myServer.isNickLogged(nick)) {
                            System.out.println(nick + " logged into chat");
                            name = nick;
                            sendMsg("/authOk " + nick);
                            myServer.broadcast(nick + " is in chat", true);
                            myServer.subscribe(this);
                            return;
                        } else {
                            System.out.println("User " + nick + " tried to re-enter");
                            sendMsg("User already logged in");
                        }
                    } else {
                        System.out.println("Wrong login/password");
                        sendMsg("Incorrect login attempted");
                    }
                }
            }

        }
    }

    public void sendMsg(String s) {
        try {
            out.writeUTF(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
