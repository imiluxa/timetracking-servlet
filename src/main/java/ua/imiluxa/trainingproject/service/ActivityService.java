package ua.imiluxa.trainingproject.service;

import ua.imiluxa.trainingproject.dto.ActivityDTO;
import ua.imiluxa.trainingproject.model.dao.ActivityDao;
import ua.imiluxa.trainingproject.model.dao.DaoFactory;
import ua.imiluxa.trainingproject.model.entity.Activity;
import ua.imiluxa.trainingproject.model.entity.StatusActivity;
import ua.imiluxa.trainingproject.model.entity.User;
import ua.imiluxa.trainingproject.util.exceptions.DAOException;

import java.util.List;

public class ActivityService {
    private static final DaoFactory daoFactory = DaoFactory.getInstance();

    public void createNewActivity(ActivityDTO activityDTO) {
        Activity activity = Activity.builder()
                .goal(activityDTO.getGoal())
                .name(activityDTO.getName())
                .statusActivity(StatusActivity.WAITING)
                .build();
        try {
            ActivityDao activityDao = daoFactory.createActivityDao();
            activityDao.create(activity);
            daoFactory.getConnection().commit();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    public Activity getActivityById(long id) {
        try {
            ActivityDao activityDao = daoFactory.createActivityDao();
            Activity activity = activityDao.findById(id).orElseThrow(() ->
                    new Exception("cant find id " + id));
            daoFactory.getConnection().commit();
            return activity;
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    public List<Activity> getAllActivities() {
        try {
            ActivityDao activityDao = daoFactory.createActivityDao();
            List<Activity> activities = activityDao.findAll();
            daoFactory.getConnection().commit();
            return activities;
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    public void setDuration(long id, User user, long duration) {
        try {
            ActivityDao activityDao = daoFactory.createActivityDao();
            Activity activity = getActivityById(id);

            if (activity.getUser().equals(user)) {
                activity.setDuration(duration);
                activityDao.update(activity);
            }

            daoFactory.getConnection().commit();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
}
