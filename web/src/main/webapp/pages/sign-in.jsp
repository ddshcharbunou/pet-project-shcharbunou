<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lol Club - Вход</title>
    <link rel="shortcut icon" href="assets/img/sign-in.png" type="image/png">
    <link rel="stylesheet" href="css/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@700&family=Raleway:wght@600&display=swap"
          rel="stylesheet">
</head>
<body>
<header>
    <div class="logo">
        <p><img src="assets/img/logo.png" width="192px" height="36px" alt="Lol Club"></p>
    </div>
    <nav>
        <div class="menu" id="menu">
            <a href="main">Главная</a>
            <a href="courses">Курсы</a>
            <a href="about">О школе</a>
            <a href="test">Тест</a>
            <a href="blog">Блог</a>
            <a href="contacts">Контакты</a>
        </div>
    </nav>
    <a href="sign-in">
        <div class="sign__in">
            <div class="sign__in__logo">
                <p><img src="assets/img/sign-in.png" width="24px" height="24px" alt="Logo"></p>
            </div>
            <div class="sign__in__text">Войти</div>
        </div>
    </a>
</header>
<div class="sign__in__page__logo">
    <p><img src="assets/img/logo.png" width="246px" height="46px" alt="Lol Club"></p>
</div>
<form class="sign__in__form" action="sign-in" method="post">
    <label>
        <input type="text" id="username" name="username" placeholder="Логин" required
               oninvalid="this.setCustomValidity('Введите логин')" oninput="setCustomValidity('')">
        <input type="password" id="password" name="password" placeholder="Пароль" required
               oninvalid="this.setCustomValidity('Введите пароль')" oninput="setCustomValidity('')">
    </label>
    <div class="buttons">
        <button type="submit" class="sign__in__button">Войти</button>
        <a href="sign-up">
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
<script src="js/script.js"></script>
</body>
</html>