package lesson3Homework;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.System.*;

public class Client {
    private static boolean authorized = false;

    public static void main(String[] args) throws IOException {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 8189);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            // Считываем из консоли логин и пароль, отправляем запрос авторизации на сервер
            BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter your login:");
            String login = consoleIn.readLine();
            System.out.println("Enter your password:");
            String password = consoleIn.readLine();
            out.writeUTF("/auth " + login + " " + password);

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            if(in.available()>0) {
                                String strFromServer = in.readUTF();
                                if (strFromServer.startsWith("/authOk")) {
                                    authorized = true;
                                    System.out.println("Authorized on server");
                                    Client.runOutputThread(out);
                                    break;
                                }
                                System.out.println(strFromServer + "\n");
                            }
                        }
                        while (true) {
                            if (in.available()>0) {
                                String strFromServer = in.readUTF();
                                if (strFromServer.equalsIgnoreCase("/end")) {
                                    break;
                                }
                                System.out.println(strFromServer);
                                System.out.println("\n");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
            t.join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }

    private static Thread runOutputThread(DataOutputStream out) {
        Thread thread = new Thread(()-> {
            while (!Thread.currentThread().isInterrupted()) {
                Scanner scanner = new Scanner(in);
                while (true) {
                    String message = scanner.nextLine();
                    try {
                        out.writeUTF(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (message.equals("/end")) {
                        break;
                    }
                }
            }
        });
        thread.start();
        return thread;
    }
}
