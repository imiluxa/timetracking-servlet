package ua.imiluxa.trainingproject.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.imiluxa.trainingproject.dto.RequestDTO;
import ua.imiluxa.trainingproject.model.dao.ActivityDao;
import ua.imiluxa.trainingproject.model.dao.DaoFactory;
import ua.imiluxa.trainingproject.model.dao.RequestDao;
import ua.imiluxa.trainingproject.model.dao.UserDao;
import ua.imiluxa.trainingproject.model.entity.Request;
import ua.imiluxa.trainingproject.model.entity.RequestActions;
import ua.imiluxa.trainingproject.model.entity.RequestStatus;
import ua.imiluxa.trainingproject.util.exceptions.DAOException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class RequestService {
    private static final DaoFactory daoFactory = DaoFactory.getInstance();
    private static final Logger logger = LogManager.getLogger();

    public void create(RequestDTO requestDTO) {
        try {
            RequestDao requestDao = daoFactory.createRequestDao();
            Request request = Request.builder()
                    .user(requestDTO.getUser())
                    .status(requestDTO.getStatus())
                    .action(requestDTO.getAction())
                    .activity(requestDTO.getActivity())
                    .build();
            requestDao.create(request);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { daoFactory.getConnection().commit(); }
            catch (SQLException e) { throw new DAOException(e);}
        }
    }

    public void addRequest(long userId, long activityId, RequestActions requestActions, RequestStatus requestStatus) {
        try {
            RequestDao requestDao = daoFactory.createRequestDao();
            if (requestDao.findByActivityAndUserIds(activityId, userId) != null) {
                logger.info("request already created");
                return;
            }

            UserDao userDao = daoFactory.createUserDao();
            ActivityDao activityDao = daoFactory.createActivityDao();


            Request request = Request.builder()
                    .action(requestActions)
                    .status(requestStatus)
                    .user(userDao.findById(userId).orElseThrow(() ->
                            new Exception("cant find userid: " + userId)))
                    .activity(activityDao.findById(activityId).orElseThrow(() ->
                            new Exception("cant find activityid: " + activityId)))
                    .build();
            requestDao.create(request);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { daoFactory.getConnection().commit(); }
            catch (SQLException e) { throw new DAOException(e);}
        }
    }

    public Request getRequestById(long id) {
        try {
            RequestDao requestDao = daoFactory.createRequestDao();
            Request request = requestDao.findById(id).orElseThrow(() ->
                    new Exception("cant find requestId: " + id));
            daoFactory.getConnection().commit();
            return request;
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    public Request getRequestByUserIdAndActivityId(long userId, long actId) {
        try {
            RequestDao requestDao = daoFactory.createRequestDao();
            Optional<Request> request = requestDao.findByActivityAndUserIds(userId, actId);
            daoFactory.getConnection().commit();
            return request.orElse(null);
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    public List<Request> getRequestsByActivityId(long actId) {
        try {
            RequestDao requestDao = daoFactory.createRequestDao();
            List<Request> request = requestDao.findByActivity(actId);
            daoFactory.getConnection().commit();
            return request;
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }


    public void update(Request request) {
        try {
            RequestDao requestDao = daoFactory.createRequestDao();
            requestDao.update(request);
            daoFactory.getConnection().commit();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
}
