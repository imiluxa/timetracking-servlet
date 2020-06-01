package ua.imiluxa.trainingproject.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.imiluxa.trainingproject.model.dao.DaoConnection;

import java.sql.Connection;
import java.sql.SQLException;

@Deprecated
public class ConnectionDaoImpl implements DaoConnection {
    private static final Connection connection = ConnectionPoolHolder.getConnection();
    private static final Logger log = LogManager.getLogger();


    /*public ConnectionDaoImpl(Connection connection) {
        this.connection = connection;
    }*/

    @Override
    public void beginTransaction() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            log.error("autocommit not disabled", e);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            log.error("transaction not commited", e);
        }
    }

    @Override
    public void rollback() {
        try { ;
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.error("rollback not successful", e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error("connection not closed", e);
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
