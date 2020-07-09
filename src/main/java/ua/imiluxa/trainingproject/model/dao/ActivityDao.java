package ua.imiluxa.trainingproject.model.dao;

import ua.imiluxa.trainingproject.model.entity.Activity;

import java.util.List;

public interface ActivityDao extends GenericDao<Activity> {
    List<Activity> findAllPageable(int page, int size);
    List<Activity> findByUserId(long userid);
    void saveHistory(Activity activity);
    List<Activity> findHistoryByUserId(long userid);
    List<Activity> findAllHistory();
}
