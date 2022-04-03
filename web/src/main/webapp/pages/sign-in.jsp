<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lol Club - Вход</title>
    <link rel="shortcut icon" href="<c:url value="/assets/img/sign-in.png"/>" type="image/png">
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@700&family=Raleway:wght@600&display=swap"
          rel="stylesheet">
</head>
<body>
<header>
    <div class="logo">
        <p><img src="<c:url value="/assets/img/logo.png"/>" width="192px" height="36px" alt="Lol Club"></p>
    </div>
    <nav>
        <div class="menu" id="menu">
            <a href="<c:url value="/main"/>">Главная</a>
            <a href="<c:url value="/courses"/>">Курсы</a>
            <a href="<c:url value="/about"/>">О школе</a>
            <a href="<c:url value="/test"/>">Тест</a>
            <a href="<c:url value="/blog"/>">Блог</a>
            <a href="<c:url value="/contacts"/>">Контакты</a>
        </div>
    </nav>
    <a href="<c:url value="/sign-in"/>">
        <div class="sign__in">
            <div class="sign__in__logo">
                <p><img src="<c:url value="/assets/img/sign-in.png"/>" width="24px" height="24px" alt="Logo"></p>
            </div>
            <div class="sign__in__text">Войти</div>
        </div>
    </a>
</header>
<div class="sign__in__page__logo">
    <p><img src="<c:url value="/assets/img/logo.png"/>" width="246px" height="46px" alt="Lol Club"></p>
</div>
<form class="sign__in__form" action="<c:url value="/sign-in"/>" method="post">
    <label>
        <input type="text" id="username" name="username" placeholder="Логин" required
               oninvalid="this.setCustomValidity('Введите логин')" oninput="setCustomValidity('')">
        <input type="password" id="password" name="password" placeholder="Пароль" required
               oninvalid="this.setCustomValidity('Введите пароль')" oninput="setCustomValidity('')">
    </label>
    <div class="buttons">
        <button type="submit" class="sign__in__button">Войти</button>
        <a href="<c:url value="/sign-up"/>">
            <button type="button" class="sign__up__button">Регистрация</button>
        </a>
    </div>
</form>
<div class="sign__in__error__message">
    <c:if test="${error != null}">
        <c:out value="${error}"/>
    </c:if>
</div>
<div class="student__office__text">Кабинет студента</div>
<script src="<c:url value="/js/script.js"/>"></script>
</body>
</html>