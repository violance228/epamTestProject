package com.violence.servlets.author;

import com.violence.entity.Author;
import com.violence.repository.AuthorRepository;
import com.violence.repository.AuthorRepositoryImpl;
import com.violence.util.Utils;
import com.violence.util.api.parser.ObjectParserFromReq;
import com.violence.util.api.parser.ObjectParserFromReqImpl;
import org.apache.log4j.Logger;

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

@Named
@PermitAll
@RequestScoped
@WebServlet(urlPatterns = "/addAuthor")
public class CreateAuthor extends HttpServlet {

    private static final Logger log = Logger.getLogger(CreateAuthor.class);

    private static final AuthorRepository authorRepository = new AuthorRepositoryImpl();
    private static final ObjectParserFromReq parser = new ObjectParserFromReqImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Utils.getUserRoleBySession(request).equals("admin")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/html/author/addAuthor.jsp");
            dispatcher.forward(request,response);
            log.info("user was went in addAuthor controller doGet, userId " + Utils.getUserIdBySession(request.getSession()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Utils.getUserRoleBySession(request).equals("admin")) {
            Author author = (Author) parser.getObjectFromRequest(request, Author.class);
            authorRepository.save(author);
            log.info("user" +  Utils.getUserIdBySession(request.getSession()) + "was save author doPost " + author.getSurname() );
            RequestDispatcher dispatcher = request.getRequestDispatcher("/html/author/addAuthor.jsp");
            dispatcher.forward(request, response);
        }
    }
}
