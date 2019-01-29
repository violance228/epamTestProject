package com.violence.servlets.user;

import com.violence.entity.User;
import com.violence.repository.UserRepository;
import com.violence.repository.UserRepositoryImpl;
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
@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {
    private static final UserRepository userRepository = new UserRepositoryImpl();
    private static final Logger logger = Logger.getLogger(Login.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/html/user/login.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userRepository.getUserByLogin(request.getParameter("login"));
        if (user != null && user.getPassword().equals(request.getParameter("password"))) {
            request.getSession().setAttribute("user_id", user.getId());
            request.getSession().setAttribute("user_role", user.getRole());
            request.setAttribute("user", user);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/html/user/trackUser.jsp");
            requestDispatcher.forward(request, response);
            logger.info("user " + Utils.getUserIdBySession(request.getSession()) + " was login, user role " + user.getRole());
        } else {
            request.setAttribute("error", "login ar password incorrect");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/html/user/login.jsp");
            requestDispatcher.forward(request, response);
            logger.info("user wrote incorrect login or password");
        }
    }
}
