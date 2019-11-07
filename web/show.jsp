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

<c:set var="totalCount" value="${listSize}"/>
<c:set var="perPage" scope="session"  value="${5}"/>
<c:set var="pageStart" value="${param.start}"/>
<c:if test="${empty pageStart or pageStart < 0}">
    <c:set var="pageStart" value="0"/>
</c:if>
<c:if test="${totalCount < pageStart}">
    <c:set var="pageStart" value="${pageStart - perPage}"/>
</c:if>
<a href="?start=${pageStart - perPage}"><<</a>${pageStart + 1} - ${pageStart + perPage}
<a href="?start=${pageStart + perPage}">>></a>

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
        <c:forEach var="user" items="${users}" begin="${pageStart}" end="${pageStart + perPage - 1}">
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
</div>
</body>
</html>
