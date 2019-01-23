package com.violence.servlets.user;

import com.violence.entity.User;
import com.violence.repository.UserRepository;
import com.violence.repository.UserRepositoryImpl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * created by user violence
 * created on 23.01.2019
 * class created for project SecurityExample
 */

@Named
@RequestScoped
@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {
    private UserRepository userRepository = new UserRepositoryImpl();

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
        } else {
            request.setAttribute("error", "login ar password incorrect");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/html/user/login.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
