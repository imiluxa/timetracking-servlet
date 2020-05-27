package ua.imiluxa.trainingproject.model.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable{
    void create(T entity);
    void update(T entity);
    void delete(T entity);
    List<T> findAll();
    Optional<T> findById(long id);
}
