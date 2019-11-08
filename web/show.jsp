<%--
  Created by IntelliJ IDEA.
  User: CiteflaZe
  Date: 06-Nov-19
  Time: 4:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->

    <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Kaushan+Script" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>

<a href="index.jsp">Home</a>



<div>
    <table class="table table-striped table-responsive-md btn-table">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Surname</th>
            <th scope="col">Email</th>
            <th scope="col">Role</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.email}</td>
                <td>${user.role.description}</td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

    <form action="<c:url value='/show'/>" method="post">
        <input type="hidden" name="rowCount" value="${rowCount}">
        <input type="hidden" name="startFrom" value="${startFrom}">
        <input type="submit" name="page" value="previous" class="btn btn-indigo btn-sm m-0">
        <input type="submit" name="page" value="next" class="btn btn-indigo btn-sm m-0">
    </form>

</div>
</body>
</html>
