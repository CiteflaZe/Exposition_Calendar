<%--
  Created by IntelliJ IDEA.
  User: CiteflaZe
  Date: 14-Nov-19
  Time: 8:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>Title</title>
</head>
<body>
<c:import url="header.jsp"/>
<div class="wrapper">
    <c:import url="user-sidebar.jsp"/>
    <div id="content">
        <table class="table table-striped table-responsive-md btn-table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Exposition Title</th>
                <th scope="col">Hall</th>
                <th scope="col">Ticket Amount</th>
                <th scope="col">Placeholder</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach begin="0" end="${tickets.size()-1}" var="i">
                <tr>
                    <td>${tickets.get(i).getExposition().getTitle()}</td>
                    <td>${tickets.get(i).getHall().getName()}</td>
                    <td>${ticketAmount.get(i)}</td>
                    <td>Will be user soon</td>

                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>
</body>
</html>
