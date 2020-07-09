package ua.imiluxa.trainingproject.controller.command;

import ua.imiluxa.trainingproject.service.ActivityService;
import ua.imiluxa.trainingproject.service.RequestService;

import javax.servlet.http.HttpServletRequest;

public class AllRequestsCommand implements Command {
    private final RequestService requestService;
    private final ActivityService activityService;

    public AllRequestsCommand(RequestService requestService, ActivityService activityService) {
        this.requestService = requestService;
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String strIdAct = request.getParameter("request.act.id");

        if (strIdAct==null)
            return "/activities.jsp";

        Long idAct = Long.valueOf(strIdAct);
        request.setAttribute("requests", requestService.getRequestsByActivityId(idAct));
        request.setAttribute("activityReq", activityService.getActivityById(idAct).getName());

        return "/requests.jsp";
    }

}
