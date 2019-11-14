<%--
  Created by IntelliJ IDEA.
  User: CiteflaZe
  Date: 12-Nov-19
  Time: 10:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Fredoka+One" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Raleway:400,700" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="css/error404.css" />
    <title>404 - Not Found</title>
</head>
<body>
<div id="notfound">
    <div class="notfound">
        <div class="notfound-404">
            <h1><fmt:message key="error.text"/></h1>
        </div>
        <h2><fmt:message key="error.message"/></h2>
        <a href="/"><span class="arrow"></span><fmt:message key="error.homepage"/></a>
    </div>
</div>
</body>
</html>
