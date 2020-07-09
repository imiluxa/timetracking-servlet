<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<!-- %@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %-->

<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="messages" />

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>

<body>
<%@include file="header.jspf"%>
<div class="container">
    <div class="row">
        <div class="col-lg-20">
            <div class="card">
                <div class="card-header">
                    <h1 class="display-3">${requestScope.activityReq}</h1>
                </div>
                <div class="card-body">
                    <c:if test="${not empty requestScope.requests}">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">
                                    <fmt:message key="users.firstname"/>
                                </th>
                                <th scope="col">
                                    <fmt:message key="users.lastname"/>
                                </th>
                                <th scope="col">
                                    <fmt:message key="request.action"/>
                                </th>
                                <th scope="col">
                                    <fmt:message key="request.status"/>
                                </th>
                                <th scope="col">
                                    <fmt:message key="request.edit"/>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.requests}" var="request">
                                <tr>
                                    <form method="post" action="add_request" id="req${request.getId()}">
                                        <input type="hidden"
                                               name="request.act.id"
                                               form="req${request.getId()}"
                                               value="${request.getActivity().getIdactivity()}">
                                        <input type="hidden"
                                               name="request.id"
                                               form="req${request.getId()}"
                                               value="${request.getId()}">
                                    </form>
                                    <td scope="row">${request.getUser().getFirstName()}</td>
                                    <td scope="row">${request.getUser().getLastName()}</td>
                                    <td scope="row">${request.getAction()}</td>
                                    <td scope="row">${request.getStatus()}</td>
                                    <td scope="row"><button value="Submit" type="submit" class="btn btn-primary" form="req${request.getId()}" name="edit.request">
                                        <fmt:message key="requests.edit"/>
                                    </button></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${empty requestScope.requests}">

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