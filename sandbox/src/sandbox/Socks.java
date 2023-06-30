package sandbox;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Socks {
    public static void main(String[] args) throws InterruptedException {
        Thread serverThread = new Thread(Socks::server);
        Thread clientThread = new Thread(Socks::client);

        serverThread.setName("Server");
        clientThread.setName("Client");

        serverThread.start();
        clientThread.start();

        serverThread.join();
        clientThread.join();
        System.out.println("Exiting...");
    }

    private static void server() {
        int port = 12345;
        try (ServerSocket sock = new ServerSocket(port);
             Socket client = sock.accept();
             ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(client.getInputStream());
        ) {
            System.out.printf("Server received: %s%n", in.readObject());
            out.writeObject("server to client");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void client() {
        int port = 12345;
        try (Socket sock = new Socket(InetAddress.getLocalHost(), port);
             ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
        ) {
            out.writeObject("client to server");
            System.out.printf("Client received: %s%n", in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
