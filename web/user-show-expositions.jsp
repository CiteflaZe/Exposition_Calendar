<%--
  Created by IntelliJ IDEA.
  User: CiteflaZe
  Date: 13-Nov-19
  Time: 11:46 AM
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
    <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Kaushan+Script" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

    <title>Expositions</title>
</head>
<body>
<c:import url="header.jsp"/>
<div class="wrapper">
    <c:import url="user-sidebar.jsp"/>
    <div id="content">
        <table class="table table-striped table-responsive-md btn-table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Title</th>
                <th scope="col">Theme</th>
                <th scope="col">Starts From</th>
                <th scope="col">Ends On</th>
                <th scope="col">Ticket Price</th>
                <th scope="col" style="width:30%">Description</th>
                <th scope="col">Hall</th>
                <th scope="col" style="width:8%"></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach begin="0" end="${expositions.size()-1}" var="i">
                <tr>
                    <td>${expositions.get(i).getTitle()}</td>
                    <td>${expositions.get(i).getTheme()}</td>
                    <td>${expositions.get(i).getStartTime()}</td>
                    <td>${expositions.get(i).getFinishTime()}</td>
                    <td>${expositions.get(i).getTicketPrice()}</td>
                    <td>${expositions.get(i).getDescription()}</td>
                    <td>${expositions.get(i).getHall().getName()}</td>
                    <td>
                        <form action="user" method="post">
                            <input type="hidden" name="command" value="processExposition"/>
                            <input type="hidden" name="exposition" value="${i}"/>
                            <button type="submit" class="btn btn-success">Place order</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>

</body>
</html>
