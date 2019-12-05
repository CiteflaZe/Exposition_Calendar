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
                <label for="title"><fmt:message key="admin.addExposition.title"/></label>
                <input name="title" type="text" class="form-control" id="title"  placeholder="<fmt:message key="admin.addExposition.title"/>" required>
            </div>
            <div class="col-sm-3 my-1">
                <label for="theme"><fmt:message key="admin.addExposition.theme"/></label>
                <input name="theme" type="text" class="form-control" id="theme" placeholder="<fmt:message key="admin.addExposition.theme"/>" required>
            </div>
            <div class="col-sm-3 my-1">
                <label for="dateStart"><fmt:message key="admin.addExposition.startDate"/></label>
                <input name="dateStart" type="date" id="dateStart" class="form-control" required>
            </div>
            <div class="col-sm-3 my-1">
                <label for="dateEnd"><fmt:message key="admin.addExposition.endDate"/></label>
                <input name="dateEnd" type="date" id="dateEnd" class="form-control" required>
            </div>
            <div class="col-sm-3 my-1">
                <label for="ticketPrice"><fmt:message key="admin.addExposition.ticketPrice"/></label>
                <input name="ticketPrice" type="number" step=".01" class="form-control" id="ticketPrice" placeholder="<fmt:message key="admin.addExposition.ticketPrice"/>" required>
            </div>
            <div class="col-sm-3 my-1">
                <label for="description"><fmt:message key="admin.addExposition.description"/></label>
                <textarea name="description" type="text" class="form-control" id="description" maxlength="500" rows="5" required></textarea>
            </div>
            <div class="col-sm-3 my-1">
                <label for="hallId"><fmt:message key="admin.addExposition.hall"/></label>
                <select name="hallId" id="hallId" class="custom-select custom-select-lg mb-3">
                    <c:forEach begin="0" end="${halls.size()-1}" var="i">
                        <option value="${halls.get(i).getId()}">${halls.get(i).getName()}</option>
                    </c:forEach>

                </select>
            </div>

            <button type="submit" class="btn btn-primary"><fmt:message key="admin.submit"/></button>
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
