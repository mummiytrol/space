<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24.04.2020
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/resources/bootstrap-4.3.1-dist/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap-4.3.1-dist/js/jq.js" rel="stylesheet">
    <title>Edit</title>
</head>
<body>

  <c:url value="/add" var="var"/>

<form action="${var}" method="POST">
    <input type="hidden" name="id" value="${ship.id}">

<label for="name">Name</label>
<input type="text" name="name" id="name">
<label for="prodDate">prodDate</label>
<input type="text" name="prodDate" id="prodDate">
<label for="speed">Speed</label>
<input type="text" name="speed" id="speed">
<label for="crewSize">crewSize</label>
<input type="text" name="crewSize" id="crewSize">
    <input type="submit" value="Add new film">
</form>
</body>
</html>
