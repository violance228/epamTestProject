package com.violence.servlets.catalog;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.annotation.WebServlet;

/**
 * created by user violence
 * created on 23.01.2019
 * class created for project SecurityExample
 */

@Named
@RequestScoped
@WebServlet(urlPatterns = "/editCatalog")
public class EditCatalog {
}
