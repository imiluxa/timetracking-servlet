package ua.imiluxa.trainingproject.controller.command;

import ua.imiluxa.trainingproject.service.ActivityService;

import javax.servlet.http.HttpServletRequest;

public class ActivitiesCommand implements Command {
    private final ActivityService activityService;

    public ActivitiesCommand(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
            request.setAttribute("activities", activityService.getAllActivities());

        return "/activities.jsp";
    }
}
