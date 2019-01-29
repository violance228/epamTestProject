package com.violence.servlets.user;

import com.violence.entity.User;
import com.violence.repository.UserRepository;
import com.violence.repository.UserRepositoryImpl;
import com.violence.util.Utils;
import org.apache.log4j.Logger;

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

    private static final Logger logger = Logger.getLogger(ViewUser.class);
    private static final UserRepository userRepository = new UserRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Long userId = Utils.getUserIdBySession(req.getSession());
        String userRole = Utils.getUserRoleBySession(req);
        try {
            if (userRole != null && userRole.equals("admin") || userId.equals(Long.valueOf(req.getAttribute("user_id").toString()))) {
                User user = userRepository.getById(Utils.getLongParamFromReq(req, "user_id"));
                req.setAttribute("user", user);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/html/user/trackUser.jsp");
                requestDispatcher.forward(req, resp);
            } else if (userId == null) {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/html/user/login.jsp");
                requestDispatcher.include(req, resp);
            }
        } catch (ServletException | IOException e) {
            logger.info("exception in viewUser" + e.getMessage());
        }
    }
}