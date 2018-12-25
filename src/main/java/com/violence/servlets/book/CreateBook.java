package com.violence.servlets.book;

import com.violence.entity.Book;
import com.violence.repository.BookRepository;
import com.violence.repository.BookRepositoryImpl;
import com.violence.util.api.parser.ObjectParserFromReq;
import com.violence.util.api.parser.ObjectParserFromReqImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(urlPatterns = "createBook")
public class CreateBook extends HttpServlet {

    private BookRepository bookRepository = new BookRepositoryImpl();
    private ObjectParserFromReq parser = new ObjectParserFromReqImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Book book = (Book) parser.getObjectFromRequest(request, Book.class);
        bookRepository.save(book);
    }
}
