package ua.imiluxa.trainingproject.model.dao.mapper;

import ua.imiluxa.trainingproject.model.entity.Request;
import ua.imiluxa.trainingproject.model.entity.RequestActions;
import ua.imiluxa.trainingproject.model.entity.RequestStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class RequestMapper implements ObjectMapper<Request> {

    @Override
    public Request makeUnique(Map<Long, Request> map, Request object) {
        map.putIfAbsent(object.getId(), object);
        return map.get(object.getId());
    }

    @Override
    public Request extractFromResultSet(ResultSet resultSet) throws SQLException {
        return Request.Builder.requestBuilder()
                    .id(resultSet.getLong("request.id"))
                    .action(RequestActions.valueOf(resultSet.getString("request.action")))
                    .status(RequestStatus.valueOf(resultSet.getString("request.status")))
                    .build();
    }


}
