package com.violence.servlets.catalog;

import com.violence.entity.Catalog;
import com.violence.entity.User;
import com.violence.repository.CatalogRepository;
import com.violence.repository.CatalogRepositoryImpl;
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
import java.util.List;

@Named
@RequestScoped
@WebServlet(urlPatterns = "/getAllCatalogs")
public class GetAllCatalogs extends HttpServlet {

    private CatalogRepository catalogRepository = new CatalogRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (Utils.getUserRoleBySession(req).equals("admin")) {
            List<Catalog> catalogs = catalogRepository.getAll();
            req.setAttribute("catalogs", catalogs);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/html/catalog/getAllCatalogs.jsp");
            requestDispatcher.forward(req, resp);
        } else if (Utils.getUserIdBySession(req.getSession()) != 0) {
            List<Catalog> catalogs = catalogRepository.getAllCatalogByUserId(Utils.getUserIdBySession(req.getSession()));
            req.setAttribute("catalogs", catalogs);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/html/catalog/getAllCatalogs.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login");
            requestDispatcher.include(req, resp);
        }
    }
}
