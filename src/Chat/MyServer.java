package Chat;

import Chat.auth.AuthenticationService;
import Chat.auth.DatabaseAuthService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

public class MyServer {
    private final int PORT = 8189;
    private List<ClientHandler> clients;
    private AuthenticationService authService;
    private Moderator moderator;

    public AuthenticationService getAuthService() {
        return authService;
    }

    public MyServer() {
        try (ServerSocket server = new ServerSocket(PORT)) {
            authService = new DatabaseAuthService();
            authService.start();
            moderator = new Moderator();
            clients = new ArrayList<>();
            while (true) {
                System.out.println("Server awaits clients");
                Socket socket = server.accept();
                System.out.println("Client connected");
                new ClientHandler(this, socket);
            }
        } catch (IOException ex) {
            System.out.println("Server error");
        } finally {
            if(authService!=null) {
                authService.stop();
                //При завершении работы сервера отключаем пул потоков для клиентских интерфейсов
                ClientHandler.shutdownExecutor();
            }
        }
    }


    public synchronized void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        broadcastClientsList();
    }

    public synchronized void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
        broadcastClientsList();
    }

    public synchronized void broadcast(String s, boolean addTime) {
        if (addTime) s += " @" + LocalTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
        s = moderator.moderate(s); // Пропускаем сообщение перед рассылкой через модератор
        for(ClientHandler client: clients) {
            client.sendMsg(s);
        }
    }

    public synchronized void broadcastClientsList() {
        StringBuilder sb = new StringBuilder("/clients ");
        for (ClientHandler o : clients) {
            sb.append(o.getName()).append(" ");
        }
        broadcast(sb.toString(), false);
    }

    public synchronized boolean isNickLogged(String nick) {
        for(ClientHandler client: clients) {
            if (client.getName().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public void sendDirect(String nick, String message) {
        for (ClientHandler client: clients) {
            if (client.getName().equals(nick)) {
                client.sendMsg("(direct) " + message + "@" +
                        LocalTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)));
                return;
            }
        }
        System.out.println("Unknown nick - message not sent");
    }
}
