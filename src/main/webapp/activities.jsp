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
                    <h1 class="display-3"><fmt:message key="activities.title"/></h1>
                </div>
                <div class="card-body">
                    <c:if test="${not empty requestScope.activities}">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">
                                        <fmt:message key="activities.id"/>
                                    </th>
                                    <th scope="col">
                                        <fmt:message key="users.firstname"/>
                                    </th>
                                    <th scope="col">
                                        <fmt:message key="users.lastname"/>
                                    </th>
                                    <th scope="col">
                                        <fmt:message key="activities.name"/>
                                    </th>
                                    <th scope="col">
                                        <fmt:message key="activities.goal"/>
                                    </th>
                                    <th scope="col">
                                        <fmt:message key="activities.duration"/>
                                    </th>
                                    <th scope="col">
                                        <fmt:message key="activities.status"/>
                                    </th>
                                    <th scope="col">
                                        <fmt:message key="request.action"/>
                                    </th>
                                    <th scope="col">
                                        <fmt:message key="request.status"/>
                                    </th>
                                    <th scope="col">
                                        <fmt:message key="activities.edit"/>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.activities}" var="activity">
                                    <tr>
                                        <form method="post" action="add_activity" id="act${activity.getIdactivity()}">
                                            <input type="hidden"
                                                   name="activity.id"
                                                   form="act${activity.getIdactivity()}"
                                                   value="${activity.getIdactivity()}">
                                        </form>
                                        <form method="post" action="add_request" id="req${activity.getIdactivity()}">
                                            <input type="hidden"
                                                   name="request.act.id"
                                                   form="req${activity.getIdactivity()}"
                                                   value="${activity.getIdactivity()}">
                                        </form>
                                            <td scope="row">${activity.getIdactivity()}</td>
                                            <td scope="row">${activity.getUser().getFirstName()}</td>
                                            <td scope="row">${activity.getUser().getLastName()}</td>
                                            <td scope="row">${activity.getName()}</td>
                                            <td scope="row">${activity.getGoal()}</td>
                                            <td scope="row">${activity.getDuration()}</td>
                                            <td scope="row">${activity.getStatusActivity()}</td>
                                            <td scope="row">${activity.getRequestMap().get(roleUser.getId()).getAction()}</td>
                                            <td scope="row">${activity.getRequestMap().get(roleUser.getId()).getStatus()}</td>
                                            <c:if test="${sessionScope.roleUser.getId() eq activity.getUser().getId() || role eq 'ADMIN'}">
                                                <td class="row"><button value="Submit" type="submit" class="btn btn-primary" form="act${activity.getIdactivity()}" name="edit.activity">
                                                    <fmt:message key="activities.edit"/>
                                                </button></td>
                                            </c:if>
                                            <c:if test="${sessionScope.roleUser.getId() ne activity.getUser().getId() && role ne 'ADMIN'}">
                                                <td class="row"><button value="Submit" type="submit" class="btn btn-primary" form="act${activity.getIdactivity()}" name="edit.activity" disabled>
                                                    <fmt:message key="activities.edit"/>
                                                </button></td>
                                            </c:if>

                                            <td class="row"><button value="Submit" type="submit" class="btn btn-primary" form="req${activity.getIdactivity()}" name="edit.request">
                                                <c:choose><c:when test="${activity.getRequestMap().get(roleUser.getId()) eq null}">
                                                    <fmt:message key="requests.add"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <fmt:message key="requests.edit"/>
                                                </c:otherwise>
                                                </c:choose>
                                            </button></td>

                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${empty requestScope.activities}">
                        
                    </c:if>
                </div>
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