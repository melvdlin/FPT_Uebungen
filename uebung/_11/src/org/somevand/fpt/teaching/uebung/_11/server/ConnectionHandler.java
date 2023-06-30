package org.somevand.fpt.teaching.uebung._11.server;

import org.somevand.fpt.teaching.uebung._11.shared.Action;
import org.somevand.fpt.teaching.uebung._11.shared.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

class ConnectionHandler implements Runnable {
    private final String logFormat = "[CHANDLER] %s%n";
    private final ConcurrentServer server;
    private final Socket socket;
    private final Connection dbConnection;

    public ConnectionHandler(ConcurrentServer server, Socket socket, Connection dbConnection) {
        this.server = server;
        this.socket = socket;
        this.dbConnection = dbConnection;
    }

    @Override
    public void run() {
        try (socket;
             var in = new ObjectInputStream(socket.getInputStream());
             var out = new ObjectOutputStream(socket.getOutputStream());
             dbConnection) {

            System.out.printf(logFormat, "accepted connection...");

            Action action = (Action) in.readObject();
            Response response;

            switch (action) {
                case LOGIN -> {
                    String userName = (String) in.readObject();
                    String pwd = (String) in.readObject();
                    Optional<String> actualPwd;

                    DBHelper.getLock().readLock().lock();
                    try {
                        actualPwd = DBHelper.queryPassword(dbConnection, userName);
                    } finally {
                        DBHelper.getLock().readLock().unlock();
                    }

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

                    DBHelper.getLock().writeLock().lock();
                    try {
                        if (!DBHelper.queryContainsUserName(dbConnection, userName)) {
                            response = Response.SUCCESS;
                            DBHelper.insertEntry(dbConnection, userName, pwd, firstName, lastName);
                        } else {
                            response = Response.FAILURE;
                        }
                    } finally {
                        DBHelper.getLock().writeLock().unlock();
                    }
                }
                case SHUTDOWN -> {
                    server.shutdown();
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
}
