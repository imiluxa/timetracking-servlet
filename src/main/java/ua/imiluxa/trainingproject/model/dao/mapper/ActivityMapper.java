package ua.imiluxa.trainingproject.model.dao.mapper;

import ua.imiluxa.trainingproject.model.entity.Activity;
import ua.imiluxa.trainingproject.model.entity.StatusActivity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ActivityMapper implements ObjectMapper<Activity> {

    @Override
    public Activity makeUnique(Map<Long, Activity> map, Activity object) {
        map.putIfAbsent(object.getIdactivity(), object);
        return map.get(object.getIdactivity());
    }

    @Override
    public Activity extractFromResultSet(ResultSet resultSet) throws SQLException {
        return Activity.Builder.activityBuilder()
                .idactivity(resultSet.getLong("activities.idactivity"))
                .duration(resultSet.getLong("activities.duration"))
                .goal(resultSet.getString("activities.goal"))
                .name(resultSet.getString("activities.name"))
                .statusActivity(StatusActivity.valueOf(resultSet.getString("activities.status")))
                .build();
    }
}
