package com.violence.servlets.catalog;

import com.violence.entity.Catalog;
import com.violence.entity.User;
import com.violence.repository.CatalogRepository;
import com.violence.repository.CatalogRepositoryImpl;

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

/**
 * created by user violence
 * created on 15.01.2019
 * class created for project SecurityExample
 */

@Named
@RequestScoped
@WebServlet(urlPatterns = "/getAllCatalogs")
public class getAllCatalogs extends HttpServlet {

    private CatalogRepository catalogRepository = new CatalogRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<Catalog> catalogs = catalogRepository.getAll();
        req.setAttribute("catalogs", catalogs);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/html/getAllCatalogs.jsp");
        requestDispatcher.forward(req, resp);
    }
}
