package com.violence.servlets.catalog;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.annotation.WebServlet;

@Named
@RequestScoped
@WebServlet(urlPatterns = "/editCatalog")
public class EditCatalog {
}
