package ua.imiluxa.trainingproject.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.imiluxa.trainingproject.model.dao.ActivityDao;
import ua.imiluxa.trainingproject.model.dao.DaoFactory;
import ua.imiluxa.trainingproject.model.dao.RequestDao;
import ua.imiluxa.trainingproject.model.dao.UserDao;
import ua.imiluxa.trainingproject.model.entity.*;
import ua.imiluxa.trainingproject.util.exceptions.DAOException;


public class RequestService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private static final Logger logger = LogManager.getLogger();

    public void addRequest(long userId, long activityId) {
        try (RequestDao requestDao = daoFactory.createRequestDao()) {

            if (requestDao.findByActivityAndUserIds(activityId, userId) != null) {
                logger.info("request already created");
                return;
            }

            UserDao userDao = daoFactory.createUserDao();
            ActivityDao activityDao = daoFactory.createActivityDao();

            Request request = Request.builder()
                    .action(RequestActions.ADD)
                    .status(RequestStatus.WAITING)
                    .user(userDao.findById(userId).orElseThrow(() ->
                            new Exception("cant find userid: " + userId)))
                    .activity(activityDao.findById(activityId).orElseThrow(() ->
                            new Exception("cant find activityid: " + activityId)))
                    .build();
            requestDao.create(request);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Request getRequestById(long id) {
        try (RequestDao requestDao = daoFactory.createRequestDao()) {
            return requestDao.findById(id).orElseThrow(() ->
                    new Exception("cant find requestId: " + id));
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    public void changeRequest(Request request, RequestStatus requestStatus,
                              RequestActions requestActions) {
        try (RequestDao requestDao = daoFactory.createRequestDao()) {
            Request updateRequest = Request.builder()
                    .action(requestActions)
                    .status(requestStatus)
                    .user(request.getUser())
                    .activity(request.getActivity())
                    .build();
            requestDao.update(updateRequest);
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
}
