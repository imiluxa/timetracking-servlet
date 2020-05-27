package ua.imiluxa.trainingproject.model.dao.mapper;

import ua.imiluxa.trainingproject.model.entity.Role;
import ua.imiluxa.trainingproject.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {
    @Override
    public User makeUnique(Map<Long, User> map, User user) {
        map.putIfAbsent(user.getId(), user);
        return map.get(user.getId());
    }

    @Override
    public User extractFromResultSet(ResultSet resultSet) throws SQLException {
        return User.Builder.userbuilder()
                .id(resultSet.getLong("user.id"))
                .firstName(resultSet.getString("user.firstname"))
                .lastName(resultSet.getString("user.lastname"))
                .password(resultSet.getString("user.password"))
                .userName(resultSet.getString("user.username"))
                .email(resultSet.getString("user.email"))
                .role(Role.valueOf(resultSet.getString("user.role")))
                .build();
    }
}
