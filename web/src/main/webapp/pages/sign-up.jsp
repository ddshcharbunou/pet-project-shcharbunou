<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lol Club - Регистрация</title>
    <link rel="shortcut icon" href="../assets/img/sign-in.png" type="image/png">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@700&family=Raleway:wght@600&display=swap"
          rel="stylesheet">
</head>
<body>
<header>
    <div class="logo">
        <p><img src="../assets/img/logo.png" width="192px" height="36px" alt="Lol Club"></p>
    </div>
    <nav>
        <div class="menu" id="menu">
            <a href="index.jsp">Главная</a>
            <a href="courses.jsp">Курсы</a>
            <a href="about.jsp">О школе</a>
            <a href="test.jsp">Тест</a>
            <a href="blog.jsp">Блог</a>
            <a href="contacts.jsp">Контакты</a>
        </div>
    </nav>
    <a href="sign-in.jsp">
        <div class="sign__in">
            <div class="sign__in__logo">
                <p><img src="../assets/img/sign-in.png" width="24px" height="24px" alt="Logo"></p>
            </div>
            <div class="sign__in__text">Войти</div>
        </div>
    </a>
</header>
<div class="sign__in__page__logo">
    <p><img src="../assets/img/logo.png" width="246px" height="46px" alt="Lol Club"></p>
</div>
<form class="sign__up__form" action="sign-up" method="post">
    <label>
        <input type="text" name="email" placeholder="Email">
        <input type="text" name="phone_number" placeholder="Телефон">
        <input type="text" name="name" placeholder="Имя">
        <input type="text" name="surname" placeholder="Фамилия">
        <input type="text" name="username" placeholder="Логин">
        <input type="password" name="password" placeholder="Пароль">
        <input type="password" name="repeat_password" placeholder="Повторите пароль">
    </label>
    <div class="buttons">
        <button type="submit" class="sign__up__page__button">Регистрация</button>
    </div>
</form>
<div class="student__office__text">Регистрация</div>
<script src="../js/script.js"></script>
</body>
</html>
