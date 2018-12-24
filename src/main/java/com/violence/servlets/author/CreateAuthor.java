package com.violence.servlets.author;

import com.violence.entity.Author;
import com.violence.repository.AuthorRepository;
import com.violence.repository.AuthorRepositoryImpl;
import com.violence.util.api.parser.ObjectParserFromReq;
import com.violence.util.api.parser.ObjectParserFromReqImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/createAuthor")
public class CreateAuthor extends HttpServlet {

    private AuthorRepository authorRepository = new AuthorRepositoryImpl();
    private ObjectParserFromReq parser = new ObjectParserFromReqImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Author author = (Author) parser.getObjectFromRequest(request, Author.class);
        authorRepository.save(author);
    }
}
