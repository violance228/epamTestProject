<%--
  Created by IntelliJ IDEA.
  User: violence
  Date: 23.01.2019
  Time: 11:26
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
<form action="/addCatalog" method="post">
    <table class="table">
        <tbody>
        <tr>
            <td>User</td>
            <td>
                <select name="user">
                    <c:forEach items="${users}" var="author">
                        <option value="${author.id}">${author.name} ${author.surname}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Book</td>
            <td>
                <select name="book">
                    <c:forEach items="${books}" var="book">
                        <option value="${book.id}">${book.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>From date</td>
            <td><input type="date" name="dateFrom"/></td>
        </tr>
        <tr>
            <td>For date</td>
            <td><input type="date" name="dateTo"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="add"/></td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>
