<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Ads</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>

<a href=/>Back to Index</a>
<h3>Hello ${pageContext.request.userPrincipal.name}</h3>


<br/>
<br/>
<form:form action="saveUser?${_csrf.parameterName}=${_csrf.token}" method="post" modelAttribute="user" >
    <table border="0" cellpadding="5">
        <tr>
            <td><form:input value="${user.id}" path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden value="${user.id}" path="id"/></td>
            <td>Name :</td>
                <td><form:input  value="${user.username}" path="username"  /></td>



                <td>phone number: </td>
                <td><form:input  type="number" value="${user.phoneNumber}" path="phoneNumber"  /></td>



                <td>Email: </td>
                <td><form:input value="${user.email}" path="email" /></td>

        </tr>

        <tr>
        </tr>

        <tr>
            <td colspan="2"><input type="submit" value="Save"></td>
        </tr>
    </table>
</form:form>





</body>
</html>
