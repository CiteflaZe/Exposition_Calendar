<%--
  Created by IntelliJ IDEA.
  User: CiteflaZe
  Date: 06-Nov-19
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <input type="text" value="<c:out value='${user.email}'/>">
</body>
</html>
