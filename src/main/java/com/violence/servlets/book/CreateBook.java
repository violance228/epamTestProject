package com.violence.servlets.book;

import com.violence.entity.Author;
import com.violence.entity.AuthorBooks;
import com.violence.entity.Book;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Named
@RequestScoped
@WebServlet(urlPatterns = "/createBook")
public class CreateBook extends HttpServlet {

    private BookRepository bookRepository = new BookRepositoryImpl();
    private AuthorRepository authorRepository = new AuthorRepositoryImpl();
    private AuthorBooksRepository authorBooksRepository = new AuthorBooksRepositoryImpl();
    private ObjectParserFromReq parser = new ObjectParserFromReqImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Utils.getUserRoleBySession(request).equals("admin")) {
            List<Author> authors = authorRepository.getAll();
            request.setAttribute("authors", authors);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/html/book/addBook.jsp");
            requestDispatcher.forward(request, response);
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login");
            requestDispatcher.include(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = (Book) parser.getObjectFromRequest(request, Book.class);
        bookRepository.save(book);
        setAuthorBook(request.getParameterValues("authors"), bookRepository.getLastRecord().getId());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/getAllBooks");
        requestDispatcher.include(request, response);
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
