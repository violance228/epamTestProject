<%--
  Created by IntelliJ IDEA.
  User: violence
  Date: 23.01.2019
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table class="table">
    <tbody>
    <tr>
        <td>Catalog</td>
        <td>${catalog.id}</td>
    </tr>
    <tr>
        <td>User</td>
        <td>${catalog.user.name} ${catalog.user.surname}</td>
    </tr>
    <tr>
        <td>Book</td>
        <td>${catalog.book.name}</td>
    </tr>
    <tr>
        <td>Date from</td>
        <td>${catalog.dateFrom}</td>
    </tr>
    <tr>
        <td>Date to</td>
        <td>${catalog.dateTo}</td>
    </tr>
    </tbody>
</table>
</body>
</html>
