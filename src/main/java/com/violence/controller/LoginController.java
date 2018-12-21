package com.violence.controller;

import com.violence.entity.User;
import com.violence.repository.UserRepository;
import com.violence.repository.UserRepositoryImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginController implements Filter {

    private UserRepository userRepository = new UserRepositoryImpl();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = userRepository.getUserByLogin(login);
        HttpSession httpSession = request.getSession();

        if (httpSession != null &&
                httpSession.getAttribute("login") != null &&
                httpSession.getAttribute("password") != null) {

        } else if(user != null) {
            if (user.getPassword().equals(password)) {
                httpSession.setAttribute("login", login);
//                httpSession.setAttribute("role", );
            }
        }
    }

    @Override
    public void destroy() {

    }
}
