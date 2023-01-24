package org.somevand.fpt.teaching.uebung._11.server;

import org.somevand.fpt.teaching.uebung._11.shared.Action;
import org.somevand.fpt.teaching.uebung._11.shared.Response;
import org.sqlite.JDBC;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class BasicServer implements Runnable {

    private static final String logFormat = "[SERVER] %s%n";
    private final String dbURL;
    private final int port;

    private boolean shutdown = false;

    public BasicServer(String dbURL, int port) {
        this.dbURL = dbURL;
        this.port = port;
    }

    @Override
    public void run() {
        try (ServerSocket socket = new ServerSocket(port);
             Connection dbConnection = DriverManager.getConnection(dbURL)) {
            while (!shutdown) {
                Socket sock = socket.accept();
                service(sock, dbConnection);
            }
        } catch (IOException | SQLException e) {
            System.out.printf(logFormat, e);
        } finally {
            System.out.printf(logFormat, "terminating...");
        }
    }

    private void service(Socket socket, Connection dbConnection) {
        try (socket;
             var in = new ObjectInputStream(socket.getInputStream());
             var out = new ObjectOutputStream(socket.getOutputStream());
             dbConnection) {

            Action action = (Action) in.readObject();
            Response response;

            switch (action) {
                case LOGIN -> {
                    String userName = (String) in.readObject();
                    String pwd = (String) in.readObject();
                    Optional<String> actualPwd = DBHelper.queryPassword(dbConnection, userName);

                    if (actualPwd.isPresent() && pwd.equals(actualPwd.get())) {
                        response = Response.SUCCESS;
                    } else {
                        response = Response.FAILURE;
                    }
                }
                case REGISTER -> {
                    String userName = (String) in.readObject();
                    String pwd = (String) in.readObject();
                    String firstName = (String) in.readObject();
                    String lastName = (String) in.readObject();

                    if (!DBHelper.queryContainsUserName(dbConnection, userName)) {
                        response = Response.SUCCESS;
                        DBHelper.insertEntry(dbConnection, userName, pwd, firstName, lastName);
                    } else {
                        response = Response.FAILURE;
                    }
                }
                case SHUTDOWN -> {
                    shutdown = true;
                    response = Response.SUCCESS;
                }
                default -> response = Response.FAILURE;
            }
            out.writeObject(response);
        } catch (IOException | SQLException e) {
            System.out.printf(logFormat, e);
        } catch (ClassNotFoundException | ClassCastException e) {
            System.out.printf(logFormat, "Invalid request!");
        } finally {
            System.out.printf(logFormat, "closing connection...");
        }
    }

    public static void main(String[] args) {
        final String dbURL = JDBC.PREFIX + "fpt.uebung11";
        final int port = 6666;
        BasicServer server = new BasicServer(dbURL, port);
        Thread serverThread = new Thread(server);
        serverThread.start();
    }

}
