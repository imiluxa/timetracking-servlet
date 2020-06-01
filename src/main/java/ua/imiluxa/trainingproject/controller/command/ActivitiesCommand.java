package ua.imiluxa.trainingproject.controller.command;

import ua.imiluxa.trainingproject.model.entity.User;
import ua.imiluxa.trainingproject.service.ActivityService;

import javax.servlet.http.HttpServletRequest;

public class ActivitiesCommand implements Command {
    private final ActivityService activityService;

    public ActivitiesCommand(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getSession().getAttribute("role") == "ADMIN") {
            request.setAttribute("activities", activityService.getAllActivities());

        } else {
            User user = (User) request.getSession().getAttribute("roleUser");
            request.setAttribute("activities", activityService.getAllActivitiesByUserId(user.getId()));
        }
        return "/activities.jsp";
    }
}
