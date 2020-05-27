package ua.imiluxa.trainingproject.service;

import ua.imiluxa.trainingproject.model.dao.DaoFactory;
import ua.imiluxa.trainingproject.model.dao.UserDao;
import ua.imiluxa.trainingproject.model.entity.Role;
import ua.imiluxa.trainingproject.model.entity.User;
import ua.imiluxa.trainingproject.util.exceptions.DAOException;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public Optional<User> findByUsername(String username) throws Exception {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findByUsername(username);
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    public void createNewUser(User user) throws Exception {
        User newUser = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .userName(user.getUserName())
                .email(user.getEmail())
                .role(Role.USER)
                .build();

        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.create(newUser);
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    public List<User> getAllUsers() {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findAll();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
}
