<%--
  Created by IntelliJ IDEA.
  User: CiteflaZe
  Date: 15-Nov-19
  Time: 4:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <script src="js/bootstrap.min.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>Halls</title>
</head>
<body>
<c:import url="header.jsp"/>
<div class="wrapper">
    <c:import url="admin-sidebar.jsp"/>
    <div id="content">
        <table class="table table-striped table-responsive-md btn-table">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="admin.showHalls.id"/></th>
                <th scope="col"><fmt:message key="admin.showHalls.name"/></th>
                <th scope="col"><fmt:message key="admin.showHalls.city"/></th>
                <th scope="col"><fmt:message key="admin.showHalls.street"/></th>
                <th scope="col"><fmt:message key="admin.showHalls.houseNumber"/></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="hall" items="${halls}">
                <tr>
                    <td>${hall.getId()}</td>
                    <td>${hall.getName()}</td>
                    <td>${hall.getCity()}</td>
                    <td>${hall.getStreet()}</td>
                    <td>${hall.getHouseNumber()}</td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>
</body>
</html>
