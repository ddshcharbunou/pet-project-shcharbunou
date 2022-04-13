<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lol Club - Кабинет студента</title>
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
            <div class="sign__in__text">Выйти</div>
        </div>
    </a>
</header>
<c:choose>
    <c:when test="${sessionScope.user.group == null}">
        <div class="adm__panel__text">Группы</div>
        <a href="<c:url value="/office/groups/kids/1"/>">
            <div class="group__age__kid">
                <img src="<c:url value="/assets/img/kid.png"/>" width="320px" height="320px" alt="Kids">
                <p style="color: #FBFF41; font-family: 'Montserrat', sans-serif; font-size: 32px">Дети</p>
            </div>
        </a>
        <a href="<c:url value="/office/groups/teens/1"/>">
            <div class="group__age__teen">
                <img src="<c:url value="/assets/img/teen.png"/>" width="320px" height="320px" alt="Teens">
                <p style="color: #FBFF41; font-family: 'Montserrat', sans-serif; font-size: 32px">Подростки</p>
            </div>
        </a>
        <a href="<c:url value="/office/groups/adults/1"/>">
            <div class="group__age__adult">
                <img src="<c:url value="/assets/img/adult.png"/>" width="320px" height="320px" alt="Adults">
                <p style="color: #FBFF41; font-family: 'Montserrat', sans-serif; font-size: 32px">Взрослые</p>
            </div>
        </a>
        <div class="claim__success__message">
            <c:if test="${requestScope.message != null}">
                <c:out value="${requestScope.message}"/>
            </c:if>
        </div>
        <div class="claim__failure__message">
            <c:if test="${requestScope.error != null}">
                <c:out value="${requestScope.error}"/>
            </c:if>
        </div>
    </c:when>
</c:choose>
<script src="<c:url value="/js/script.js"/>"></script>
</body>
</html>