package ua.imiluxa.trainingproject.model.dao;

import ua.imiluxa.trainingproject.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> findByUsername(String userName);
    Optional<User> findById(long id);
    List<User> findAllPageable(int page, int size);

}
