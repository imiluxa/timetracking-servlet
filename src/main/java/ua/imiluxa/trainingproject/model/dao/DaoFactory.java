package ua.imiluxa.trainingproject.model.dao;

import ua.imiluxa.trainingproject.model.dao.impl.JDBCDaoFactory;

import java.sql.Connection;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public static DaoFactory getInstance() {
        if(daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }

    public abstract UserDao createUserDao();
    public abstract RequestDao createRequestDao();
    public abstract ActivityDao createActivityDao();

    public abstract Connection getConnection();
}
