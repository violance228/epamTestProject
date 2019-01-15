<%--
  Created by IntelliJ IDEA.
  User: s3809
  Date: 1/14/2019
  Time: 8:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="2px">
    <tr>
        <td>catalog</td>
        <td>login</td>
        <td>book</td>
        <td>date from</td>
        <td>date to</td>
    </tr>
    <c:forEach items="${catalogs}" var="catalog">
        <tr>
            <td>${catalog.id}</td>
            <td>${catalog.user.login}</td>
            <td>${catalog.book.name}</td>
            <td>${catalog.dateFrom}</td>
            <td>${catalog.dateTo}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
