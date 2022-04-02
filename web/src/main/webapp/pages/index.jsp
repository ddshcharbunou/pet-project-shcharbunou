<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lol Club - Главная</title>
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
            <a href="main"><span class="current__link">Главная</span></a>
            <a href="courses">Курсы</a>
            <a href="about">О школе</a>
            <a href="test">Тест</a>
            <a href="blog">Блог</a>
            <a href="contacts">Контакты</a>
        </div>
    </nav>
    <c:choose>
        <c:when test="${sessionScope.user == null}">
            <a href="sign-in">
                <div class="sign__in">
                    <div class="sign__in__logo">
                        <p><img src="assets/img/sign-in.png" width="24px" height="24px" alt="Logo"></p>
                    </div>
                    <div class="sign__in__text">Войти</div>
                </div>
            </a>
        </c:when>
        <c:when test="${sessionScope.user != null}">
            <c:if test="${sessionScope.ROLE ne 'ADMIN'}">
                <a href="office">
                    <div class="sign__in">
                        <div class="sign__in__logo">
                            <p><img src="assets/img/sign-in.png" width="24px" height="24px" alt="Logo"></p>
                        </div>
                        <div class="sign__in__text">
                            <c:out value="${sessionScope.user.username}"/>
                        </div>
                    </div>
                </a>
            </c:if>
            <c:if test="${sessionScope.ROLE eq 'ADMIN'}">
                <a href="office">
                    <div class="sign__in">
                        <div class="sign__in__logo">
                            <p><img src="assets/img/sign-in.png" width="24px" height="24px" alt="Logo"></p>
                        </div>
                        <div class="sign__in__text">
                            <c:out value="${sessionScope.user.username}"/>
                        </div>
                    </div>
                </a>
            </c:if>
        </c:when>
    </c:choose>
</header>
<div class="main__page__text__block">
    <div class="main__page__text__sub__block">
        <div class="main__page__lol__text"><p>LOTS OF LOVE</p></div>
        <div class="main__page__main__text"><p>Английский<br>для детей и взрослых<br>в Жодино</p></div>
    </div>
    <div class="main__page__description__text"><p>Обучайтесь английскому языку у опытного преподавателя<br>
        по уникальному методу полного погружения в атмосферу языка.<br>
        С нуля до свободного владения!</p></div>
</div>
<div class="main__page__back__rectangle"></div>
<div class="main__page__front__rectangle"></div>
<div class="main__page__front__rectangle__helper"></div>
<div class="star">
    <p><img src="assets/img/star.png" width="81px" height="81px" alt="STAR"></p>
</div>
<div class="star__2">
    <p><img src="assets/img/star.png" width="62px" height="62px" alt="STAR"></p>
</div>
<div class="star__3">
    <p><img src="assets/img/star.png" width="103px" height="103px" alt="STAR"></p>
</div>
<div class="star__4">
    <p><img src="assets/img/star.png" width="62px" height="62px" alt="STAR"></p>
</div>
<div class="star__5">
    <p><img src="assets/img/star.png" width="70px" height="70px" alt="STAR"></p>
</div>
<div class="main__buttons">
    <a href="test">
        <button type="button" class="test__button">Пройти тест</button>
    </a>
    <a href="contacts">
        <button type="button" class="connect__button">Связаться</button>
    </a>
</div>
<script src="js/script.js"></script>
</body>
</html>