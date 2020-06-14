package ua.imiluxa.trainingproject.model.dao.mapper;

import ua.imiluxa.trainingproject.model.entity.Request;
import ua.imiluxa.trainingproject.model.entity.RequestActions;
import ua.imiluxa.trainingproject.model.entity.RequestStatus;
import ua.imiluxa.trainingproject.service.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class RequestMapper implements ObjectMapper<Request> {
    public static UserService userService = new UserService();

    @Override
    public Request makeUnique(Map<Long, Request> map, Request object) {
        map.putIfAbsent(object.getId(), object);
        return map.get(object.getId());
    }

    @Override
    public Request extractFromResultSet(ResultSet resultSet) throws SQLException {
        return Request.Builder.requestBuilder()
                    .id(resultSet.getLong("request.id"))
                    .action(RequestActions.getValue(resultSet.getString("request.action")))
                    .status(RequestStatus.getValue(resultSet.getString("request.status")))
                    .build();
    }

}
