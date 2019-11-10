<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="text"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <script src="js/bootstrap.min.js"></script>

<%--    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/cover/">--%>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/cover.css" rel="stylesheet">

    <title>Exposition Calendar</title>

<%--    <style>--%>
<%--        a,--%>
<%--        a:focus,--%>
<%--        a:hover {--%>
<%--            color: #fff;--%>
<%--        }--%>

<%--        .btn-secondary,--%>
<%--        .btn-secondary:hover,--%>
<%--        .btn-secondary:focus {--%>
<%--            color: #333;--%>
<%--            text-shadow: none; /* Prevent inheritance from `body` */--%>
<%--            background-color: #fff;--%>
<%--            border: .05rem solid #fff;--%>
<%--        }--%>
<%--        html,--%>
<%--        body {--%>
<%--            height: 100%;--%>
<%--            background-color: #333;--%>
<%--        }--%>

<%--        body {--%>
<%--            display: -ms-flexbox;--%>
<%--            display: -webkit-box;--%>
<%--            display: flex;--%>
<%--            -ms-flex-pack: center;--%>
<%--            -webkit-box-pack: center;--%>
<%--            justify-content: center;--%>
<%--            color: #fff;--%>
<%--            text-shadow: 0 .05rem .1rem rgba(0, 0, 0, .5);--%>
<%--            box-shadow: inset 0 0 5rem rgba(0, 0, 0, .5);--%>
<%--        }--%>

<%--        .cover-container {--%>
<%--            max-width: 42em;--%>
<%--        }--%>

<%--        .masthead {--%>
<%--            margin-bottom: 2rem;--%>
<%--        }--%>

<%--        .masthead-brand {--%>
<%--            margin-bottom: 0;--%>
<%--        }--%>

<%--        .nav-masthead .nav-link {--%>
<%--            padding: .25rem 0;--%>
<%--            font-weight: 700;--%>
<%--            color: rgba(255, 255, 255, .5);--%>
<%--            background-color: transparent;--%>
<%--            border-bottom: .25rem solid transparent;--%>
<%--        }--%>

<%--        .nav-masthead .nav-link:hover,--%>
<%--        .nav-masthead .nav-link:focus {--%>
<%--            border-bottom-color: rgba(255, 255, 255, .25);--%>
<%--        }--%>

<%--        .nav-masthead .nav-link + .nav-link {--%>
<%--            margin-left: 1rem;--%>
<%--        }--%>

<%--        .nav-masthead .active {--%>
<%--            color: #fff;--%>
<%--            border-bottom-color: #fff;--%>
<%--        }--%>

<%--        @media (min-width: 48em) {--%>
<%--            .masthead-brand {--%>
<%--                float: left;--%>
<%--            }--%>
<%--            .nav-masthead {--%>
<%--                float: right;--%>
<%--            }--%>
<%--        }--%>

<%--        .cover {--%>
<%--            padding: 0 1.5rem;--%>
<%--        }--%>
<%--        .cover .btn-lg {--%>
<%--            padding: .75rem 1.25rem;--%>
<%--            font-weight: 700;--%>
<%--        }--%>

<%--        .mastfoot {--%>
<%--            color: rgba(255, 255, 255, .5);--%>
<%--        }--%>
<%--    </style>--%>
</head>

<body class="text-center">
<div class="cover-container d-flex h-100 p-3 mx-auto flex-column">
    <header class="masthead mb-auto">
        <div class="inner">
            <h3 class="masthead-brand"><fmt:message key="index.headerName"/></h3>
            <nav class="nav nav-masthead justify-content-center">
                <a class="nav-link" href="?locale=en">English</a>
                <a class="nav-link" href="?locale=ru">Русский</a>
                <a class="nav-link" href="<c:url value="/sign-in.jsp"/>"><fmt:message key="signIn.submit"/></a>
            </nav>
        </div>
    </header>
    <main role="main" class="inner cover">
        <h1 class="cover-heading"><fmt:message key="index.brandName"/></h1>
        <p class="lead"><fmt:message key="index.text"/> </p>
        <p class="lead">
            <a href="register.jsp" class="btn btn-lg btn-secondary"><fmt:message key="index.register"/> </a>
        </p>
    </main>

    <footer class="mastfoot mt-auto">
        <div class="inner">
            <p>Cover template for <a href="https://getbootstrap.com/">Bootstrap</a>, by <a href="https://twitter.com/mdo">@mdo</a>.</p>
        </div>
    </footer>
</div>

</body>
</html>
