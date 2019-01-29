package com.violence.servlets.catalog;

import com.violence.entity.Book;
import com.violence.entity.Catalog;
import com.violence.entity.User;
import com.violence.repository.*;
import com.violence.util.Utils;
import com.violence.util.api.parser.ObjectParserFromReq;
import com.violence.util.api.parser.ObjectParserFromReqImpl;
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
import java.util.List;

@Named
@RequestScoped
@WebServlet(urlPatterns = "/addCatalog")
public class AddCatalog extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AddCatalog.class);

    private static final CatalogRepository catalogRepository = new CatalogRepositoryImpl();
    private static final UserRepository userRepository = new UserRepositoryImpl();
    private static final BookRepository bookRepository = new BookRepositoryImpl();
    private static final ObjectParserFromReq parser = new ObjectParserFromReqImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (Utils.getUserRoleBySession(req).equals("admin")) {
            List<User> users = userRepository.getAll();
            List<Book> books = bookRepository.getAll();
            req.setAttribute("users", users);
            req.setAttribute("books", books);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/html/catalog/addCatalog.jsp");
            requestDispatcher.forward(req, resp);
            logger.info("user was went in addCatalog controller doGet, userId " + Utils.getUserIdBySession(req.getSession()));
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login");
            requestDispatcher.forward(req, resp);
            logger.info("user " + Utils.getUserIdBySession(req.getSession()) + " didn't have role admin, and he was redirect into login" );
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (Utils.getUserRoleBySession(req).equals("admin")) {
            Book book = bookRepository.getById(Utils.getLongParamFromReq(req, "book"));
            Catalog catalog = (Catalog) parser.getObjectFromRequest(req, Catalog.class);
            catalog.setUser(userRepository.getById(Utils.getLongParamFromReq(req, "user")));
            if (!book.getUse()) {
                catalog.setBook(book);
                catalogRepository.save(catalog);
                book.setUse(true);
                bookRepository.edit(book);
                req.setAttribute("catalog", catalog);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/getCatalog");
                requestDispatcher.forward(req, resp);
                logger.info("user " + Utils.getUserIdBySession(req.getSession()) + " was save catalog with id " + catalog.getId());
            } else {
                req.setAttribute("error", "this book is not available");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/getCatalog");
                requestDispatcher.forward(req, resp);
            }
        }
    }
}
