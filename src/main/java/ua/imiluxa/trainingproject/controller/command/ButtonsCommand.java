package ua.imiluxa.trainingproject.controller.command;

import javax.servlet.http.HttpServletRequest;

@Deprecated
public class ButtonsCommand implements Command {


    @Override
    public String execute(HttpServletRequest request) {
        if (request.getParameter("edit.activity") != null) return "forward:/add_activity.jsp";
        else if (request.getParameter("edit.request") != null) return "forward:/index.jsp";
        return null;
    }
}
