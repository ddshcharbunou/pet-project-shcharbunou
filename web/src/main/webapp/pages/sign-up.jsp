<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lol Club - Регистрация</title>
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
            <a href="">Главная</a>
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
<form class="sign__up__form" action="sign-up" method="post">
    <label>
        <input type="text" name="email" placeholder="Email" required
               oninvalid="this.setCustomValidity('Введите email')" oninput="setCustomValidity('')">
        <input type="text" name="phone" placeholder="Телефон"
               oninvalid="this.setCustomValidity('Введите телефон')" oninput="setCustomValidity('')">
        <input type="text" name="name" placeholder="Имя"
               oninvalid="this.setCustomValidity('Введите имя')" oninput="setCustomValidity('')">
        <input type="text" name="surname" placeholder="Фамилия"
               oninvalid="this.setCustomValidity('Введите фамилию')" oninput="setCustomValidity('')">
        <input type="text" name="username" placeholder="Логин"
               oninvalid="this.setCustomValidity('Введите логин')" oninput="setCustomValidity('')">
        <input type="password" name="password" placeholder="Пароль"
               oninvalid="this.setCustomValidity('Введите пароль')" oninput="setCustomValidity('')">
        <input type="password" name="repeated_password" placeholder="Повторите пароль"
               oninvalid="this.setCustomValidity('Подтвердите пароль')" oninput="setCustomValidity('')">
    </label>
    <div class="buttons">
        <button type="submit" class="sign__up__page__button">Регистрация</button>
    </div>
</form>
<div class="student__office__text">Регистрация</div>
<script src="js/script.js"></script>
</body>
</html>
