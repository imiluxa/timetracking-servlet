package ua.imiluxa.trainingproject.controller.utility;

import ua.imiluxa.trainingproject.controller.command.*;
import ua.imiluxa.trainingproject.model.entity.Role;
import ua.imiluxa.trainingproject.model.entity.User;
import ua.imiluxa.trainingproject.service.ActivityService;
import ua.imiluxa.trainingproject.service.RequestService;
import ua.imiluxa.trainingproject.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.*;

public class SecurityUtility {
    public static final Map<Role, Set<String>> authCommands = new HashMap<>();
    public static UserService userService = new UserService();
    public static ActivityService activityService = new ActivityService();
    public static RequestService requestService = new RequestService();
    public static Map<String, Command> commandMap = new HashMap<>();

    public SecurityUtility() {
        authCommands.put(null,
                new HashSet<>(Arrays.asList("/", "/index", "/registration", "/login", "/log")));
        authCommands.put(Role.USER,
                new HashSet<>(Arrays.asList("/", "/index",  "/log", "/logout", "/user", "/activities", "/request", "/update_activity", "/add_activity", "/update_activity", "/add_request", "/update_request")));
        authCommands.put(Role.ADMIN,
                new HashSet<>(Arrays.asList("/", "/index",  "/log", "/logout", "/user", "/activities", "/request", "/create_activity", "/users", "/add_activity", "/update_activity", "/add_request", "/update_request")));
        commandMap.put("/registration", new RegistrationCommand(userService));
        commandMap.put("/activities", new ActivitiesCommand(activityService));
        commandMap.put("/index", new HomeCommand());
        commandMap.put("/logout", new LogoutCommand());
        commandMap.put("/login", new LoginCommand(userService));
        commandMap.put("/users", new UsersCommand(userService));
        commandMap.put("/add_activity", new AddActivityCommand(activityService, userService));
        commandMap.put("/update_activity", new UpdateActivityCommand(activityService, userService/*, requestService*/));
        commandMap.put("/add_request", new AddRequestCommand(activityService, requestService));
        commandMap.put("/update_request", new UpdateRequestCommand(activityService, requestService));
    }

    public static void init() {

    }

    public Role getSessionRole(HttpSession session) {
        User user = (User) session.getAttribute("roleUser");
        return user == null ? null : user.getRole();
    }
}
