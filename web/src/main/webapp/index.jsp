<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lol Club - Главная</title>
    <link rel="shortcut icon" href="../../../../src/main/webapp/assets/img/sign-in.png" type="image/png">
    <link rel="stylesheet" href="../../../../src/main/webapp/css/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@700&family=Raleway:wght@600&display=swap"
          rel="stylesheet">
</head>
<body>
<header>
    <div class="logo">
        <p><img src="../../../../src/main/webapp/assets/img/logo.png" width="192px" height="36px" alt="Lol Club"></p>
    </div>
    <nav>
        <div class="menu" id="menu">
            <a href="index.jsp"><span class="current__link">Главная</span></a>
            <a href="../../../../src/main/webapp/pages/courses.jsp">Курсы</a>
            <a href="../../../../src/main/webapp/pages/about.jsp">О школе</a>
            <a href="../../../../src/main/webapp/pages/test.jsp">Тест</a>
            <a href="../../../../src/main/webapp/pages/blog.jsp">Блог</a>
            <a href="../../../../src/main/webapp/pages/contacts.jsp">Контакты</a>
        </div>
    </nav>
    <a href="../../../../src/main/webapp/pages/sign-in.jsp">
        <div class="sign__in">
            <div class="sign__in__logo">
                <p><img src="../../../../src/main/webapp/assets/img/sign-in.png" width="24px" height="24px" alt="Logo"></p>
            </div>
            <div class="sign__in__text">Войти</div>
        </div>
    </a>
</header>
<script src="../../../../src/main/webapp/js/script.js"></script>
</body>
</html>