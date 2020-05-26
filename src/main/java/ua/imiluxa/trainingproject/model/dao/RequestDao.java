package ua.imiluxa.trainingproject.model.dao;

import ua.imiluxa.trainingproject.model.entity.Request;

import java.util.List;

public interface RequestDao extends GenericDao<Request> {
    List<Request> findAllPageable(int page, int size);
    List<Request> findByActivityAndUserIds(long activityId, long userid);
}
