package ua.imiluxa.trainingproject.controller.command;

import ua.imiluxa.trainingproject.dto.RequestDTO;
import ua.imiluxa.trainingproject.model.entity.Request;
import ua.imiluxa.trainingproject.model.entity.RequestActions;
import ua.imiluxa.trainingproject.model.entity.RequestStatus;
import ua.imiluxa.trainingproject.model.entity.User;
import ua.imiluxa.trainingproject.service.ActivityService;
import ua.imiluxa.trainingproject.service.RequestService;

import javax.servlet.http.HttpServletRequest;

public class UpdateRequestCommand implements Command {
    private final ActivityService activityService;
    private final RequestService requestService;

    public UpdateRequestCommand(ActivityService activityService,
                                RequestService requestService) {
        this.activityService = activityService;
        this.requestService = requestService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("roleUser");

        String strActivityId = request.getParameter("act.id");
        String strRequestId = request.getParameter("request.id");
        String reqAction = request.getParameter("reqAction");
        String reqStatus = request.getParameter("reqStatus");

        Long actId = strActivityId != "" ? Long.valueOf(strActivityId) : 0L;
        Long reqId = strRequestId != "" ? Long.valueOf(strRequestId) : 0L;

        reqStatus = reqStatus == null ? RequestStatus.WAITING.toString() : reqStatus;

        if (reqId == 0L) {
            RequestDTO req = RequestDTO.builder()
                    .action(RequestActions.valueOf(reqAction))
                    .status(RequestStatus.valueOf(reqStatus))
                    .user(user)
                    .activity(activityService.getActivityById(actId))
                    .build();

            requestService.create(req);
        } else {
            Request req = requestService.getRequestById(reqId);

            req.setAction(RequestActions.valueOf(reqAction));
            req.setStatus(RequestStatus.valueOf(reqStatus));

            requestService.update(req);
        }

        return "redirect:/activities";
    }
}
