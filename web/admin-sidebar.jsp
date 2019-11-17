<%--
  Created by IntelliJ IDEA.
  User: CiteflaZe
  Date: 15-Nov-19
  Time: 12:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title>User side bar</title>

    <link href="css/sidebar.css" rel="stylesheet"/>
    <link href="css/bootstrap.min.css" rel="stylesheet"/>



    <style>
        <%@ include file ="css/sidebar.css"%>
        #sidebar {
            min-width: 250px;
            max-width: 250px;
            background: #120d1a;
            color: #e2e2e2;
            transition: all 0.3s;
        }
    </style>
</head>
<body>
<nav id="sidebar">
    <ul class="list-unstyled components">
        <p><fmt:message key="sidebar.menu"/> </p>
        <li>
            <a href="?command=expositionForm"><fmt:message key="admin.sidebar.addExposition"/></a>
        </li>
        <li>
            <a href="?command=hallForm"><fmt:message key="admin.sidebar.addHall"/></a>
        </li>
        <li>
            <a href="?command=showUsers&currentPage=1&rowCount=15"><fmt:message key="admin.sidebar.showUsers"/></a>
        </li>
        <li>
            <a href="?command=showHalls&currentPage=1&rowCount=15"><fmt:message key="admin.sidebar.showHalls"/></a>
        </li>
        <li>
            <a href="#"><fmt:message key="admin.sidebar.showExpositions"/></a>
        </li>
    </ul>
</nav>
</body>
</html>