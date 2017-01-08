<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="ua.flibberty.wysiwyg.domain.item.Item" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Нові публікації</title>
    <jsp:include page="../includes.jsp"/>
</head>
<body>
<jsp:include page="../header.jsp">
    <jsp:param name="activePage" value="RECOMMEND"/>
</jsp:include>

<div class="container">

    <div class="row">
        <h2 id="my-css-title">Нові публікації</h2>
    </div>

    <div>

        <c:forEach var="item" items="${items}">
            <div class="panel panel-info">
                <div class="panel-heading" id="my-css-news-title">
                    <h3 class="panel-title"><a href="/items/${item.id}"> ${item.title} </a></h3>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
