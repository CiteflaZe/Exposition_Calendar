<%--
  Created by IntelliJ IDEA.
  User: CiteflaZe
  Date: 15-Nov-19
  Time: 3:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <link href="css/sidebar.css" rel="stylesheet"/>
    <link href="css/bootstrap.min.css" rel="stylesheet"/>
    <title>Expositions</title>
</head>
<body>
<c:import url="header.jsp"/>
<div class="wrapper">
    <c:import url="admin-sidebar.jsp"/>
    <div id="content">
        <c:choose>
            <c:when test="${expositions.isEmpty()}">
                <h2><fmt:message key="list.empty"/></h2>
            </c:when>
            <c:otherwise>
                <table class="table table-striped table-responsive-md btn-table">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col"><fmt:message key="user.showExpositions.title"/></th>
                        <th scope="col"><fmt:message key="user.showExpositions.theme"/></th>
                        <th scope="col"><fmt:message key="user.showExpositions.start"/></th>
                        <th scope="col"><fmt:message key="user.showExpositions.end"/></th>
                        <th scope="col"><fmt:message key="user.showExpositions.ticketPrice"/></th>
                        <th scope="col" style="width:30%"><fmt:message key="user.showExpositions.description"/></th>
                        <th scope="col"><fmt:message key="user.showExpositions.hall"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="exposition" items="${expositions}">
                        <tr>
                            <td>${exposition.getTitle()}</td>
                            <td>${exposition.getTheme()}</td>
                            <td>${exposition.getStartDate()}</td>
                            <td>${exposition.getEndDate()}</td>
                            <td>${exposition.getTicketPrice()}</td>
                            <td>${exposition.getDescription()}</td>
                            <td>${exposition.getHall().getName()}</td>
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
