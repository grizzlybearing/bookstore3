<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<c:set var="lang" value="${sessionScope.language ? sessionScope.language : 'ru'}"--%>
<%--       scope="session"/>--%>
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
    <title>Панель администратора</title>
    <link href="<c:url value="/resource/css/admin.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="wrapper">
    <h1>Статистика</h1>
    <ol>
        <c:forEach var="user" items="${users}">
            <li>
                <br>
                <ul>
                    <li>Логин: ${user.name}</li>
                    <li>Email: ${user.email}</li>
                    <li>Password: ${user.password}</li>
                    <li>Товары в корзине:
                        <ul>
                            <c:forEach var="dishItem" items="${user.dishesInBasket}">
                                <li>${dishItem.dish.name}(${dishItem.amount}шт)</li>
                            </c:forEach>
                        </ul>
                    </li>
                </ul>
            </li>
        </c:forEach>
    </ol>
</div>
</body>
</html>
