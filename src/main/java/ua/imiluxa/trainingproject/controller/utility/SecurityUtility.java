package ua.imiluxa.trainingproject.controller.utility;

import ua.imiluxa.trainingproject.controller.command.*;
import ua.imiluxa.trainingproject.model.entity.Role;
import ua.imiluxa.trainingproject.model.entity.User;
import ua.imiluxa.trainingproject.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.*;

public class SecurityUtility {
    public static final Map<Role, Set<String>> authCommands = new HashMap<>();
    public static UserService userService = new UserService();
    public static Map<String, Command> commandMap = new HashMap<>();

    public SecurityUtility() {
        authCommands.put(null,
                new HashSet<>(Arrays.asList("/", "/index", "/registration", "/login", "/log")));
        authCommands.put(Role.USER,
                new HashSet<>(Arrays.asList("/", "/index", "/registration", "/login", "/log", "/logout", "/user", "/activity", "/request")));
        authCommands.put(Role.ADMIN,
                new HashSet<>(Arrays.asList("/", "/index", "/registration", "/login", "/log", "/logout", "/user", "/activity", "/request", "/create_activity", "/users")));
        commandMap.put("/registration", new RegistrationCommand(userService));
        //commandMap.put("/user", new UsersCommand());
        commandMap.put("/index", new HomeCommand());
        commandMap.put("/logout", new LogoutCommand());
        commandMap.put("/login", new LoginCommand(userService));
        commandMap.put("/users", new UsersCommand(userService));
    }

    public static void init() {


    }

    public boolean isAccessiblePath(String path, Role role) {
        return authCommands.entrySet()
                .stream()
                .noneMatch(e -> role.equals(e.getKey()));
    }

    public Role getSessionRole(HttpSession session) {
        User user = (User) session.getAttribute("roleUser");
        return user == null ? null : user.getRole();
    }
}
