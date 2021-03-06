<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lol Club - Студенты группы</title>
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
        <c:forEach var="user" items="${requestScope.users}">
            <li>
                <div class="group__card">
                    <p>${user.name} ${user.surname}</p><br>
                    <p>Username: ${user.username}</p>
                    <p>Email: ${user.email}</p>
                    <p>Phone: ${user.phone}</p>
                    <a href="<c:url value="/admin/group/control/group-users/delete/${user.username}/${group}/${page}"/>">
                        <button class="group__card__button" type="button">Отсоединить</button>
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
            <a href="<c:url value="/admin/group/control/delete-user/users/${requestScope.group}/${sessionScope.page + 1}"/>">
                <button class="pagination__right__button__active" type="button">&#5125</button>
            </a>
        </c:when>
    </c:choose>
</c:if>
<c:if test="${sessionScope.page != 1}">
    <c:choose>
        <c:when test="${requestScope.pagesNumber == sessionScope.page}">
            <a href="<c:url value="/admin/group/control/delete-user/users/${requestScope.group}/${sessionScope.page - 1}"/>">
                <button class="pagination__left__button__active" type="button">&#5130</button>
            </a>
            <button class="pagination__right__button__non__active" type="button">&#5125</button>
        </c:when>
        <c:when test="${requestScope.pagesNumber != sessionScope.page}">
            <a href="<c:url value="/admin/group/control/delete-user/users/${requestScope.group}/${sessionScope.page - 1}"/>">
                <button class="pagination__left__button__active" type="button">&#5130</button>
            </a>
            <a href="<c:url value="/admin/group/control/delete-user/users/${requestScope.group}/${sessionScope.page + 1}"/>">
                <button class="pagination__right__button__active" type="button">&#5125</button>
            </a>
        </c:when>
    </c:choose>
</c:if>
<script src="<c:url value="/js/script.js"/>"></script>
</body>
</html>
