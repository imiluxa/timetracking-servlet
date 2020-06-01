package ua.imiluxa.trainingproject.model.dao.impl;

import ua.imiluxa.trainingproject.model.dao.RequestDao;
import ua.imiluxa.trainingproject.model.dao.mapper.ActivityMapper;
import ua.imiluxa.trainingproject.model.dao.mapper.RequestMapper;
import ua.imiluxa.trainingproject.model.dao.mapper.UserMapper;
import ua.imiluxa.trainingproject.model.entity.Activity;
import ua.imiluxa.trainingproject.model.entity.Request;
import ua.imiluxa.trainingproject.model.entity.User;
import ua.imiluxa.trainingproject.util.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RequestDaoImpl implements RequestDao {
    private final ResourceBundle rb = ResourceBundle.getBundle("db");
    private final Connection connection;

    RequestDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Request> findAllPageable(int page, int size) {
        return null;
    }

    @Override
    public Request findByActivityAndUserIds(long activityId, long userid) {
        return null;
    }

    @Override
    public void create(Request request) {
        try(PreparedStatement ps = connection.prepareStatement(rb.getString("request.create"))) {
            ps.setString(1, request.getAction().toString());
            ps.setString(2, request.getStatus().toString());
            ps.setLong(3, request.getUser().getId());
            ps.setLong(4, request.getActivity().getIdactivity());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void update(Request request) {
        try (PreparedStatement ps = connection.prepareStatement(rb.getString("request.update"))) {
            ps.setString(1, request.getAction().toString());
            ps.setString(2, request.getStatus().toString());
            ps.setLong(3, request.getUser().getId());
            ps.setLong(4, request.getActivity().getIdactivity());
            ps.setLong(5, request.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(Request request) {
        try (PreparedStatement ps = connection.prepareStatement(rb.getString("request.delete"))) {
            ps.setLong(1, request.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Request> findAll() {
        try (PreparedStatement ps = connection.prepareStatement(rb.getString("request.findAll"))) {
            ResultSet resultSet = ps.executeQuery();

            Map<Long, Request> requestMap = extractRequest(resultSet);
            return new ArrayList<>(requestMap.values());

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private Map<Long, Request> extractRequest(ResultSet resultSet) throws SQLException {
        Map<Long,User> userMap = new HashMap<>();
        Map<Long, Activity> activityMap = new HashMap<>();
        Map<Long, Request> requestMap = new LinkedHashMap<>();

        UserMapper userMapper = new UserMapper();
        ActivityMapper activityMapper = new ActivityMapper();
        RequestMapper requestMapper = new RequestMapper();

        while(resultSet.next()) {
            Request request = requestMapper.extractFromResultSet(resultSet);
            requestMapper.makeUnique(requestMap, request);
        }

        for(Request request : requestMap.values()) {
            try (PreparedStatement pspreparedStatement = connection.prepareStatement(rb.getString("request.and.activity.and.user"))) {
                pspreparedStatement.setLong(1, request.getId());
                ResultSet resultSetRequest = pspreparedStatement.executeQuery();

                while(resultSetRequest.next()) {
                    User user = userMapper.extractFromResultSet(resultSetRequest);
                    Activity activity = activityMapper.extractFromResultSet(resultSetRequest);
                    user = userMapper.makeUnique(userMap, user);
                    activity = activityMapper.makeUnique(activityMap, activity);
                    request.setUser(user);
                    request.setActivity(activity);
                }

            }
        }
        return requestMap;
    }

    @Override
    public Optional<Request> findById(long id) {
        try(PreparedStatement ps = connection.prepareStatement(rb.getString("request.findById"))) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            Map<Long, Request> requestMap = extractRequest(rs);

            return requestMap.values().stream().findAny();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
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
