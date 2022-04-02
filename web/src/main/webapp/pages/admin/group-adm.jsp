<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lol Club - Администрация</title>
    <link rel="shortcut icon" href="../../assets/img/sign-in.png" type="image/png">
    <link rel="stylesheet" href="../../css/style.css">
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
            <a href="main">Пользователи</a>
            <a href="admin/group/service"><span class="current__link">Группы</span></a>
            <a href="about">Домашка</a>
            <a href="test">Блог</a>
            <a href="blog">Фото</a>
            <a href="contacts">Прочее</a>
        </div>
    </nav>
    <a href="../../sign-out">
        <div class="sign__in">
            <div class="sign__in__logo">
                <p><img src="../../assets/img/sign-in.png" width="24px" height="24px" alt="Logo"></p>
            </div>
            <div class="sign__in__text">Выйти</div>
        </div>
    </a>
</header>
<div class="adm__panel__text">
    <p>AdmPanel</p>
</div>
<div class="admin__service__buttons">
    <a href="admin/group/service">
        <button type="button" class="admin__service__buttons__each">Добавить группу</button>
    </a>
    <a href="admin/group/service">
        <button type="button" class="admin__service__buttons__each">Удалить группу</button>
    </a>
    <a href="admin/group/service">
        <button type="button" class="admin__service__buttons__each">Заявки</button>
    </a>
    <a href="admin/group/service">
        <button type="button" class="admin__service__buttons__each">Добавить ученика</button>
    </a>
    <a href="admin/group/service">
        <button type="button" class="admin__service__buttons__each">Удалить ученика</button>
    </a>
    <a href="admin/group/service">
        <button type="button" class="admin__service__buttons__each">Просмотреть группы</button>
    </a>
</div>
<script src="../../js/script.js"></script>
</body>
</html>