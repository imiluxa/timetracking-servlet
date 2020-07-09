package ua.imiluxa.trainingproject.controller.command;

import ua.imiluxa.trainingproject.model.entity.Role;
import ua.imiluxa.trainingproject.model.entity.User;
import ua.imiluxa.trainingproject.service.ActivityService;

import javax.servlet.http.HttpServletRequest;

public class HistoryCommand implements Command {
    private final ActivityService activityService;

    public HistoryCommand(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("roleUser");
        if (user.getRole() == Role.ADMIN)
            request.setAttribute("activities", activityService.findAllHistory());
        else
            request.setAttribute("activities", activityService.findHistoryByUserId(user.getId()));

        return "/history.jsp";
    }
}
