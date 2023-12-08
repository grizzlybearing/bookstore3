<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${sessionScope.language != null ? sessionScope.language : 'ru'}"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;900&display=swap" rel="stylesheet">
    <title>Site</title>
    <link href="<c:url value="/resource/css/main.css"/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="/resource/css/footer.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<header>
    <div class="container">
        <div class="navigation-bar">
            <div class="brand">
                <h1><a href="/">Bookstore</a></h1>
            </div>
            <nav class="navigation">
                <ul class="navigation-list">
                    <li class="navigation-list-item"><a href="/"><fmt:message key="nav.main"/></a></li>
                    <li class="navigation-list-item"><a href="/#menu"><fmt:message key="nav.menu"/></a></li>
                    <li class="navigation-list-item"><a href="/basket"><fmt:message key="nav.basket"/></a></li>
                    <li class="navigation-list-item"><a id="tel" href="tel:+375291809834">+375(29)180-98-34</a></li>
                    <c:if test="${sessionScope.user.isAuth}">
                        <li class="navigation-list-item">
                            <form method="POST" action="/logout">
<%--                                <input type="hidden" name="command" value="LOG_OUT"/>--%>
                                <input type="submit" class="loginbtn" value="<fmt:message key="nav.logout"/>"/>
                            </form>

                        </li>
                    </c:if>
                    <c:if test="${!sessionScope.user.isAuth}">
                        <li class="navigation-list-item">
                            <a href="login" class="loginbtn"><fmt:message key="nav.login"/></a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </div>
</header>
<div class="container">
    <a class="telephone" href="tel:+375291809834">+375(29)180-98-34</a>
</div>
<div class="container">
    <div class="lang">
        <form method="post" action="/change-lang/ru">
            <input type="hidden" name="lang" value="ru">
            <input type="submit" value="ru" class="ru-lang ${language == "ru" ? "active" : ""}">
        </form>
        <form method="post" action="/change-lang/en">
            <input type="hidden" name="lang" value="en">
            <input type="submit" value="en" class="en-lang ${language == "en" ? "active" : ""}">
        </form>
    </div>
</div>
<section class="main-screen">
    <div class="container">
        <div class="main-screen-img">
<%--            <img src="<c:url value="/resource/img/jumbotron_1200.jpg"/>" alt="Jumbotron">--%>
        </div>
    </div>
</section>
<section id="menu" class="menu-categories">
    <div class="container">
        <h2 class="menu-categories-title"><fmt:message key="menu.our"/></h2>
        <div class="menu-items">

            <form method="get" action="single-category/1">
                <button type="submit" class="menu-category-btn">
                    <div class="menu-item">
                        <a class="menu-category col-3" href="single-category/1">
                            <div class="menu-category-img" id="breakfast">
                                <span class="menu-category-title"><fmt:message key="menu.breakfat"/></span>
                            </div>
                        </a>
                    </div>
                </button>
            </form>

            <form method="get" action="single-category/2">
                <button type="submit" class="menu-category-btn">
                    <div class="menu-item">
                        <a class="menu-category" href="single-category/2">
                            <div class="menu-category-img" id="soup">
                                <span class="menu-category-title"><fmt:message key="menu.soup"/></span>
                            </div>
                        </a>
                    </div>
                </button>
            </form>

            <form method="get" action="single-category/3">
                <button type="submit" class="menu-category-btn">
                    <div class="menu-item">
                        <a class="menu-category" href="single-category/3">
                            <div class="menu-category-img" id="deserts">
                                <span class="menu-category-title"><fmt:message key="menu.deserts"/></span>
                            </div>
                        </a>
                    </div>
                </button>
            </form>

            <form action="single-category/4">
                <button type="submit" class="menu-category-btn">
                    <div class="menu-item">
                        <a class="menu-category" href="single-category/4">
                            <div class="menu-category-img" id="street-food">
                                <span class="menu-category-title"><fmt:message key="menu.streetFood"/></span>
                            </div>
                        </a>
                    </div>
                </button>
            </form>
        </div>
    </div>
</section>
<footer>
    <div class="container">
        <h3 class="footer-title"><fmt:message key="location.our"/></h3>
        <div class="map">
            <a class="map-link" href="https://maps.app.goo.gl/4hZ9W667CsL851AS6" target="_blank">
                <div class="map-item">
                   <iframe src="https://www.google.com/maps/embed?pb=!1m16!1m12!1m3!1d4014.816358454044!2d27.579008600804585!3d53.91554003240021!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!2m1!1z0LHRg9C60LjQvdC40YHRgiDQvdC10LfQsNCy0LjRgdC40LzQvtGB0YLQuA!5e0!3m2!1sru!2sby!4v1701612845670!5m2!1sru!2sby" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                    <span class="map-title">map</span>
                </div>
            </a>
        </div>
        <div class="copyright">
            All rights are reserved &#169;
        </div>
    </div>
</footer>
</body>
</html>