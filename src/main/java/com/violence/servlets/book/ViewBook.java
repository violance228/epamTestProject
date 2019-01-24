package com.violence.servlets.book;

import com.violence.entity.Book;
import com.violence.repository.BookRepository;
import com.violence.repository.BookRepositoryImpl;
import com.violence.util.Utils;

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
@RequestScoped
@WebServlet(urlPatterns = "/getBook")
public class ViewBook extends HttpServlet {

    private BookRepository bookRepository = new BookRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
            Book book = bookRepository.getById(Utils.getLongParamFromReq(req, "book_id"));
            req.setAttribute("book", book);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/html/book/getBook.jsp");
            requestDispatcher.forward(req, resp);
    }
}