<%--
  Created by IntelliJ IDEA.
  User: CiteflaZe
  Date: 15-Nov-19
  Time: 3:57 PM
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
    <title>Add Hall</title>
</head>
<body>
<c:import url="header.jsp"/>
<div class="wrapper">
    <c:import url="admin-sidebar.jsp"/>
    <div id="content">
        <form action="admin">
            <input type="hidden" name="command" value="addHall">
            <div class="col-sm-3 my-1">
                <label for="name"><fmt:message key="admin.addHall.name"/></label>
                <input name="name" type="text" class="form-control" id="name"  placeholder="<fmt:message key="admin.addHall.name"/>">
            </div>
            <div class="col-sm-3 my-1">
                <label for="city"><fmt:message key="admin.addHall.city"/></label>
                <input name="city" type="text" class="form-control" id="city" placeholder="<fmt:message key="admin.addHall.city"/>">
            </div>
            <div class="col-sm-3 my-1">
                <label for="street"><fmt:message key="admin.addHall.street"/></label>
                <input name="street" type="text" id="street" class="form-control" placeholder="<fmt:message key="admin.addHall.street"/>">
            </div>
            <div class="col-sm-3 my-1">
                <label for="houseNumber"><fmt:message key="admin.addHall.houseNumber"/></label>
                <input name="houseNumber" type="number" min="1" id="houseNumber" class="form-control" >
            </div>
            <button type="submit" class="btn btn-primary"><fmt:message key="admin.submit"/></button>
        </form>
    </div>
</div>

</body>
</html>
