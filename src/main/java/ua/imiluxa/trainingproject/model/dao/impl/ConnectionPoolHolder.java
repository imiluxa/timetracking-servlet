package ua.imiluxa.trainingproject.model.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionPoolHolder {
    public static final ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
    private static volatile Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (connection == null) {
                    try {
                        Class.forName(resourceBundle.getString("db.driver"));
                        connection = DriverManager.getConnection(
                                resourceBundle.getString("db.connectUrl"),
                                resourceBundle.getString("db.user"),
                                resourceBundle.getString("db.password"));
                        connection.setAutoCommit(false);
                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }
}
