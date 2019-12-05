<%--
  Created by IntelliJ IDEA.
  User: CiteflaZe
  Date: 13-Nov-19
  Time: 11:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Kaushan+Script" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>Expositions</title>
</head>
<body>
<c:import url="header.jsp"/>
<div class="wrapper">
    <c:import url="user-sidebar.jsp"/>
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
                        <th scope="col" style="width:8%"></th>
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
                            <td>
                                <form action="user" method="post">
                                    <input type="hidden" name="command" value="processExposition"/>
                                    <input type="hidden" name="expositionId" value="${exposition.getId()}"/>
                                    <button type="submit" class="btn btn-success"><fmt:message key="user.showExpositions.order"/></button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <c:import url="user-pagination.jsp"/>
            </c:otherwise>
        </c:choose>
    </div>
</div>

</body>
</html>
