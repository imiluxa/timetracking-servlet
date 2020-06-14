package ua.imiluxa.trainingproject.model.dao;

import ua.imiluxa.trainingproject.model.entity.Request;

import java.util.List;
import java.util.Optional;

public interface RequestDao extends GenericDao<Request> {
    List<Request> findAllPageable(int page, int size);
    Optional<Request> findByActivityAndUserIds(long activityId, long userid);
    List<Request> findByActivity(long activityId);
}
