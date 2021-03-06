<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lol Club - О школе</title>
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
            <a href="<c:url value="/about"/>"><span class="current__link">О школе</span></a>
            <a href="<c:url value="/test"/>">Тест</a>
            <a href="<c:url value="/blog"/>">Блог</a>
            <a href="<c:url value="/contacts"/>">Контакты</a>
        </div>
    </nav>
    <c:choose>
        <c:when test="${sessionScope.user == null}">
            <a href="<c:url value="/sign-in"/>">
                <div class="sign__in">
                    <div class="sign__in__logo">
                        <p><img src="<c:url value="/assets/img/sign-in.png"/>" width="24px" height="24px" alt="Logo"></p>
                    </div>
                    <div class="sign__in__text">Войти</div>
                </div>
            </a>
        </c:when>
        <c:when test="${sessionScope.user != null}">
            <c:if test="${sessionScope.ROLE ne 'ADMIN'}">
                <a href="<c:url value="/office"/>">
                    <div class="sign__in">
                        <div class="sign__in__logo">
                            <p><img src="<c:url value="/assets/img/sign-in.png"/>" width="24px" height="24px" alt="Logo"></p>
                        </div>
                        <div class="sign__in__text">
                            <c:out value="${sessionScope.user.username}"/>
                        </div>
                    </div>
                </a>
            </c:if>
            <c:if test="${sessionScope.ROLE eq 'ADMIN'}">
                <a href="<c:url value="/office"/>">
                    <div class="sign__in">
                        <div class="sign__in__logo">
                            <p><img src="<c:url value="/assets/img/sign-in.png"/>" width="24px" height="24px" alt="Logo"></p>
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
<script src="<c:url value="/js/script.js"/>"></script>
</body>
</html>
