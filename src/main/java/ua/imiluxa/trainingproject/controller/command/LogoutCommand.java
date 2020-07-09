package ua.imiluxa.trainingproject.controller.command;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        request.getSession().getServletContext().removeAttribute("roleUser");
        request.getSession().invalidate();

        /*HttpSession session = request.getSession();
        session.removeAttribute("roleUser");
        session.removeAttribute("role");
        session.invalidate();*/

        return "/index.jsp";
    }
}
