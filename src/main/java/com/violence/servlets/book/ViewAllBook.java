package com.violence.servlets.book;

import com.violence.entity.Book;
import com.violence.repository.BookRepository;
import com.violence.repository.BookRepositoryImpl;

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
@WebServlet(urlPatterns = "/getAllBooks")
public class ViewAllBook extends HttpServlet {

    private static final BookRepository bookRepository = new BookRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
            List<Book> books  = bookRepository.getAll();
            req.setAttribute("books", books);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/html/book/getAllBook.jsp");
            requestDispatcher.forward(req, resp);
    }
}