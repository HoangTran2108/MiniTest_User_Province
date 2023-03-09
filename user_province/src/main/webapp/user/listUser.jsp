<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 09/3/2023
  Time: 12:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User Management</title>
</head>
<body>
<center>
    <h1>User Management</h1>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Province</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.province.p_name}"/></td>
            </tr>
        </c:forEach>
    </table>
    <a href="/users?action=addUser">Add New User</a>
</div>
</body>
</html>