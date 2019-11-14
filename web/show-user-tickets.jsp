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
<%--    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">--%>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
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
                <th scope="col">Date</th>
                <th scope="col">Download</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach begin="0" end="${tickets.size()-1}" var="i">
                <tr>
                    <td>${tickets.get(i).getExposition().getTitle()}</td>
                    <td>${tickets.get(i).getHall().getName()}</td>
                    <td>${ticketAmount.get(i)}</td>
                    <td>${tickets.get(i).getValidDate()}</td>
                    <td>
                        <form action="user" method="get">
                            <input type="hidden" name="command" value="download"/>
                            <input type="hidden" name="paymentId" value="${tickets.get(i).getPayment().getId()}"/>
                            <button type="submit" class="btn btn-success"><i class="fa fa-download" aria-hidden="true"></i></button>
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
