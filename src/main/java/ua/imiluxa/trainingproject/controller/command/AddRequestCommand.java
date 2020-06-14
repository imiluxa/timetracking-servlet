package ua.imiluxa.trainingproject.controller.command;

import ua.imiluxa.trainingproject.dto.RequestDTO;
import ua.imiluxa.trainingproject.model.entity.*;
import ua.imiluxa.trainingproject.service.ActivityService;
import ua.imiluxa.trainingproject.service.RequestService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class AddRequestCommand implements Command {
    private final ActivityService activityService;
    private final RequestService requestService;

    public AddRequestCommand(ActivityService activityService, RequestService requestService) {
        this.activityService = activityService;
        this.requestService = requestService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String strIdAct = request.getParameter("request.act.id");
        User user = (User) request.getSession().getAttribute("roleUser");

        setDefAttributes(request);

        if (strIdAct != null) {
            Long idAct = Long.valueOf(strIdAct);
            Activity activity = activityService.getActivityById(idAct);
            request.setAttribute("idAct", idAct);
            Request req = requestService.getRequestByUserIdAndActivityId(user.getId(), idAct);

            if (activity.getUser() == null) {
                requestService.create(RequestDTO.builder().user(user)
                        .action(RequestActions.ADD)
                        .status(RequestStatus.WAITING)
                        .activity(activity)
                        .build());
                return "/activities.jsp";
            } else {
                if (!Objects.isNull(req))
                    request.setAttribute("request", req);
            }

        }
        return "/add_request.jsp";
    }

    private void setDefAttributes(HttpServletRequest request) {
        request.setAttribute("requestActions", RequestActions.values());
        request.setAttribute("requestStatus", RequestStatus.values());
    }

}
