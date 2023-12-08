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
    <title>Login</title>
    <link href="<c:url value="/resource/css/auth.css"/>" rel="stylesheet" type="text/css"/>
</head>

<body>
<div class="wrapper">
    <h1 class="title"><fmt:message key="authorize.title"/></h1>
    <form method="POST" >
        <div class="input-wrap">
            <label><fmt:message key="authorize.login"/></label>
            <input class="input" type="text" name="username" id="username" autocomplete="off">
        </div>
        <div class="input-wrap">
            <label><fmt:message key="authorize.pass"/></label>
            <input class="input" type="password" name="password" id="password" >
        </div>
        <input type="hidden" name="command" value="LOG_IN" />
        <div class="buttons">
            <input class="loginbtn" type="submit" name="login" value="<fmt:message key="authorize.signIn"/>">
        </div>
        <a class="regbtn" href="/registration"><fmt:message key="authorize.signUp"/></a>
    </form>
</div>
</body>
</html>
