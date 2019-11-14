
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
        <!------ Include the above in your HEAD tag ---------->

        <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Kaushan+Script" rel="stylesheet">
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style.css" type="text/css"/>
        <script src="js/script.js" type="text/javascript"></script>
        <title>Login</title>

    </head>

    <body>
    <div class="container">
        <div class="row">
            <div class="col-md-5 mx-auto">
                <div class="myform form ">
                    <div class="logo mb-3">
                        <div class="col-md-12 text-center">
                            <h1>Login</h1>
                        </div>
                    </div>
                    <form action="user" method="post" name="login">
                        <input type="hidden" name="command" value="login">
                        <div class="form-group">
                            <label for="email"><fmt:message key="signIn.email"/></label>
                            <input type="email" name="email" id="email" class="form-control" aria-describedby="emailHelp" placeholder="Enter email">
                        </div>
                        <div class="form-group">
                            <label for="password"><fmt:message key="signIn.password"/></label>
                            <input type="password" name="password" id="password"  class="form-control" aria-describedby="emailHelp" placeholder="Enter Password">
                            <div class="form-group">
                                <p class="text-center"><fmt:message key="signIn.message"/></p>
                            </div>
                            <button type="submit" class=" btn btn-block mybtn btn-primary tx-tfm"><fmt:message key="signIn.submit"/></button>
                        </div>

                        <div class="form-group">
                            <p class="text-center"><fmt:message key="signIn.register.message"/> <a href="register.jsp" id="signup"><fmt:message key="signIn.register.link"/></a></p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    </body>

</html>
