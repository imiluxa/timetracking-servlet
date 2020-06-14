<!DOCTYPE HTML>
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="messages" />

<head>
    <meta http-equiv="Content-Type" content="text/html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>


<body>
<%@include file="header.jspf"%>
<div class="container">
    <div class="row">
        <div class="col-md-14">
            <div class="card">
                <div class="card-header">
                    <h1 class="display-3"><fmt:message key="add.activities.title"/></h1>
                    <hr>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-8">
                            <form method="post" action="update_activity" id="form_change">
                                <input type="hidden"
                                       name="activity.id"
                                       form="form_change"
                                       value="${requestScope.activity.getIdactivity()}"
                                >
                                <c:if test="${role eq 'ADMIN'}">
                                    <div class="form-group col-md-8">
                                        <label class="col-form-label" for="activity.name">
                                            <fmt:message key="add.activities.name"/>
                                        </label>
                                        <input class="form-control"
                                                id="activity.name"
                                                name="activity.name"
                                                value="${requestScope.activity.getName()}"
                                                placeholder="<fmt:message key="add.activities.name.placeholder"/>"
                                                type="text"
                                                required>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label class="col-form-label" for="activity.goal">
                                            <fmt:message key="add.activities.goal"/>
                                        </label>
                                        <input class="form-control"
                                               id="activity.goal"
                                               name="activity.goal"
                                               value="${requestScope.activity.getGoal()}"
                                               placeholder="<fmt:message key="add.activities.goal.placeholder"/>"
                                               type="text"
                                               required>
                                    </div>
                                </c:if>
                                <c:if test="${role eq 'USER'}">
                                    <div class="form-group col-md-8">
                                        <label class="col-form-label" for="activity.name.user">
                                            <fmt:message key="add.activities.name"/>
                                        </label>
                                        <input type="text"
                                               class="form-control"
                                               id="activity.name.user"
                                               name="activity.name"
                                               value="${requestScope.activity.getName()}"
                                               disabled>
                                    </div>
                                    <div class="form-group col-md-8">
                                        <label class="col-form-label" for="activity.goal.user">
                                            <fmt:message key="add.activities.goal"/>
                                        </label>
                                        <input type="text"
                                               class="form-control"
                                               id="activity.goal.user"
                                               name="activity.goal"
                                               value="${requestScope.activity.getGoal()}"
                                               disabled>
                                    </div>
                                </c:if>
                                <div class="form-group col-md-8">
                                    <label class="col-form-label" for="activity.duration">
                                        <fmt:message key="add.activities.duration"/>
                                    </label>
                                    <input class="form-control"
                                           id="activity.duration"
                                           name="activity.duration"
                                           value="${requestScope.activity.getDuration()}"
                                           placeholder="<fmt:message key="add.activities.duration.placeholder"/>"
                                           type="number">
                                </div>
                                <div class="input-group mb-4">
                                    <div class="input-group-prepend">
                                        <label class="input-group-text" for="activity.status">
                                            <fmt:message key="add.activities.status"/>
                                        </label>
                                    </div>
                                    <select name="status" class="custom-select" id="activity.status">
                                        <c:forEach items="${requestScope.statusActivities}" var="status">
                                            <option value="${status.name()}">
                                                ${status.toString()}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <c:if test="${role eq 'ADMIN'}">
                                    <div class="input-group mb-4">
                                        <div class="input-group-prepend">
                                            <label class="input-group-text" for="user.username">
                                                <fmt:message key="add.activities.user.username"/>
                                            </label>
                                        </div>
                                        <select name="username" class="custom-select" id="user.username">
                                            <c:forEach items="${requestScope.users}" var="username">
                                                <option value="${username.getUserName()}">
                                                        ${username.getUserName()}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </c:if>

                            </form>
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary" form="form_change"><fmt:message key="button.submit"/></button>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>