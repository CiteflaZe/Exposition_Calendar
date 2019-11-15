<%--
  Created by IntelliJ IDEA.
  User: CiteflaZe
  Date: 15-Nov-19
  Time: 12:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <title>Add Exposition</title>
</head>
<body>
<c:import url="header.jsp"/>
<div class="wrapper">
    <c:import url="admin-sidebar.jsp"/>
    <div id="content">
        <form action="admin">
            <input type="hidden" name="command" value="addExposition">
            <div class="col-sm-3 my-1">
                <label for="title">Title</label>
                <input name="title" type="text" class="form-control" id="title"  placeholder="Title">
            </div>
            <div class="col-sm-3 my-1">
                <label for="theme">Theme</label>
                <input name="theme" type="text" class="form-control" id="theme" placeholder="Theme">
            </div>
            <div class="col-sm-3 my-1">
                <label for="dateStart">Start Date</label>
                <input name="dateStart" type="text" id="dateStart" class="form-control">
            </div>
            <div class="col-sm-3 my-1">
                <label for="dateEnd">End Date</label>
                <input name="dateEnd" type="text" id="dateEnd" class="form-control">
            </div>
            <div class="col-sm-3 my-1">
                <label for="ticketPrice">Ticket Price</label>
                <input name="ticketPrice" type="number" step=".01" class="form-control" id="ticketPrice" placeholder="Ticket Price">
            </div>
            <div class="col-sm-3 my-1">
                <label for="description">Description</label>
                <textarea name="description" type="text" class="form-control" id="description" maxlength="500" rows="5"></textarea>
            </div>
            <div class="col-sm-3 my-1">
                <label for="hallId">Hall</label>
                <select name="hallId" id="hallId" class="custom-select custom-select-lg mb-3">
                    <option selected>Open this select menu</option>
                    <c:forEach begin="0" end="${halls.size()-1}" var="i">
                        <option value="${i+1}">${halls.get(i).getName()}</option>
                    </c:forEach>

                </select>
            </div>

            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</div>

<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>

<script>
    $(document).ready(function () {
        var date_input_start = $('input[name="dateStart"]');
        var container = $('.bootstrap-iso form').length > 0 ? $('.bootstrap-iso form').parent() : "body";
        date_input_start.datepicker({
            format: 'yyyy-mm-dd',
            container: container,
            todayHighlight: true,
            autoclose: true,
        });

        var date_input_end = $('input[name="dateEnd"]');
        var container = $('.bootstrap-iso form').length > 0 ? $('.bootstrap-iso form').parent() : "body";
        date_input_end.datepicker({
            format: 'yyyy-mm-dd',
            container: container,
            todayHighlight: true,
            autoclose: true,

        })
    })
</script>

</body>
</html>
