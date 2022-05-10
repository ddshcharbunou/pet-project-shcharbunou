<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lol Club - Добавление группы</title>
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
    <a href="<c:url value="/sign-out"/>">
        <div class="sign__in">
            <div class="sign__in__logo">
                <p><img src="<c:url value="/assets/img/sign-in.png"/>" width="24px" height="24px" alt="Logo"></p>
            </div>
            <div class="sign__in__text">Выйти</div>
        </div>
    </a>
</header>
<div class="adm__panel__text">
    <p>AdmPanel</p>
</div>
<div class="admin__service__buttons">
    <form action="<c:url value="/admin/group/control/add-group"/>" method="post">
        <label>
            <select class="admin__service__buttons__each" name="designation">
                <option disabled>Выберите название</option>
                <option value="BEGINNER">Beginner</option>
                <option value="ELEMENTARY1_3">Elementary 1-3</option>
                <option value="ELEMENTARY4">Elementary 4</option>
                <option value="PRE_INTERMEDIATE1_3">Pre-intermediate 1-3</option>
                <option value="PRE_INTERMEDIATE4">Pre-intermediate 4</option>
                <option value="LOW_INTERMEDIATE">Low-intermediate</option>
                <option value="MID_INTERMEDIATE">Mid-intermediate</option>
                <option value="UPPER_INTERMEDIATE1_3">Upper-intermediate 1-3</option>
                <option value="PRE_ADVANCED1_3">Pre-advanced 1-3</option>
                <option value="LOWER_ADVANCED1_3">Lower-advanced 1-3</option>
                <option value="UPPER_ADVANCED1_3">Upper-advanced 1-3</option>
            </select>
        </label>
        <label>
            <select class="admin__service__buttons__each" name="age">
                <option disabled>Выберите возраст</option>
                <option value="KIDS">Kids</option>
                <option value="TEENS">Teens</option>
                <option value="ADULTS">Adults</option>
            </select>
        </label>
        <label>
            <select class="admin__service__buttons__each" name="level">
                <option disabled>Выберите уровень</option>
                <option value="A1">A1</option>
                <option value="A2">A2</option>
                <option value="B1">B1</option>
                <option value="B2">B2</option>
                <option value="C1">C1</option>
                <option value="C2">C2</option>
            </select>
        </label>
        <label>
            <p style="color: #FBFF41; font-family: 'Montserrat', sans-serif; font-size: 16px;
            margin-left: auto; margin-right: auto">Понедельник</p>
            <input class="group_checkbox" type="checkbox" name="days" value="MONDAY">
        </label>
        <label>
            <p style="color: #FBFF41; font-family: 'Montserrat', sans-serif; font-size: 16px;
            margin-left: auto; margin-right: auto">Вторник</p>
            <input class="group_checkbox" type="checkbox" name="days" value="TUESDAY">
        </label>
        <label>
            <p style="color: #FBFF41; font-family: 'Montserrat', sans-serif; font-size: 16px;
            margin-left: auto; margin-right: auto">Среда</p>
            <input class="group_checkbox" type="checkbox" name="days" value="WEDNESDAY">
        </label>
        <label>
            <p style="color: #FBFF41; font-family: 'Montserrat', sans-serif; font-size: 16px;
            margin-left: auto; margin-right: auto">Четверг</p>
            <input class="group_checkbox" type="checkbox" name="days" value="THURSDAY">
        </label>
        <label>
            <p style="color: #FBFF41; font-family: 'Montserrat', sans-serif; font-size: 16px;
            margin-left: auto; margin-right: auto">Пятница</p>
            <input class="group_checkbox" type="checkbox" name="days" value="FRIDAY">
        </label>
        <label>
            <p style="color: #FBFF41; font-family: 'Montserrat', sans-serif; font-size: 16px;
            margin-left: auto; margin-right: auto">Суббота</p>
            <input class="group_checkbox" type="checkbox" name="days" value="SATURDAY">
        </label>
        <label>
            <p style="color: #FBFF41; font-family: 'Montserrat', sans-serif; font-size: 16px;
            margin-left: auto; margin-right: auto">Воскресенье</p>
            <input class="group_checkbox" type="checkbox" name="days" value="SUNDAY">
        </label>
        <label>
            <input class="admin__service__buttons__each" type="text" id="time" name="time" placeholder="Время (00:00)" required
                   oninvalid="this.setCustomValidity('Введите время')" oninput="setCustomValidity('')">
        </label>
        <label>
            <input class="admin__service__buttons__each" type="text" id="teacher" name="teacher" placeholder="Преподаватель" required
                   oninvalid="this.setCustomValidity('Введите юзернейм преподавателя')" oninput="setCustomValidity('')">
        </label>
        <label>
            <input class="admin__service__buttons__each" type="text" id="additionalInformation" name="additionalInformation" placeholder="Время (искл.)">
        </label>
        <button class="admin__service__buttons__each" type="submit">Добавить</button>
    </form>
</div>
<div class="adm__error__message">
    <c:if test="${requestScope.error != null}">
        <c:out value="${requestScope.error}"/>
    </c:if>
</div>
<script src="<c:url value="/js/script.js"/>"></script>
</body>
</html>