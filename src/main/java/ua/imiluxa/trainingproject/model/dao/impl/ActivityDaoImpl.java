package ua.imiluxa.trainingproject.model.dao.impl;

import ua.imiluxa.trainingproject.model.dao.ActivityDao;
import ua.imiluxa.trainingproject.model.dao.mapper.ActivityMapper;
import ua.imiluxa.trainingproject.model.dao.mapper.RequestMapper;
import ua.imiluxa.trainingproject.model.dao.mapper.UserMapper;
import ua.imiluxa.trainingproject.model.entity.Activity;
import ua.imiluxa.trainingproject.model.entity.Request;
import ua.imiluxa.trainingproject.model.entity.StatusActivity;
import ua.imiluxa.trainingproject.model.entity.User;
import ua.imiluxa.trainingproject.util.exceptions.DAOException;

import java.sql.*;
import java.util.*;

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

            Map<Long, Activity> activityMap = extractActivities(rs, rb.getString("activity.and.user"));

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
            ps.setLong(5, activity.getUser().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void update(Activity activity) {
        try (PreparedStatement ps = connection.prepareStatement(rb.getString("activity.update"))) {

            if (activity.getStatusActivity() == StatusActivity.COMPLETED) {
                saveHistory(activity);
                delete(activity);
            } else {
                ps.setLong(1, activity.getDuration());
                ps.setString(2, activity.getGoal());
                ps.setString(3, activity.getName());
                ps.setString(4, String.valueOf(activity.getStatusActivity()));
                ps.setLong(5, activity.getUser().getId());
                ps.setLong(6, activity.getIdactivity());
                ps.executeUpdate();
            }
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

            Map<Long, Activity> activityMap = extractActivities(rs, rb.getString("activity.and.user"));

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

            Map<Long, Activity> activityMap = extractActivities(rs, rb.getString("activity.and.user"));

            return activityMap.values().stream().findAny();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void saveHistory(Activity activity) {
        try (PreparedStatement ps = connection.prepareStatement(rb.getString("activity.saveHistory"))) {
            ps.setLong(1, activity.getIdactivity());
            ps.setLong(2, activity.getDuration());
            ps.setString(3, activity.getGoal());
            ps.setString(4, activity.getName());
            ps.setString(5, activity.getStatusActivity().getValue());
            ps.setLong(6, activity.getUser().getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public List<Activity> findHistoryByUserId(long userid) {
        try (PreparedStatement ps = connection.prepareStatement(rb.getString("activity.findHistoryByUserId"))) {
            ps.setLong(1, userid);
            ResultSet rs = ps.executeQuery();

            Map<Long, Activity> activityMap = extractActivities(rs, rb.getString("activity_history.and.user"));

            return new ArrayList<>(activityMap.values());
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public List<Activity> findAllHistory() {
        try (Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(rb.getString("activity.findAllHistory"));

            Map<Long, Activity> activityMap = extractActivities(rs, rb.getString("activity_history.and.user"));

            return new ArrayList<>(activityMap.values());
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private Map<Long, Activity> extractActivities(ResultSet rs, String statement) throws SQLException {
        Map<Long, Activity> activityMap = new LinkedHashMap<>();
        Map<Long, User> userMap = new HashMap<>();
        Map<Long, Request> reqMap = new HashMap<>();

        UserMapper userMapper = new UserMapper();
        ActivityMapper activityMapper = new ActivityMapper();
        RequestMapper requestMapper = new RequestMapper();

        while (rs.next()) {
            Activity activity = activityMapper.extractFromResultSet(rs);
            activityMapper.makeUnique(activityMap, activity);
        }
        for (Activity activity : activityMap.values()) {
            try (PreparedStatement ps = connection.prepareStatement(statement/*rb.getString("activity.and.user")*/)) {
                ps.setLong(1, activity.getIdactivity());
                ResultSet resultSetActivity = ps.executeQuery();
                while (resultSetActivity.next()) {
                    User user = userMapper.extractFromResultSet(resultSetActivity);
                    user = userMapper.makeUnique(userMap, user);
                    activity.setUser(user);
                }
            }
            try (PreparedStatement ps = connection.prepareStatement(rb.getString("request.and.user"))) {
                ps.setLong(1, activity.getIdactivity());
                ResultSet resultSetRequest = ps.executeQuery();
                Map<Long, Request> requestMap = new LinkedHashMap<>();
                while (resultSetRequest.next()) {
                    Request request = requestMapper.extractFromResultSet(resultSetRequest);
                    request = requestMapper.makeUnique(reqMap, request);
                    requestMap.put(resultSetRequest.getLong("request.user_id"), request);
                }
                activity.setRequestMap(requestMap);

            }
        }

        return activityMap;
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
