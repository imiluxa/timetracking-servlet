package ua.imiluxa.trainingproject.controller.command;

import ua.imiluxa.trainingproject.model.entity.*;
import ua.imiluxa.trainingproject.service.ActivityService;
import ua.imiluxa.trainingproject.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public class AddActivityCommand implements Command {
    private final ActivityService activityService;
    private final UserService userService;

    public AddActivityCommand(ActivityService activityService, UserService userService) {
        this.activityService = activityService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String strIdAct = request.getParameter("activity.id");

        setDefAttributes(request);
        if (strIdAct == null) {
            setDefAttributes(request);
        } else {
            Long idAct = Long.valueOf(strIdAct);
            Activity activity = activityService.getActivityById(idAct);
            if (!Objects.isNull(activity)) {
                request.setAttribute("activity", activity);
                setDefAttributes(request);
            }
        }

        return "/add_activity.jsp";
    }

    private void setDefAttributes(HttpServletRequest request) {
        List<User> users = userService.getAllUsers();
        request.setAttribute("statusActivities", StatusActivity.values());
        request.setAttribute("requestActions", RequestActions.values());
        request.setAttribute("requestStatus", RequestStatus.values());
        request.setAttribute("users", users);
    }
}
