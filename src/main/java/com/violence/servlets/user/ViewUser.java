package com.violence.servlets.user;

import com.violence.entity.User;
import com.violence.repository.UserRepository;
import com.violence.repository.UserRepositoryImpl;
import com.violence.util.Utils;

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
        Long userId = Utils.getUserIdBySession(req.getSession());
        String userRole = Utils.getUserRoleBySession(req);
        if (userRole != null && userRole.equals("admin") || userId.equals(Long.valueOf(req.getAttribute("user_id").toString()))) {
            User user = userRepository.getById(Utils.getLongParamFromReq(req, "user_id"));
            req.setAttribute("user", user);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/html/user/trackUser.jsp");
            requestDispatcher.forward(req, resp);
        } else if (userId == null) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/html/user/login.jsp");
            requestDispatcher.include(req, resp);
        }
    }
}