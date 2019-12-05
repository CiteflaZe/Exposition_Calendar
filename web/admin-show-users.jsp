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

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link href="https://fonts.googleapis.com/css?family=Kaushan+Script" rel="stylesheet">
    <title>Users</title>
</head>
<body>
<c:import url="header.jsp"/>
<div class="wrapper">
    <c:import url="admin-sidebar.jsp"/>
    <div id="content">
        <c:choose>
            <c:when test="${users.isEmpty()}">
                <h2><fmt:message key="list.empty"/></h2>
            </c:when>
            <c:otherwise>
                <table class="table table-striped table-responsive-md btn-table">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col"><fmt:message key="admin.showUsers.id"/></th>
                        <th scope="col"><fmt:message key="admin.showUsers.name"/></th>
                        <th scope="col"><fmt:message key="admin.showUsers.surname"/></th>
                        <th scope="col"><fmt:message key="admin.showUsers.email"/></th>
                        <th scope="col"><fmt:message key="admin.showUsers.role"/></th>
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
                <c:import url="admin-pagination.jsp"/>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
