<%--
  Created by IntelliJ IDEA.
  User: CiteflaZe
  Date: 08-Nov-19
  Time: 9:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="text"/>
<html>
    <head>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

        <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Kaushan+Script" rel="stylesheet">
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet"/>
        <script src="js/script.js" type="text/javascript"></script>

        <title>Register</title>

<%--        <style>--%>
<%--            body{--%>
<%--                padding-top:4.2rem;--%>
<%--                padding-bottom:4.2rem;--%>
<%--                background:rgba(0, 0, 0, 0.76);--%>
<%--            }--%>
<%--            a{--%>
<%--                text-decoration:none !important;--%>
<%--            }--%>
<%--            h1,h2,h3{--%>
<%--                font-family: 'Kaushan Script', cursive;--%>
<%--            }--%>
<%--            .myform{--%>
<%--                position: relative;--%>
<%--                display: -ms-flexbox;--%>
<%--                display: flex;--%>
<%--                padding: 1rem;--%>
<%--                -ms-flex-direction: column;--%>
<%--                flex-direction: column;--%>
<%--                width: 100%;--%>
<%--                pointer-events: auto;--%>
<%--                background-color: #fff;--%>
<%--                background-clip: padding-box;--%>
<%--                border: 1px solid rgba(0,0,0,.2);--%>
<%--                border-radius: 1.1rem;--%>
<%--                outline: 0;--%>
<%--                max-width: 500px;--%>
<%--            }--%>
<%--            .tx-tfm{--%>
<%--                text-transform:uppercase;--%>
<%--            }--%>
<%--            .mybtn{--%>
<%--                border-radius:50px;--%>
<%--            }--%>
<%--        </style>--%>
    </head>
</html>


<body>
<div class="container">
    <div class="row">
        <div class="col-md-5 mx-auto">
            <div class="myform form ">
                <div class="logo mb-3">
                    <div class="col-md-12 text-center">
                        <h1>Signup</h1>
                    </div>
                </div>
                <form action="/user" name="registration" method="post">
                    <input type="hidden" name="command" value="register">
                    <div class="form-group">
                        <label for="name"><fmt:message key="register.name"/></label>
                        <input type="text"  name="name" class="form-control" id="name" aria-describedby="emailHelp">
                    </div>
                    <div class="form-group">
                        <label for="surname"><fmt:message key="register.surname"/></label>
                        <input type="text"  name="surname" class="form-control" id="surname" aria-describedby="emailHelp">
                    </div>
                    <div class="form-group">
                        <label for="email"><fmt:message key="register.email"/></label>
                        <input type="email" name="email"  class="form-control" id="email" aria-describedby="emailHelp">
                    </div>
                    <div class="form-group">
                        <label for="password"><fmt:message key="register.password"/></label>
                        <input type="password" name="password" id="password"  class="form-control" aria-describedby="emailHelp">
                    </div>
                    <div class="form-group">
                        <label for="passwordConfirmation"><fmt:message key="register.confirmPassword"/></label>
                        <input type="password" name="passwordConfirmation" id="passwordConfirmation"  class="form-control" aria-describedby="emailHelp">
                    </div>
                    <div class="col-md-12 text-center mb-3">
                        <button type="submit" class=" btn btn-block mybtn btn-primary tx-tfm"><fmt:message key="register.submit"/></button>
                    </div>
                    <div class="col-md-12 ">
                        <div class="form-group">
                            <p class="text-center"><a href="sign-in.jsp" id="signin"><fmt:message key="register.signIn.message"/></a></p>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
