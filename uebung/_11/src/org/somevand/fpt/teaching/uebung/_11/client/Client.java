package org.somevand.fpt.teaching.uebung._11.client;

import org.somevand.fpt.teaching.uebung._11.shared.Action;
import org.somevand.fpt.teaching.uebung._11.shared.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    private final int serverPort;
    public Client(int serverPort) { this.serverPort = serverPort; }

    public boolean login(String userName, String pwd) throws IOException {
        try (Socket socket = new Socket(InetAddress.getLocalHost(), serverPort);
             var out = new ObjectOutputStream(socket.getOutputStream());
             var in = new ObjectInputStream(socket.getInputStream())) {

            out.writeObject(Action.LOGIN);
            out.writeObject(userName);
            out.writeObject(pwd);

            Response response = (Response) in.readObject();

            return switch (response) {
                case SUCCESS -> true;
                case FAILURE -> false;
            };
        } catch (ClassNotFoundException | ClassCastException e) {
            return false;
        }
    }

    public boolean register(String userName, String pwd, String firstName, String lastName) throws IOException {

        try (Socket socket = new Socket(InetAddress.getLocalHost(), serverPort);
             var out = new ObjectOutputStream(socket.getOutputStream());
             var in = new ObjectInputStream(socket.getInputStream())) {

            out.writeObject(Action.REGISTER);
            out.writeObject(userName);
            out.writeObject(pwd);
            out.writeObject(firstName);
            out.writeObject(lastName);

            Response response = (Response) in.readObject();

            return switch (response) {
                case SUCCESS -> true;
                case FAILURE -> false;
            };
        } catch (ClassNotFoundException | ClassCastException e) {
            return false;
        }
    }


    public boolean shutdown() throws IOException {
        try (Socket socket = new Socket(InetAddress.getLocalHost(), serverPort);
             var out = new ObjectOutputStream(socket.getOutputStream());
             var in = new ObjectInputStream(socket.getInputStream())) {

            out.writeObject(Action.SHUTDOWN);

            Response response = (Response) in.readObject();

            return switch (response) {
                case SUCCESS -> true;
                case FAILURE -> false;
            };
        } catch (ClassNotFoundException | ClassCastException e) {
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        final int serverPort = 6666;
        Client client = new Client(serverPort);
        if (args[0].equalsIgnoreCase("shutdown")) {
            System.out.println(client.shutdown());
        } else {
            System.out.println(client.login("somevand", "noneofyourbusiness"));
            System.out.println(client.register("dick", "rocks", "dick", "the head"));
        }
    }
}
