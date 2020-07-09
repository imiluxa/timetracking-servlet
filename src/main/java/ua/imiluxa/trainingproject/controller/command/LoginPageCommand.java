package ua.imiluxa.trainingproject.controller.command;

import javax.servlet.http.HttpServletRequest;

public class LoginPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String role = (String) request.getSession().getAttribute("role");

        if (role == "")
            return "/login.jsp";
        else
            return "/index.jsp";
    }
}
