package com.violence.servlets.catalog;

import com.violence.entity.Catalog;
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

/**
 * created by user violence
 * created on 15.01.2019
 * class created for project SecurityExample
 */

@Named
@RequestScoped
@WebServlet(urlPatterns = "/getCatalog")
public class GetCatalog extends HttpServlet {

    private CatalogRepository catalogRepository = new CatalogRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Catalog catalog = catalogRepository.getById(Utils.getLongParamFromReq(req, "catalog_id"));
        req.setAttribute("catalog", catalog);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/html/catalog/getCatalog.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Catalog catalog = catalogRepository.getById(Utils.getLongParamFromReq(req, "catalog_id"));
        req.setAttribute("catalog", catalog);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/editCatalog");
        requestDispatcher.include(req, resp);
    }
}
