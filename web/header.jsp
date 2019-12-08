<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="rep" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title>Header</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #9ca0fd;">
    <div class="navbar-nav mr-auto">
        <a class="navbar-brand mx-auto" href="<c:url value="/"/>">Exposition</a>
    </div>
    <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
        <ul class="navbar-nav ml-auto">
            <a class="nav-link active" href="<rep:replaceParam name="locale" value="en"/>">English</a>
            <a class="nav-link active" href="<rep:replaceParam name="locale" value="ru"/>">Русский</a>
            <form class="form-inline my-2 my-lg-0" action="logout" method="post">
                <input class="form-control mr-sm-2" type="hidden" name="command" value="logout">
                <button class="btn btn-dark" type="submit"><fmt:message key="header.logout"/></button>
            </form>
        </ul>

    </div>
</nav>
</body>
</html>
