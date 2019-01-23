package com.violence.servlets.user;

import com.violence.entity.User;
import com.violence.repository.UserRepository;
import com.violence.repository.UserRepositoryImpl;
import com.violence.util.Utils;
import com.violence.util.api.parser.ObjectParserFromReq;
import com.violence.util.api.parser.ObjectParserFromReqImpl;

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
@WebServlet(urlPatterns = "/editUser")
public class EditUser extends HttpServlet {
    private UserRepository userRepository = new UserRepositoryImpl();
    private ObjectParserFromReq parserFromReq = new ObjectParserFromReqImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userRepository.getById(Utils.getUserIdBySession(request.getSession()));
        if (user != null) {
            request.setAttribute("user", user);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/html/user/editUser.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) parserFromReq.getObjectFromRequest(request, User.class);
        if (Utils.getUserRoleBySession(request).equals("admin") || user.getId().equals(Utils.getUserIdBySession(request.getSession()))) {
            if (userRepository.getUserByLogin(user.getLogin()) == null && userRepository.getUserByPhone(user.getPhone()) == null) {
                userRepository.edit(user);
                request.setAttribute("user", user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/html/user/trackUser.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("user", user);
                request.setAttribute("error", "user with same login or password already exist");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/html/user/addUser.jsp");
                dispatcher.forward(request,response);
            }
        } else {
            request.setAttribute("error", "user with same login or password already exist");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/html/user/addUser.jsp");
            dispatcher.forward(request,response);
        }
    }
}
