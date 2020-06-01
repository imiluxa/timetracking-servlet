package ua.imiluxa.trainingproject.model.dao;

import java.sql.Connection;

@Deprecated
public interface DaoConnection extends AutoCloseable {

    void beginTransaction();

    void commit();

    void rollback();

    @Override
    void close();

    Connection getConnection();
}
