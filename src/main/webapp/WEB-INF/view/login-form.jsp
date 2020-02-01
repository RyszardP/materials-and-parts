<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>

    <style>
        .error {
            color: red
        }
    </style>
</head>
<body>
<i>Please Login</i>

<br><br>

<form:form action="loginForm" modelAttribute="user">

    Login (*): <form:input path="login"/>
    <form:errors path="login" cssClass="error"/>
    <br><br>

    Password (*): <form:input path="password"/>
    <form:errors path="password" cssClass="error"/>

    <br><br>

    <input type="submit" value="Submit" />
</form:form>
</body>
</html>
