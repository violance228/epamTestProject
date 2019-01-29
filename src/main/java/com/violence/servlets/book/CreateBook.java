package com.violence.servlets.book;

import com.violence.entity.Author;
import com.violence.entity.AuthorBooks;
import com.violence.entity.Book;
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
@WebServlet(urlPatterns = "/createBook")
public class CreateBook extends HttpServlet {
    private static final Logger logger = Logger.getLogger(CreateBook.class);

    private static final BookRepository bookRepository = new BookRepositoryImpl();
    private static final AuthorRepository authorRepository = new AuthorRepositoryImpl();
    private static final AuthorBooksRepository authorBooksRepository = new AuthorBooksRepositoryImpl();
    private static final ObjectParserFromReq parser = new ObjectParserFromReqImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (Utils.getUserRoleBySession(request).equals("admin")) {
                List<Author> authors = authorRepository.getAll();
                request.setAttribute("authors", authors);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/html/book/addBook.jsp");
                requestDispatcher.forward(request, response);
                logger.info("user was went in createBook controller doGet, userId " + Utils.getUserIdBySession(request.getSession()));
            } else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login");
                requestDispatcher.include(request, response);
                logger.info("user " + Utils.getUserIdBySession(request.getSession()) + " didn't have role admin, and he was redirect into login");
            }
        } catch (ServletException | IOException e) {
            logger.error("can't execute method doGet in servlet createBook: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (Utils.getUserRoleBySession(request).equals("admin")) {
                Book book = (Book) parser.getObjectFromRequest(request, Book.class);
                bookRepository.save(book);
                setAuthorBook(request.getParameterValues("authors"), bookRepository.getLastRecord().getId());
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/getAllBooks");
                requestDispatcher.include(request, response);
                logger.info("user " + Utils.getUserIdBySession(request.getSession()) + " was save book with id " + book.getId());
            }
        } catch (ServletException | IOException e) {
            logger.error("can't execute method doPost in servlet createBook: " + e.getMessage());
        }
    }

    private void setAuthorBook(String[] param, Long bookId) {
        AuthorBooks authorBooks = new AuthorBooks();
        for (String s : param) {
            authorBooks.setAuthorId(Long.valueOf(s));
            authorBooks.setBookId(bookId);
            authorBooksRepository.save(authorBooks);
        }
    }
}
