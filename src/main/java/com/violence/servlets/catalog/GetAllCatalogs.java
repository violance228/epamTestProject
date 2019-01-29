package com.violence.servlets.catalog;

import com.violence.entity.Catalog;
import com.violence.repository.CatalogRepository;
import com.violence.repository.CatalogRepositoryImpl;
import com.violence.util.Utils;
import org.apache.log4j.Logger;

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

    private static final Logger logger = Logger.getLogger(GetAllCatalogs.class);
    private static final CatalogRepository catalogRepository = new CatalogRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        int size = catalogRepository.getSize();
        int pages = (int) Math.ceil(size / 5);
        int offset = req.getParameter("offset") != null && Integer.valueOf(req.getParameter("offset"))-1 <= pages
                && Integer.valueOf(req.getParameter("offset"))-1 > 0
                ? Integer.valueOf(req.getParameter("offset"))-1 : 0;
        try {

            if (Utils.getUserRoleBySession(req).equals("admin")) {
                List<Catalog> catalogs = catalogRepository.getAll(5, offset * 5);
                req.setAttribute("catalogs", catalogs);
                req.setAttribute("current", offset + 1);
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
        } catch (ServletException | IOException e) {
            logger.info("can't view allCatalog " + e.getMessage());
        }
    }
}
