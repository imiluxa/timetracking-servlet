package ua.imiluxa.trainingproject.controller.command;

import org.apache.commons.lang3.ObjectUtils;
import ua.imiluxa.trainingproject.dto.UserDTO;
import ua.imiluxa.trainingproject.model.entity.Role;
import ua.imiluxa.trainingproject.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {
    private final UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String password = request.getParameter("password");

        if(!ObjectUtils.allNotNull(username, firstname, lastname, password)) {
            return "/registration.jsp";
        }

        UserDTO userDto = UserDTO.builder()
                .username(username)
                .firstName(firstname)
                .lastName(lastname)
                .password(password)
                .role(Role.USER)
                .build();

        try {
            userService.createNewUser(userDto);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/login";
    }
}
