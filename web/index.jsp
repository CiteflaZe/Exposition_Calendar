<%--
  Created by IntelliJ IDEA.
  User: CiteflaZe
  Date: 30-Oct-19
  Time: 8:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="text"/>

<html lang="param.locale">
  <head>
    <title>Front Page</title>
  </head>
  <body>

  <div class="copyright">
    <div class="container">
      <a href="?locale=en">en</a>
      <a href="?locale=ru">ru</a>
    </div>
  </div>

  <div>
    <fmt:message key="string"/>
  </div>

<%--  <div>--%>
<%--    <c:forEach var="email" items="${requestScope.emails}">--%>
<%--      <ul>--%>
<%--        <li>Email: <c:out value="${email}"/></li>--%>
<%--      </ul>--%>
<%--    </c:forEach>--%>

<%--    <c:forEach var="pass" items="${requestScope.passwords}">--%>
<%--      <ul>--%>
<%--        <li>Password: <c:out value="${pass}"/> </li>--%>
<%--      </ul>--%>
<%--    </c:forEach>--%>
<%--  </div>--%>

  <form method="post" action="<c:url value='/user'/>">
<%--    <label for="inputEmail">Email</label>--%>
<%--    <input type="email" id="inputEmail" placeholder= <fmt:message key="signIn.email"/> required autofocus name="email" value="">--%>
<%--    <label for="inputPassword">Password</label>--%>
<%--    <input type="password" id="inputPassword" placeholder= <fmt:message key="signIn.password"/> required autofocus name="password" value="">--%>
<%--    <button type="submit"><fmt:message key="signIn.submit"/></button>--%>
    <label><input type="email" name="email"></label><fmt:message key="signIn.email"/><br>
    <label><input type="password" name="password"></label><fmt:message key="signIn.password"/><br>
<%--    <input type="submit" value="<fmt:message key="signIn.submit"/>" name="Submit"/>--%>
    <button type="submit"><fmt:message key='signIn.submit'/></button>
  </form>

  <a href="<c:url value='/show'/>">Show users</a>
  <form action="<c:url value='/show'/>" method="post">
<%--    <c:set var="rowCount" scope="session" value="15"/>--%>
<%--    <c:set var="startFrom" scope="session" value="0"/>--%>
    <input type="hidden" name="rowCount" value="${15}">
    <input type="hidden" name="startFrom" value="${0}">
    <input type="submit" value="<fmt:message key='signIn.submit'/>">
  </form>

  </body>
</html>

