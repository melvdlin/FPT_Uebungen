package sandbox;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocksNoTwo {
    public static void main(String[] args) throws InterruptedException {
        Thread serverThread = new Thread(SocksNoTwo::server);
        Thread clientThread = new Thread(SocksNoTwo::client);

        serverThread.start();
        clientThread.start();

        serverThread.join();
        clientThread.join();

        System.out.println("Exiting...");
    }

    private static void client() {
        int port = 1234;
        try (Socket sock = new Socket(InetAddress.getLocalHost(), port);
             OutputStream os = sock.getOutputStream();
             InputStream is = sock.getInputStream();
             ObjectOutputStream out = new ObjectOutputStream(os);
             ObjectInputStream in = new ObjectInputStream(is);
        ) {

            String s = "Hello";
            out.writeObject(s);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void server() {
        int port = 1234;
        try (ServerSocket serversock = new ServerSocket(port);
             Socket sock = serversock.accept();
             OutputStream os = sock.getOutputStream();
             InputStream is = sock.getInputStream();
             ObjectOutputStream out = new ObjectOutputStream(os);
             ObjectInputStream in = new ObjectInputStream(is);
        ) {

            String s = (String) in.readObject();
            System.out.println(s);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
