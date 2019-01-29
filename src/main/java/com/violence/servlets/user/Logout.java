package com.violence.servlets.user;

import com.violence.util.Utils;
import org.apache.log4j.Logger;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Named
@RequestScoped
@PermitAll
@WebServlet(urlPatterns = "/logout")
public class Logout extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Login.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        logger.info("user " + Utils.getUserIdBySession(request.getSession()) + "try logout");
        if (request.getSession() != null) {
            request.getSession().invalidate();
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/html/user/login.jsp");
        try {
            requestDispatcher.include(request, response);
        } catch (ServletException | IOException e) {
            logger.info(e.getMessage());
        }
    }
}
