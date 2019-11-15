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

    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js"
            integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ"
            crossorigin="anonymous"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js"
            integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"
            integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ"
            crossorigin="anonymous"></script>
    <script src="js/bootstrap.min.js"></script>

    <style>
        <%@ include file ="css/sidebar.css"%>
        #sidebar {
            min-width: 250px;
            max-width: 250px;
            background: #6e3759;
            color: #cfcfcf;
            transition: all 0.3s;
        }
    </style>
</head>
<body>
<nav id="sidebar">
    <ul class="list-unstyled components">
        <p><fmt:message key="sidebar.menu"/> </p>
        <li>
            <a href="?command=expositionForm">Add Exposition</a>
        </li>
        <li>
            <a href="?command=showUsers&currentPage=1&rowCount=15">Show Users</a>
        </li>
        <li>
            <a href="?command=showTickets">View tickets</a>
        </li>
        <li>
            <a href="#">Contact</a>
        </li>
        <li>
            <a href="#">Another</a>
        </li>
    </ul>
</nav>
</body>
</html>