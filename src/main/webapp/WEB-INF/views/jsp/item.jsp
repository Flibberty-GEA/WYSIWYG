<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="java.time.LocalDate" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp">
    <jsp:param name="activePage" value="ALL"/>
</jsp:include>

<div class="container">
    <ol class="breadcrumb">
        <li><a href="/items/allItems">Усі публікації</a></li>
    </ol>

    <div class="row">
        <h2 id="my-css-title">Зміст</h2>
    </div>

    <form:form modelAttribute="item" action="/items/items/${item.id}">
    <c:choose>
        <c:when test="${param.readonly}">
                <div class="panel panel-info">
                    <div class="panel-heading" id="my-css-news-title">
                        <h3 class="panel-title">${item.title}</h3>
                    </div>
                    <div class="panel-body">
                            ${item.content}
                    </div>
                </div>
        </c:when>
        <c:otherwise>
            <div class="form-group">
                <div class="row">
                    <div class="col-md-6">
                        <label for="itemTitle">Назва</label>
                        <form:input type="text" path="title" class="form-control" id="itemTitle"
                                    placeholder="Write title of item" readonly="${param.readonly}"/>
                    </div>
                    <%--<c:choose>--%>
                        <%--<c:when test="${param.readonly}">--%>
                            <%--<div class="col-md-6">--%>
                                <%--<label for="itemTitle">Автор</label>--%>
                                <%--<form:input type="text" path="user.username" class="form-control" id="itemTitle"--%>
                                            <%--placeholder="Write author name" readonly="${param.readonly}"/>--%>
                            <%--</div>--%>
                        <%--</c:when>--%>
                    <%--</c:choose>--%>
                </div>
            </div>

            <%--<div class="form-group">--%>
                <%--<div class="row">--%>
                    <%--<div class="col-md-12">--%>
                        <%--<label for="itemTitle">Короткий зміст</label>--%>
                        <%--<form:input type="text" path="summary" class="form-control" id="itemTitle"--%>
                                    <%--placeholder="Write some summary" readonly="${param.readonly}"/>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <div class="form-group">
                <div class="row">
                    <div class="col-md-12">
                        <label for="itemTitle">Зміст</label>
                        <form:input type="textarea" path="content" class="form-control" id="itemTitle"
                                    placeholder="Write some content" readonly="${param.readonly}"/>
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>



    <div class="row">
            <c:choose>
                <c:when test="${param.readonly}">
                        <a href="/items/edit/${item.id}" class="btn btn-danger">Редагувати</a>
                </c:when>
                <c:otherwise>
                    <input type="submit" class="btn btn-primary" value="Зберігти зміни"/>
                </c:otherwise>
            </c:choose>
    </div>
    </form:form>
</div>