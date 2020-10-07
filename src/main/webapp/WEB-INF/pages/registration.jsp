<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <title>Регистрация</title>
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    <script>
        function enableBtn(){
            document.getElementById("button1").disabled = false;
        }
    </script>

</head>


<body>
<div>
    <form:form method="POST" modelAttribute="userForm">
        <h2>Регистрация</h2>


        <div>
            <form:input type="text" path="username" placeholder="Username"
                        autofocus="true"></form:input>
            <form:errors path="username"></form:errors>
                ${usernameError}
        </div>
        <div>
            <form:input type="password" path="password" placeholder="Password"></form:input>
        </div>
        <div>
            <form:input type="password" path="passwordConfirm"
                        placeholder="Confirm your password"></form:input>
            <form:errors path="password"></form:errors>
                ${passwordError}
        </div>
        <div>
            <form:input type="number" path="phoneNumber"
                        placeholder="Phone number"></form:input>
            <form:errors path="phoneNumber"></form:errors>
                ${phoneNumberError}
        </div>
        <div>
            <form:input type="email" path="email"
                        placeholder="Email"></form:input>
            <form:errors path="email"></form:errors>
                ${emailError}
        </div>

        <div class="g-recaptcha" data-sitekey="6LfFZ8sZAAAAAAF3dH_Tlv6ipH5G730SbSMEgzAz"></div>
        <button type="submit" id="button1" disabled="disabled">Зарегистрироваться</button>
    </form:form>
    <a href="/">Главная</a>
</div>
</body>
</html>
