package ua.imiluxa.trainingproject.model.dao.impl;

import ua.imiluxa.trainingproject.model.dao.*;

import java.sql.Connection;

public class JDBCDaoFactory extends DaoFactory {
    private static Connection connection = ConnectionPoolHolder.getConnection();

    @Override
    public UserDao createUserDao() {
        return new UserDaoImpl(connection);
    }

    @Override
    public RequestDao createRequestDao() {
        return new RequestDaoImpl(connection);
    }

    @Override
    public ActivityDao createActivityDao() {
        return new ActivityDaoImpl(connection);
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
