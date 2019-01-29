package com.violence.servlets.author;

import com.violence.entity.Author;
import com.violence.repository.AuthorRepository;
import com.violence.repository.AuthorRepositoryImpl;
import com.violence.util.Utils;

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
@PermitAll
@RequestScoped
@WebServlet(urlPatterns = "/getAuthor")
public class ViewAuthor extends HttpServlet {

    private static final AuthorRepository authorRepository = new AuthorRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Author author = authorRepository.getById(Utils.getLongParamFromReq(req, "author_id"));
        req.setAttribute("author", author);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/html/author/getAuthor.jsp");
        requestDispatcher.forward(req, resp);
    }
}