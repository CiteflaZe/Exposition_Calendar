
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Header</title>
</head>
<body>
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="index.jsp">Header name</a>
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <form action="user" method="post">
                <input type="hidden" name="command" value="signOut"/>
                <button type="submit" class="btn btn-primary btn-block">Sign Out</button>
            </form>
        </li>
    </ul>
</nav>
</body>
</html>