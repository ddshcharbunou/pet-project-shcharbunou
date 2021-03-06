<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lol Club - Отсоединение пользователя от группы</title>
    <link rel="shortcut icon" href="<c:url value="/assets/img/sign-in.png"/>" type="image/png">
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@700&family=Raleway:wght@600&display=swap"
          rel="stylesheet">
</head>
<body>
<header>
    <div class="admin__logo">
        <p>AdmPanel</p>
    </div>
    <nav>
        <div class="menu" id="menu">
            <a href="<c:url value="/admin/user/control"/>">Пользователи</a>
            <a href="<c:url value="/admin/group/control"/>"><span class="current__link">Группы</span></a>
            <a href="<c:url value="/admin/homework/control"/>">Домашка</a>
            <a href="<c:url value="/admin/blog/control"/>">Блог</a>
            <a href="<c:url value="/admin/photo/control"/>">Фото</a>
            <a href="<c:url value="/admin/other/control"/>">Прочее</a>
        </div>
    </nav>
    <a href="<c:url value="/admin"/>">
        <div class="sign__in">
            <div class="sign__in__logo">
                <p><img src="<c:url value="/assets/img/sign-in.png"/>" width="24px" height="24px" alt="Logo"></p>
            </div>
            <div class="sign__in__text">
                <c:out value="${sessionScope.user.username}"/>
            </div>
        </div>
    </a>
</header>
<ul>
    <div class="group__under__card">
        <c:forEach var="group" items="${requestScope.groups}">
            <li>
                <div class="group__card">
                    <p>${group.designation.designation} (${group.level})</p><br>
                    <p>
                        <img src="<c:url value="/assets/img/clock.png"/>" style="transform: translate(0, 6px)"
                             width="24px" height="24px" alt="Time:"> ${group.time}
                        <c:if test="${group.additionalInformation != null}">
                            <c:if test="${group.additionalInformation != ''}">
                                !${group.additionalInformation}!
                            </c:if>
                        </c:if>
                    </p>
                    <p>
                        <img src="<c:url value="/assets/img/calendar.png"/>" style="transform: translate(0, 6px)"
                             width="24px" height="24px" alt="Days:">
                        <c:forEach var="day" items="${group.days}">
                            ${day}
                        </c:forEach>
                    </p>
                    <p><img src="<c:url value="/assets/img/adult.png"/>" style="transform: translate(0, 6px)"
                            width="24px" height="24px" alt="Teacher:"> ${group.teacher}</p>
                    <p>
                        <c:choose>
                            <c:when test="${group.age == 'KIDS'}">
                                <img src="<c:url value="/assets/img/kid.png"/>" style="transform: translate(0, 6px)"
                                     width="24px" height="24px" alt="Age:">
                                ${group.age.age}
                            </c:when>
                            <c:when test="${group.age == 'TEENS'}">
                                <img src="<c:url value="/assets/img/teen.png"/>" style="transform: translate(0, 6px)"
                                     width="24px" height="24px" alt="Age:">
                                ${group.age.age}
                            </c:when>
                            <c:when test="${group.age == 'ADULTS'}">
                                <img src="<c:url value="/assets/img/adult.png"/>" style="transform: translate(0, 6px)"
                                     width="24px" height="24px" alt="Age:">
                                ${group.age.age}
                            </c:when>
                        </c:choose>
                    </p>
                    <a href="<c:url value="/admin/group/control/delete-user/users/${group.id}/1"/>">
                        <button class="group__card__button" type="button">Студенты</button>
                    </a>
                </div>
            </li>
        </c:forEach>
    </div>
</ul>
<c:if test="${sessionScope.page == 1}">
    <c:choose>
        <c:when test="${requestScope.pagesNumber == 1}">
            <button class="pagination__left__button__non__active" type="button">&#5130</button>
            <button class="pagination__right__button__non__active" type="button">&#5125</button>
        </c:when>
        <c:when test="${requestScope.pagesNumber == 0}">
            <button class="pagination__left__button__non__active" type="button">&#5130</button>
            <button class="pagination__right__button__non__active" type="button">&#5125</button>
        </c:when>
        <c:when test="${requestScope.pagesNumber != 1}">
            <button class="pagination__left__button__non__active" type="button">&#5130</button>
            <a href="<c:url value="/admin/group/control/del-user-group/${sessionScope.page + 1}"/>">
                <button class="pagination__right__button__active" type="button">&#5125</button>
            </a>
        </c:when>
    </c:choose>
</c:if>
<c:if test="${sessionScope.page != 1}">
    <c:choose>
        <c:when test="${requestScope.pagesNumber == sessionScope.page}">
            <a href="<c:url value="/admin/group/control/del-user-group/${sessionScope.page - 1}"/>">
                <button class="pagination__left__button__active" type="button">&#5130</button>
            </a>
            <button class="pagination__right__button__non__active" type="button">&#5125</button>
        </c:when>
        <c:when test="${requestScope.pagesNumber != sessionScope.page}">
            <a href="<c:url value="/admin/group/control/del-user-group/${sessionScope.page - 1}"/>">
                <button class="pagination__left__button__active" type="button">&#5130</button>
            </a>
            <a href="<c:url value="/admin/group/control/del-user-group/${sessionScope.page + 1}"/>">
                <button class="pagination__right__button__active" type="button">&#5125</button>
            </a>
        </c:when>
    </c:choose>
</c:if>
<script src="<c:url value="/js/script.js"/>"></script>
</body>
</html>