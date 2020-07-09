<!DOCTYPE HTML>
<!-- %@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" % -->
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="messages" />

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>

<body>
<%@include file="header.jspf"%>
<div class="container-fluid">
    <section class="title">
        <fmt:message key = "header.title"/>
    </section>
    <section class="descr">
        <div class="container">
            2. Система Time-Tracking. Администратор закрепляет за пользователем Активность.
            У пользователя может быть одна или несколько Активностей. Пользователь отмечает
            кол-во затраченного времени на каждую активность. Пользователь может отправить
            запрос на добавление/удаление Активности.
            Вход
        </div>
    </section>
</div>
<div class="container">
    <form class="form-signin" action="registration" method="post">
        <label id="InputUsername" for="username">
            <fmt:message key="registration.username"/>
        </label>
        <input type="text"
               id="username"
               name="username"
               class="form-control"
               placeholder="<fmt:message key="registration.username.placeholder"/>"
               required
               autofocus>
        <label id="InputFirstName" for="firstname">
            <fmt:message key="registration.firstname"/>
        </label>
        <input type="text"
               id="firstname"
               name="firstname"
               class="form-control"
               placeholder="<fmt:message key="registration.firstname.placeholder"/>"
               required>
        <label id="InputLastName" for="lastname">
            <fmt:message key="registration.lastname"/>
        </label>
        <input type="text"
               id="lastname"
               name="lastname"
               class="form-control"
               placeholder="<fmt:message key="registration.lastname.placeholder"/>"
               required>
        <label id="InputPassword" for="password">
            <fmt:message key="registration.password"/>
        </label>
        <input type="password"
               id="password"
               name="password"
               class="form-control"
               placeholder="<fmt:message key="registration.password.placeholder"/>"
               required
               class="form-control">
        <button type="submit" class="btn btn-primary"><fmt:message key="button.submit"/></button>
    </form>
</div>
</body>
</html>