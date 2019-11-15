<%--
  Created by IntelliJ IDEA.
  User: CiteflaZe
  Date: 11-Nov-19
  Time: 1:53 AM
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
    <title>Welcome to Admin page</title>
</head>
<body>
<c:import url="header.jsp"/>
<div class="wrapper">
    <c:import url="admin-sidebar.jsp"/>
    <div id="content">
        <h2><fmt:message key="user-page.welcome"/> <c:out value="${sessionScope.user.getName()}"/> <c:out value="${sessionScope.user.getSurname()}"/></h2>
        <p><fmt:message key="user-page.message"/></p>
    </div>
</div>
</body>
</html>
