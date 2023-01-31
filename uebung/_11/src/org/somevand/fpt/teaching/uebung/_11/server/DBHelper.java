package org.somevand.fpt.teaching.uebung._11.server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class DBHelper {

    static final String tableName = "Users";
    static final String userNameCol = "userName";
    static final String pwdCol = "pwd";
    static final String firstNameCol = "firstName";
    static final String lastNameCol = "lastName";
    static final String valueFormat = "%.255s";
    private static final ReadWriteLock lock = new ReentrantReadWriteLock();

    static ReadWriteLock getLock() {
        return lock;
    }

    static void createTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    String.format("CREATE TABLE %s (%s %s, %s %s, %s %s, %s %s);",
                            tableName,
                            userNameCol,    "VARCHAR(255) PRIMARY KEY",
                            pwdCol,         "VARCHAR(255) NOT NULL",
                            firstNameCol,   "VARCHAR(255)",
                            lastNameCol,    "VARCHAR(255)"
                    )
            );
        }
    }

    static void dropTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE " + tableName);
        }
    }

    static void insertEntry(
            Connection connection,
            String userName,
            String pwd,
            String firstName,
            String lastName
    ) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                String.format(
                        "INSERT INTO %s (%s, %s, %s, %s) VALUES (?,?,?,?)",
                        tableName, userNameCol, pwdCol, firstNameCol, lastNameCol
                )
        )) {
            statement.setString(1, valueFormat.formatted(userName));
            statement.setString(2, valueFormat.formatted(pwd));
            statement.setString(3, valueFormat.formatted(firstName));
            statement.setString(4, valueFormat.formatted(lastName));
            statement.executeUpdate();
        }
    }

    static Optional<String> queryPassword(Connection connection, String userName) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT " + pwdCol + " FROM " + tableName + " WHERE " + userNameCol + " = ?;"
        )) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(resultSet.getString(pwdCol));
            } else {
                return Optional.empty();
            }
        }
    }

    static boolean queryContainsUserName(Connection connection, String userName) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM " + tableName + " WHERE " + userNameCol + " = ?;"
        )) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }

    static List<String> queryAll(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
            List<String> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(String.format("%s, %s, %s, %s",
                        resultSet.getString(userNameCol),
                        resultSet.getString(pwdCol),
                        resultSet.getString(firstNameCol),
                        resultSet.getString(lastNameCol))
                );
            }
            return result;
        }
    }
}
