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
<form action="/createBook" method="post">
    <table class="table">
        <tbody>
            <tr>
                <td>Author/s</td>
                <td>
                    <select name="authors" multiple="multiple">
                        <c:forEach items="${authors}" var="author">
                            <option value="${author.id}">${author.name} ${author.surname}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Book name</td>
                <td><input type="text" name="name"/></td>
            </tr>
            <tr>
                <td>Language</td>
                <td><input type="text" name="lang"/></td>
            </tr>
            <tr>
                <td>Size</td>
                <td><input type="number" name="size"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="add"/></td>
            </tr>
        </tbody>
    </table>
</form>
</body>
</html>
