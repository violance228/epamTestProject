package com.violence.servlets;

import com.violence.entity.Author;
import com.violence.entity.Catalog;
import com.violence.entity.DomainObject;
import com.violence.entity.User;
import com.violence.repository.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
@WebServlet(urlPatterns = "/Test")
public class SecuredServlet1 extends HttpServlet{

    @Inject AuthorRepository authorRepository;

    private CatalogRepository catalogRepository =  new CatalogRepositoryImpl();
    private UserRepository userRepository = new UserRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        Long start = System.currentTimeMillis();
        userRepository.getById(1L);
        List<Author> authors = new ArrayList<>();
//        authorRepository.saveList(authors);
//        List<User> as = userRepository.getAll();
//        userRepository.getById(1l);
        List<Catalog> catalogs = catalogRepository.getAll();
        System.out.println(System.currentTimeMillis() - start);
        resp
                .getWriter()
                .write("<h1>Secured Servlet1</h1>");
    }
}
