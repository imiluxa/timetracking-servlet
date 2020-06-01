package ua.imiluxa.trainingproject.controller.command;

import ua.imiluxa.trainingproject.dto.ActivityDTO;
import ua.imiluxa.trainingproject.dto.RequestDTO;
import ua.imiluxa.trainingproject.model.entity.*;
import ua.imiluxa.trainingproject.service.ActivityService;
import ua.imiluxa.trainingproject.service.RequestService;
import ua.imiluxa.trainingproject.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UpdateActivityCommand implements Command {
    private final ActivityService activityService;
    private final UserService userService;
    private final RequestService requestService;

    public UpdateActivityCommand(ActivityService activityService,
                                 UserService userService,
                                 RequestService requestService) {
        this.activityService = activityService;
        this.userService = userService;
        this.requestService = requestService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("roleUser");
        if (user.getRole() == Role.ADMIN) {
            String strActivityId = request.getParameter("activity.id");
            String activityName = request.getParameter("activity.name");
            String activityGoal = request.getParameter("activity.goal");
            String strActivityDuration = request.getParameter("activity.duration");
            String activityStatus = request.getParameter("status");
            String reqAction = request.getParameter("reqAction");
            String reqStatus = request.getParameter("reqStatus");
            String username = request.getParameter("username");

            Long activityId = 0L;
            Long actDuration = 0L;

            try {
                activityId = Long.valueOf(strActivityId);
                actDuration = Long.valueOf(strActivityDuration); }
            catch (Exception e) {
                throw new RuntimeException(e);
            }

            User reqUser = userService.findByUsername(username).get();

            if (activityId==0L) {

                ActivityDTO activityDTO = ActivityDTO.builder()
                        .name(activityName)
                        .goal(activityGoal)
                        .duration(actDuration)
                        .setStatusActivity(StatusActivity.valueOf(activityStatus))
                        .user(reqUser)
                        .build();

                activityService.createNewActivity(activityDTO);

                List<Activity> activities = activityService.getAllActivitiesByUserId(reqUser.getId());

                for (Activity activity:
                     activities) {
                    if (activity.getRequest() == null) {
                        RequestDTO requestDTO = RequestDTO.builder()
                                .activity(activity)
                                .user(reqUser)
                                .action(RequestActions.valueOf(reqAction))
                                .status(RequestStatus.valueOf(reqStatus))
                                .build();

                        requestService.create(requestDTO);
                        break;
                    }
                }

            } else {
                Activity activity = Activity.builder()
                        .idactivity(activityId)
                        .name(activityName)
                        .goal(activityGoal)
                        .duration(actDuration)
                        .statusActivity(StatusActivity.valueOf(activityStatus))
                        .user(reqUser)
                        .build();

                Request updRequest = Request.builder()
                        .activity(activity)
                        .user(reqUser)
                        .action(RequestActions.valueOf(reqAction))
                        .status(RequestStatus.valueOf(reqStatus))
                        .build();

                activityService.update(activity);
                requestService.update(updRequest);

            }

        } else if (user.getRole() == Role.USER) {
            String strActivityId = request.getParameter("activity.id");
            String activityDuration = request.getParameter("activity.duration");
            String activityStatus = request.getParameter("status");
            String reqAction = request.getParameter("reqAction");
            String reqStatus = request.getParameter("reqStatus");

            Activity activity = activityService.getActivityById(Long.valueOf(strActivityId));

            Activity updActivity = Activity.builder()
                    .idactivity(activity.getIdactivity())
                    .name(activity.getName())
                    .goal(activity.getGoal())
                    .duration(Long.valueOf(activityDuration))
                    .statusActivity(StatusActivity.valueOf(activityStatus))
                    .user((User) request.getSession().getAttribute("roleUser"))
                    .build();

            Request updRequest = Request.builder()
                    .activity(activity)
                    .user((User) request.getSession().getAttribute("roleUser"))
                    .action(RequestActions.valueOf(reqAction))
                    .status(RequestStatus.valueOf(reqStatus))
                    .build();

            activityService.update(updActivity);
            requestService.update(updRequest);


        } else {
            return "/activities.jsp";
        }
        return "/activities.jsp";
    }
}
