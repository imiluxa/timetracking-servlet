package ua.imiluxa.trainingproject.controller.command;

import ua.imiluxa.trainingproject.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class UsersCommand implements Command {
    private final UserService userService;

    public UsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("users", userService.getAllUsers());
        return "/users.jsp";
    }
}
