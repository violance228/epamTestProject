<%--
  Created by IntelliJ IDEA.
  User: violence
  Date: 23.01.2019
  Time: 11:27
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
    <form action="/editCatalog" method="post">
        <tbody>
            <tr>
                <td>Catalog</td>
                <td><input type="text" value="${catalog.id}" name="id" readonly="readonly"/></td>
            </tr>
            <tr>
                <td>User</td>
                <td>
                    <select name="user">
                        <option value="${catalog.user.id}" selected="selected">${catalog.user.name} ${catalog.user.surname}</option>
                        <c:forEach items="${users}" var="user">
                            <option value="${user.id}">${user.name} ${user.surname}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Book</td>
                <td>
                    <select name="book">
                    <option value="${catalog.book.id}" selected="selected">${catalog.book.name}</option>
                    <c:forEach items="${books}" var="book">
                        <option value="${book.id}">${book.name}</option>
                    </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Date from</td>
                <td><input type="date" name="dateFrom" value="${catalog.dateFrom}"></td>
            </tr>
            <tr>
                <td>Date to</td>
                <td><input type="date" name="dateTo" value="${catalog.dateTo}"></td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="submit">
                </td>
            </tr>
        </tbody>
    </form>
</table>
</body>
</html>
