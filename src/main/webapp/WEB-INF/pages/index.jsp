<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
  <title>Ads Index Page</title>
</head>
<body>
<h3>Ads Index Page</h3>
<br/>
<a href="<c:url value="/ads?category=All&id=0&id2=10"/>">Ads list</a>
<h1>Ads List</h1>
<sec:authorize access="isAuthenticated()"> <h4><a href="/manageAds">Manage ADS</a></h4> </sec:authorize>

<div>
  <h3>${pageContext.request.userPrincipal.name}</h3>
  <sec:authorize access="!isAuthenticated()">
    <h4><a href="${pageContext.request.contextPath}/login">Войти</a></h4>
    <h4><a href="/registration">Зарегистрироваться</a></h4>
  </sec:authorize>
  <sec:authorize access="isAuthenticated()"> <h4><a href="/myAccount">My account</a></h4> </sec:authorize>
  <sec:authorize access="isAuthenticated()">
    <h4><a href="/logout">Выйти</a></h4>
  </sec:authorize>
  <sec:authorize access="hasRole('ROLE_ADMIN')">
    <h4><a href="/admin">Пользователи (АДМИНКА)</a></h4>
  </sec:authorize>
  <sec:authorize access="isAuthenticated()">
    <a href="<c:url value="/new"/>">Add Ad</a>
  </sec:authorize>


</div>


<br/>
</body>
</html>