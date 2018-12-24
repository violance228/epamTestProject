package com.violence.servlets.user;

import com.violence.entity.User;
import com.violence.repository.UserRepository;
import com.violence.repository.UserRepositoryImpl;
import com.violence.util.api.parser.ObjectParserFromReq;
import com.violence.util.api.parser.ObjectParserFromReqImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/registration")
public class Registration extends HttpServlet {

    private UserRepository userRepository = new UserRepositoryImpl();
    private ObjectParserFromReq parser = new ObjectParserFromReqImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) parser.getObjectFromRequest(req, User.class);

        if (userRepository.getUserByLogin(user.getLogin()) == null) {
            userRepository.save(user);
        } else {

        }
    }

}
