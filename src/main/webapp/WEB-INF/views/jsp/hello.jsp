<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="UTF-8"  contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyFeed</title>
</head>
<body align="CENTER">
<div>
    <img src="<c:url value='/resources/images/hello.png' />" class="logo" >
</div>
<h3>Запрошуємо Вас до стрічки новин</h3>
<a href="/items/allItems">Список свіжих публікацій</a>
</body>
</html>
