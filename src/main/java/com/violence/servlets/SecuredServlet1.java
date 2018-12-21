package com.violence.servlets;

import com.violence.entity.Author;
import com.violence.entity.Catalog;
import com.violence.repository.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/test")
public class SecuredServlet1 extends HttpServlet{

    private AuthorRepository authorRepository = new AuthorRepositoryImpl();
    private CatalogRepository catalogRepository =  new CatalogRepositoryImpl();
    private UserRepository userRepository = new UserRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        Long start = System.currentTimeMillis();
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(8L, "Petro", "Grushevskiy", "UA"));
        authors.add(new Author(9L, "Ivan", "Franko", "UA"));
        authors.add(new Author(10L, "Lesya", "Ukrainka", "UA"));
        authorRepository.saveList(authors);
//        List<User> as = userRepository.getAll();
//        userRepository.getById(1l);
        List<Catalog> catalogs = catalogRepository.getAll();
        System.out.println(System.currentTimeMillis() - start);
        resp
                .getWriter()
                .write("<h1>Secured Servlet1</h1>");
    }
}
