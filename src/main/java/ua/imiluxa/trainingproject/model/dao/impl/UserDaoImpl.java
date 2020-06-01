package ua.imiluxa.trainingproject.model.dao.impl;

import ua.imiluxa.trainingproject.model.dao.UserDao;
import ua.imiluxa.trainingproject.model.dao.mapper.ActivityMapper;
import ua.imiluxa.trainingproject.model.dao.mapper.RequestMapper;
import ua.imiluxa.trainingproject.model.dao.mapper.UserMapper;
import ua.imiluxa.trainingproject.model.entity.Activity;
import ua.imiluxa.trainingproject.model.entity.Request;
import ua.imiluxa.trainingproject.model.entity.User;
import ua.imiluxa.trainingproject.util.exceptions.DAOException;

import java.sql.*;
import java.util.*;

public class UserDaoImpl implements UserDao {
    private final ResourceBundle rb = ResourceBundle.getBundle("db");
    private final Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> findByUsername(String userName) {
        try(PreparedStatement ps = connection.prepareStatement(rb.getString("user.findUserByUsername"))) {
            ps.setString(1, userName);
            ResultSet resultSet = ps.executeQuery();
            Map<Long, User> userMap = extractUser(resultSet);

            return userMap.values().stream().findAny();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Optional<User> findById(long id) {

        try (PreparedStatement ps = connection.prepareStatement(rb.getString("user.findUserById"))) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            Map<Long, User> userMap = extractUser(rs);

            return userMap.values().stream().findAny();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<User> findAllPageable(int page, int size) {
        return null;
    }

    @Override
    public void create(User entity) {
        try(PreparedStatement ps = connection.prepareStatement(
                rb.getString("user.createUser"), Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, entity.getUserName());
            ps.setString(2, entity.getEmail());
            ps.setString(3, entity.getPassword());
            ps.setString(4, entity.getFirstName());
            ps.setString(5, entity.getLastName());
            ps.setString(6, String.valueOf(entity.getRole()));

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {

        try(PreparedStatement ps = connection.prepareStatement(
                rb.getString("user.updateUser"), Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, entity.getUserName());
            ps.setString(2, entity.getEmail());
            ps.setString(3, entity.getPassword());
            ps.setString(4, entity.getFirstName());
            ps.setString(5, entity.getLastName());
            ps.setString(6, String.valueOf(entity.getRole()));
            ps.setLong(7, entity.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(User entity) {
        try(PreparedStatement ps = connection.prepareStatement(
                rb.getString("user.deleteUser"), Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, entity.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(rb.getString("user.findAllUser"));
            Map<Long,User> userMap = extractUser(resultSet);

            return new ArrayList<>(userMap.values());
        }
        catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private Map<Long, User> extractUser(ResultSet resultSet) throws SQLException {
        Map<Long,User> userMap = new LinkedHashMap<>();
        Map<Long, Activity> activityMap = new HashMap<>();
        Map<Long, Request> requestMap = new HashMap<>();

        UserMapper userMapper = new UserMapper();
        ActivityMapper activityMapper = new ActivityMapper();
        RequestMapper requestMapper = new RequestMapper();

        while (resultSet.next()) {
            User user = userMapper.extractFromResultSet(resultSet);
            userMapper.makeUnique(userMap, user);
        }

        for (User user : userMap.values()) {
            try (PreparedStatement ps = connection.prepareStatement(rb.getString("user.and.activities"))) {
                ps.setLong(1, user.getId());
                ResultSet resultSetActivities = ps.executeQuery();

                while(resultSetActivities.next()) {
                    Activity activity = activityMapper.extractFromResultSet(resultSetActivities);
                    activity = activityMapper.makeUnique(activityMap, activity);

                    if (!user.getActivitiesList().contains(activity)) {
                        user.getActivitiesList().add(activity);
                    }
                }
            }
            try(PreparedStatement ps = connection.prepareStatement(rb.getString("user.and.request"))) {
                ps.setLong(1, user.getId());
                ResultSet resultSetRequest = ps.executeQuery();

                while(resultSetRequest.next()) {
                    Request request = requestMapper.extractFromResultSet(resultSetRequest);
                    Activity activity = activityMapper.extractFromResultSet(resultSetRequest);

                    request = requestMapper.makeUnique(requestMap, request);
                    activity = activityMapper.makeUnique(activityMap, activity);

                    if (!user.getRequestList().contains(request)) {
                        request.setUser(user);
                        request.setActivity(activity);
                        user.getRequestList().add(request);
                    }
                }
            }
        }
        return userMap;
    }

    /*@Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }*/
}
