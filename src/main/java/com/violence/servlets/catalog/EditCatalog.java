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
@WebServlet(urlPatterns = "/editCatalog")
public class EditCatalog extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AddCatalog.class);

    private CatalogRepository catalogRepository = new CatalogRepositoryImpl();
    private UserRepository userRepository = new UserRepositoryImpl();
    private BookRepository bookRepository = new BookRepositoryImpl();
    private ObjectParserFromReq parser = new ObjectParserFromReqImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (Utils.getUserRoleBySession(req).equals("admin")) {
            Catalog catalog = catalogRepository.getById(Utils.getLongParamFromReq(req, "catalog_id"));
            List<User> users = userRepository.getAll();
            List<Book> books = bookRepository.getAllAvailable();
            req.setAttribute("catalog", catalog);
            req.setAttribute("users", users);
            req.setAttribute("books", books);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/html/catalog/editCatalog.jsp");
            requestDispatcher.forward(req, resp);
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
                catalogRepository.edit(catalog);
                book.setUse(true);
                bookRepository.edit(book);
                req.setAttribute("catalog", catalog);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/getCatalog");
                requestDispatcher.forward(req, resp);
                logger.info("user " + Utils.getUserIdBySession(req.getSession()) + " was edited catalog with id " + catalog.getId());
            } else {
                req.setAttribute("error", "this book is not available");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/getCatalog");
                requestDispatcher.forward(req, resp);
            }
        }
    }
}
