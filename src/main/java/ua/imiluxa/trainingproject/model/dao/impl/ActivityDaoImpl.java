package ua.imiluxa.trainingproject.model.dao.impl;

import ua.imiluxa.trainingproject.model.dao.ActivityDao;
import ua.imiluxa.trainingproject.model.dao.mapper.ActivityMapper;
import ua.imiluxa.trainingproject.model.dao.mapper.UserMapper;
import ua.imiluxa.trainingproject.model.entity.Activity;
import ua.imiluxa.trainingproject.model.entity.User;
import ua.imiluxa.trainingproject.util.exceptions.DAOException;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class ActivityDaoImpl implements ActivityDao {
    private final Connection connection;
    private final ResourceBundle rb = ResourceBundle.getBundle("db");

    ActivityDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Activity> findAllPageable(int page, int size) {
        return null;
    }

    @Override
    public List<Activity> findByUserId(long userid) {
        try (PreparedStatement ps = connection.prepareStatement(rb.getString("activity.findByUserId"))) {
            ps.setLong(1, userid);
            ResultSet rs = ps.executeQuery();

            Map<Long, Activity> activityMap = extractActivities(rs);
            rs.close();
            return new ArrayList<>(activityMap.values());
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void create(Activity activity) {
        try (PreparedStatement ps = connection.prepareStatement(rb.getString("activity.create"))) {
            ps.setLong(1, activity.getDuration());
            ps.setString(2, activity.getGoal());
            ps.setString(3, activity.getName());
            ps.setString(4, String.valueOf(activity.getStatusActivity()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void update(Activity activity) {
        try (PreparedStatement ps = connection.prepareStatement(rb.getString("activity.update"))) {
            ps.setLong(1, activity.getDuration());
            ps.setString(2, activity.getGoal());
            ps.setString(3, activity.getName());
            ps.setString(4, String.valueOf(activity.getStatusActivity()));
            ps.setLong(5, activity.getUser().getId());
            ps.setLong(6, activity.getIdactivity());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(Activity activity) {
        try (PreparedStatement ps = connection.prepareStatement(rb.getString("activity.delete"))) {
            ps.setLong(1, activity.getIdactivity());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }

    }

    @Override
    public List<Activity> findAll() {
        try (Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(rb.getString("activity.findAll"));

            Map<Long, Activity> activityMap = extractActivities(rs);
            rs.close();
            return new ArrayList<>(activityMap.values());
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Optional<Activity> findById(long id) {
        try (PreparedStatement ps = connection.prepareStatement(rb.getString("activity.findById"))) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            Map<Long, Activity> activityMap = extractActivities(rs);
            rs.close();
            return activityMap.values().stream().findAny();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private Map<Long, Activity> extractActivities(ResultSet rs) throws SQLException {
        Map<Long, Activity> activityMap = new LinkedHashMap<>();
        Map<Long, User> userMap = new HashMap<>();

        UserMapper userMapper = new UserMapper();
        ActivityMapper activityMapper = new ActivityMapper();

        while (rs.next()) {
            Activity activity = activityMapper.extractFromResultSet(rs);
            activityMapper.makeUnique(activityMap, activity);
        }
        for (Activity activity : activityMap.values()) {
            try (PreparedStatement ps = connection.prepareStatement(rb.getString("activity.and.user"))) {
                ps.setLong(1, activity.getIdactivity());
                ResultSet resultSetActivity = ps.executeQuery();

                while (resultSetActivity.next()) {
                    User user = userMapper.extractFromResultSet(resultSetActivity);
                    user = userMapper.makeUnique(userMap, user);
                    activity.setUser(user);
                }
                resultSetActivity.close();
            }
        }
        rs.close();
        return activityMap;
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
