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

@Named
@RequestScoped
@WebServlet(urlPatterns = "/viewUser")
public class ViewUser extends HttpServlet {

    private UserRepository userRepository = new UserRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Long userId = Long.valueOf(req.getSession().getAttribute("user_id").toString());
        String userRole = req.getSession().getAttribute("user_role").toString();
        if (userRole != null && userRole.equals("admin") || userId.equals(Long.valueOf(req.getAttribute("user_id").toString()))) {
            User user = userRepository.getById(Long.valueOf(req.getAttribute("user_id").toString()));
            req.setAttribute("user", user);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/html/user/viewUser.jsp");
            requestDispatcher.forward(req, resp);
        } else if (userId == null) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/html/user/login.jsp");
            requestDispatcher.include(req, resp);
        }
    }
}