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
import java.util.List;

@Named
@PermitAll
@RequestScoped
@WebServlet(urlPatterns = "/getAllAuthors")
public class ViewAllAuthor extends HttpServlet {

    private AuthorRepository authorRepository = new AuthorRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (Utils.getUserRoleBySession(req).equals("admin")) {
            List<Author> authors = authorRepository.getAll();
            req.setAttribute("authors", authors);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/html/author/getAllAuthor.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/personalInform");
            requestDispatcher.include(req, resp);
        }
    }
}