package ua.imiluxa.trainingproject.controller.command;

import ua.imiluxa.trainingproject.dto.ActivityDTO;
import ua.imiluxa.trainingproject.model.entity.Activity;
import ua.imiluxa.trainingproject.model.entity.Role;
import ua.imiluxa.trainingproject.model.entity.StatusActivity;
import ua.imiluxa.trainingproject.model.entity.User;
import ua.imiluxa.trainingproject.service.ActivityService;
import ua.imiluxa.trainingproject.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class UpdateActivityCommand implements Command {
    private final ActivityService activityService;
    private final UserService userService;

    public UpdateActivityCommand(ActivityService activityService,
                                 UserService userService) {
        this.activityService = activityService;
        this.userService = userService;

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
            String username = request.getParameter("username");

            Long activityId = 0L;
            Long actDuration = 0L;

            User reqUser = userService.findByUsername(username).get();

            if (strActivityId=="") {

                try {
                    actDuration = Long.valueOf(strActivityDuration); }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }

                ActivityDTO activityDTO = ActivityDTO.builder()
                        .name(activityName)
                        .goal(activityGoal)
                        .duration(actDuration)
                        .setStatusActivity(StatusActivity.valueOf(activityStatus))
                        .user(reqUser)
                        .build();

                activityService.createNewActivity(activityDTO);

            } else {

                try {
                    activityId = Long.valueOf(strActivityId);
                    actDuration = Long.valueOf(strActivityDuration); }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }

                Activity activity = Activity.builder()
                        .idactivity(activityId)
                        .name(activityName)
                        .goal(activityGoal)
                        .duration(actDuration)
                        .statusActivity(StatusActivity.valueOf(activityStatus))
                        .user(reqUser)
                        .build();

                activityService.update(activity);

            }

        } else if (user.getRole() == Role.USER) {
            String strActivityId = request.getParameter("activity.id");
            String activityDuration = request.getParameter("activity.duration");
            String activityStatus = request.getParameter("status");

            Activity activity = activityService.getActivityById(Long.valueOf(strActivityId));

            Activity updActivity = Activity.builder()
                    .idactivity(activity.getIdactivity())
                    .name(activity.getName())
                    .goal(activity.getGoal())
                    .duration(Long.valueOf(activityDuration))
                    .statusActivity(StatusActivity.valueOf(activityStatus))
                    .user((User) request.getSession().getAttribute("roleUser"))
                    .build();

            activityService.update(updActivity);

        }
        return "redirect:/activities";
    }
}
