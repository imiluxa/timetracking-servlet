package ua.imiluxa.trainingproject;

import ua.imiluxa.trainingproject.controller.command.Command;
import ua.imiluxa.trainingproject.controller.utility.SecurityUtility;
import ua.imiluxa.trainingproject.model.dao.impl.ConnectionPoolHolder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.ResourceBundle;

public class Servlet extends HttpServlet {

    Connection connection = ConnectionPoolHolder.getConnection();

    SecurityUtility securityUtility = new SecurityUtility();
    private final ResourceBundle rb = ResourceBundle.getBundle("db");


    @Override
    public void init(ServletConfig servletConfig) {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String path = req.getRequestURI().replaceFirst(".*/app", "");
        Command command = securityUtility.commandMap.getOrDefault(path, r -> "/index.jsp");

        String page = command.execute(req);

        if (page.contains("redirect")) {
            resp.sendRedirect(req.getContextPath() +
                    req.getServletPath() +
                    page.replace("redirect:", ""));
            return;
        } else {
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }
}
