package ua.imiluxa.trainingproject.controller.command;

import org.apache.commons.lang3.ObjectUtils;
import org.mindrot.jbcrypt.BCrypt;
import ua.imiluxa.trainingproject.model.entity.User;
import ua.imiluxa.trainingproject.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command{
    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (!ObjectUtils.allNotNull(username, password)) {
            return "/login.jsp";
        }

        Optional<User> user = userService.findByUsername(username);
        if (user.isEmpty()) {
            return "/login.jsp";
        }

        User findedUser = user.get();

        if (BCrypt.checkpw(password, findedUser.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("roleUser", findedUser);
            session.setAttribute("role", findedUser.getRole().toString());

            return "redirect:/index";
        } else {
            return "/login.jsp";
        }
    }
}
