<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>New Customer</title>
</head>
<body>
<a href=/>Back to Index</a>
<h3>Hello ${pageContext.request.userPrincipal.name}</h3>
<div align="left">
    <h2>New AD</h2>
    <form:form action="save?${_csrf.parameterName}=${_csrf.token}" method="post" modelAttribute="ad" enctype="multipart/form-data">
        <table border="0" cellpadding="5">
            <tr>
                Categories :

                <form:select path="category">
                    <c:forEach var="categories" items="${categoriesList}">
                        <option><c:out value="${categories.categoryName}"/></option>
                    </c:forEach>
                </form:select>


                <td>content: </td>
                <td><form:input path="content" /></td>
                <td>phone number: </td>
                <td><form:input type="number" value="${user.phoneNumber}" path="publicPhoneNumber" /></td>
                <td>Ad days: number: </td>
                <td><form:input type="number" value="${ad.adDaysAlive}" path="adDaysAlive" /></td>

            </tr>



            <tr>
            </tr>

                <tr>
                    <td>Pick file #1:</td>
                    <td><input type="file" name="fileUpload" size="50" /></td>
                </tr>
                <tr>
                    <td>Pick file #2:</td>
                    <td><input type="file" name="fileUpload" size="50" /></td>
                </tr>

            <tr>
                <td colspan="2"><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>




</div>
</body>
</html>