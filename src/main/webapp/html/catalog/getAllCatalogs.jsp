<%--
  Created by IntelliJ IDEA.
  User: s3809
  Date: 1/14/2019
  Time: 8:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="../fragments/header.jspf"%>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <%@include file="../fragments/navbar.jspf"%>
</nav>
<table class="table">
    <thead class="thead-dark">
        <td>catalog</td>
        <td>login</td>
        <td>book</td>
        <td>date from</td>
        <td>date to</td>
    </thead>
    <tbody>
        <c:forEach items="${catalogs}" var="catalog">
            <tr>
                <td><a href="/getCatalog?catalog_id=${catalog.id}">${catalog.id}</a></td>
                <td><a href="/viewUser?user_id=${catalog.user.id}">${catalog.user.login}</a></td>
                <td><a href="/getBook?book_id=${catalog.book.id}">${catalog.book.name}</a></td>
                <td>${catalog.dateFrom}</td>
                <td>${catalog.dateTo}</td>
            </tr>
        </c:forEach>
        <tr>
            <td><a href="/getAllCatalogs?offset=${current-1}">prev</a></td>
            <td>${current}</td>
            <td><a href="/getAllCatalogs?limit=20&offset=${current+1}">next</a></td>
        </tr>
    </tbody>
</table>
</body>
</html>
