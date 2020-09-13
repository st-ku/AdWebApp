<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

<br/>
<br/>

<c:if test="${!empty listAds}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Username</th>
            <th width="120">Category</th>
            <th width="120">Content</th>
            <th width="120">Date</th>
            <th width="120">Phone</th>
            <th width="120">Available</th>
            <th width="120">Photo</th>


        </tr>
        <c:forEach items="${listAds}" var="ad">
            <tr>
                <td>${ad.adId}</td>
                <td><a href="user_ads?id=${ad.user.id}">${ad.user.username}</a></td>
                <td>${ad.adCategory}</td>
                <td><a href="ad_data?id=${ad.adId}">${ad.content}</a></td>
                <td>${ad.date}</td>
                <td>${ad.publicPhoneNumber}</td>
                <td>${ad.available}</td>
                <td>Is ${ad.numberOfFiles} files inside</td>
                <td><a href="edit?id=${ad.adId}">Edit</a></td>
                <td><a href="delete?id=${ad.adId}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>


<form:form action="saveEdited?${_csrf.parameterName}=${_csrf.token}" method="post" modelAttribute="ad" >
    <table border="0" cellpadding="5">
        <tr>

            <c:if test="${!empty ad.adId}">
                <h1>Edit :</h1>
            <td><form:input value="${ad.adId}" path="adId" readonly="true" size="8" disabled="true"/>
                <form:hidden value="${ad.adId}" path="adId"/></td>
            </c:if>

            <c:if test="${!empty ad.adId}">
                Category:
                <form:select path="category">
                    <c:forEach var="categories" items="${categoriesList}">
                        <option><c:out value="${categories.categoryName}"/></option>
                    </c:forEach>
                </form:select>
            </c:if>
            <td>content: </td>
            <c:if test="${!empty ad.adId}">
            <td><form:input  value="${ad.content}" path="content"  /></td>
            </c:if>
            <td>phone number: </td>
            <c:if test="${!empty ad.adId}">
            <td><form:input type="number" value="${ad.publicPhoneNumber}" path="publicPhoneNumber" /></td>
            </c:if>
            <td>ad days: </td>
            <c:if test="${!empty ad.adId}">
                <td><form:input type="number" value="${ad.adDaysAlive}" path="adDaysAlive" /></td>
            </c:if>
            <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')">
            <c:if test="${!empty ad.adId}">
                <td><form:checkbox path="available" value="${ad.available}"/></td>
            </c:if>
            </sec:authorize>

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
