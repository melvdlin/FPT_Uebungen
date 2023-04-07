package org.somevand.fpt.teaching.uebung._01.messenger;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NetworkHelper {

    private static int port = 8080;

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        NetworkHelper.port = port;
    }

    public static void sendMessage(TextMessage message) throws IOException {
        try (Socket socket = new Socket(message.getRecipient().getAddress(), port);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())
        ) {
            out.writeObject(message.getSender().getAddress());
            out.writeLong(message.getTimestamp());
            out.writeObject(message.getBody());
        }
    }
}
