package org.somevand.fpt.teaching.uebung._11.server;

import org.sqlite.JDBC;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.*;

public class ConcurrentServer implements Runnable {

    private static final String logFormat = "[SERVER] %s%n";
    private final String dbURL;
    private final int port;
    private final ServerSocket socket;

    public ConcurrentServer(String dbURL, int port) throws IOException {
        this.dbURL = dbURL;
        this.port = port;
        this.socket = new ServerSocket(port);
    }

    @Override
    public void run() {
        try (socket) {
            while (true) {
                Socket sock = socket.accept();
                ConnectionHandler handler = new ConnectionHandler(
                        this,
                        sock,
                        DriverManager.getConnection(dbURL)
                );
                Thread handlerThread = new Thread(handler);
                handlerThread.start();
            }
        } catch (SocketException ignored) {
        } catch (IOException | SQLException e) {
            System.out.printf(logFormat, e);
        } finally {
            System.out.printf(logFormat, "terminating...");
        }
    }

    public void shutdown() throws IOException {
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        final String dbURL = JDBC.PREFIX + "fpt.uebung11";
        final int port = 6666;
        ConcurrentServer server = new ConcurrentServer(dbURL, port);
        Thread serverThread = new Thread(server);
        serverThread.start();
    }

}
