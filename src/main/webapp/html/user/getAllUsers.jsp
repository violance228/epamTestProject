<%--
  Created by IntelliJ IDEA.
  User: s3809
  Date: 1/14/2019
  Time: 8:28 PM
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
        <td>Login</td>
        <td>Name</td>
        <td>Surname</td>
        <td>Email</td>
        <td>Phone</td>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="author">
        <tr>
            <td><a href="/viewUser?user_id=${author.id}">${author.login}</a></td>
            <td>${author.name}</td>
            <td>${author.surname}</td>
            <td>${author.email}</td>
            <td>${author.phone}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div>
    <%@include file="../fragments/footer.jspf"%>
</div>
</body>
</html>
