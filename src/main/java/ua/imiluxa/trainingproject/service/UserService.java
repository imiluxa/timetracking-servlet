package ua.imiluxa.trainingproject.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.imiluxa.trainingproject.dto.UserDTO;
import ua.imiluxa.trainingproject.model.dao.DaoFactory;
import ua.imiluxa.trainingproject.model.dao.UserDao;
import ua.imiluxa.trainingproject.model.entity.Role;
import ua.imiluxa.trainingproject.model.entity.User;
import ua.imiluxa.trainingproject.util.exceptions.DAOException;

import java.util.List;
import java.util.Optional;

public class UserService {
    private static final Logger log = LogManager.getLogger();
    private static final DaoFactory daoFactory = DaoFactory.getInstance();

    public Optional<User> findByUsername(String username) {
        try {
            UserDao userDao = daoFactory.createUserDao();
            Optional<User> user = userDao.findByUsername(username);
            daoFactory.getConnection().commit();
            return user;
        } catch (Exception e) {
            log.warn("cant find user, or connection: " + username);
            return Optional.empty();
        }
    }

    public User findById(long userId) {
        try {
            UserDao userDao = daoFactory.createUserDao();
            Optional<User> user = userDao.findById(userId);
            daoFactory.getConnection().commit();
            return user.get();
        } catch (Exception e) {
            log.warn("cant find user, or connection: " + userId);
            throw new RuntimeException(e);
        }
    }

    public void createNewUser(UserDTO user) throws Exception {
        User newUser = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .userName(user.getUsername())
                .role(Role.USER)
                .build();

        try {
            UserDao userDao = daoFactory.createUserDao();
            userDao.create(newUser);
            daoFactory.getConnection().commit();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    public List<User> getAllUsers() {
        try  {
            UserDao userDao = daoFactory.createUserDao();
            List<User> user = userDao.findAll();
            daoFactory.getConnection().commit();
            return user;
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
}
