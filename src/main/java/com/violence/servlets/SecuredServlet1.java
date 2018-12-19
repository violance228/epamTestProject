package com.violence.servlets;

import com.violence.repository.UserRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/test")
public class SecuredServlet1 extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        try {
            Long start = System.currentTimeMillis();
            userRepository.getAll();
            userRepository.getById(1l);
            System.out.println(System.currentTimeMillis() - start);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp
                .getWriter()
                .write("<h1>Secured Servlet1</h1>");
    }
}
