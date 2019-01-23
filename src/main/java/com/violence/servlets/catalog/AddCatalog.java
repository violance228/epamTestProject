package com.violence.servlets.catalog;

import com.violence.entity.Book;
import com.violence.entity.Catalog;
import com.violence.entity.User;
import com.violence.repository.*;
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
import java.util.List;

/**
 * created by user violence
 * created on 23.01.2019
 * class created for project SecurityExample
 */

@Named
@RequestScoped
@WebServlet(urlPatterns = "/addCatalog")
public class AddCatalog extends HttpServlet {

    private CatalogRepository catalogRepository = new CatalogRepositoryImpl();
    private UserRepository userRepository = new UserRepositoryImpl();
    private BookRepository bookRepository = new BookRepositoryImpl();
    private ObjectParserFromReq parser = new ObjectParserFromReqImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (Utils.getUserRoleBySession(req).equals("admin")) {
            List<User> users = userRepository.getAll();
            List<Book> books = bookRepository.getAll();
            req.setAttribute("users", users);
            req.setAttribute("books", books);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/html/catalog/addCatalog.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (Utils.getUserRoleBySession(req).equals("admin")) {
            Catalog catalog = (Catalog) parser.getObjectFromRequest(req, Catalog.class);
            catalog.setUser(userRepository.getById(Utils.getLongParamFromReq(req, "user")));
            catalog.setBook(bookRepository.getById(Utils.getLongParamFromReq(req, "book")));
            catalogRepository.save(catalog);
            req.setAttribute("catalog", catalog);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/getCatalog");
            requestDispatcher.include(req, resp);
        }
    }
}
