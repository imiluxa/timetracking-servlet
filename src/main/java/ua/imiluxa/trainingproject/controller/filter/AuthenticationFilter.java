package ua.imiluxa.trainingproject.controller.filter;

import ua.imiluxa.trainingproject.controller.utility.SecurityUtility;
import ua.imiluxa.trainingproject.model.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

public class AuthenticationFilter implements Filter {

    SecurityUtility securityUtility = new SecurityUtility();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        String reqString = request.getRequestURI()
                .replaceFirst(request.getContextPath() + "/app", "");
        Set<String> allowedPath;

        Role role = securityUtility.getSessionRole(session);

        allowedPath = securityUtility.authCommands.get(role);

        if (allowedPath.contains(reqString)) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath() +
                    request.getServletPath() + "/index");
            return;
        }

    }

    @Override
    public void destroy() {

    }
}
