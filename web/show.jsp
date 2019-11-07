<%--
  Created by IntelliJ IDEA.
  User: CiteflaZe
  Date: 06-Nov-19
  Time: 4:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<a href="index.jsp">Home</a>
<div>
    <table>
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Surname</th>
            <th scope="col">Email</th>
            <th scope="col">Role</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.email}</td>
                <td>${user.role.description}</td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

    <form action="<c:url value='/show'/>" method="post">
        <input type="hidden" name="rowCount" value="${rowCount}">
        <input type="hidden" name="startFrom" value="${startFrom}">
        <input type="submit" name="page" value="previous">
        <input type="submit" name="page" value="next">
    </form>

</div>
</body>
</html>
