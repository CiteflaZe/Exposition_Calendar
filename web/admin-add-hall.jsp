<%--
  Created by IntelliJ IDEA.
  User: CiteflaZe
  Date: 15-Nov-19
  Time: 3:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
                <label for="name">Name</label>
                <input name="name" type="text" class="form-control" id="name"  placeholder="Name">
            </div>
            <div class="col-sm-3 my-1">
                <label for="city">City</label>
                <input name="city" type="text" class="form-control" id="city" placeholder="City">
            </div>
            <div class="col-sm-3 my-1">
                <label for="street">Street</label>
                <input name="street" type="text" id="street" class="form-control" placeholder="Street">
            </div>
            <div class="col-sm-3 my-1">
                <label for="houseNumber">House Number</label>
                <input name="houseNumber" type="number" min="1" id="houseNumber" class="form-control" >
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</div>

</body>
</html>
