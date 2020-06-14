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

<!-- <a href=nextAds?category=${category}&id=${id}&id2=${id2}>NEXT 10 records</a>
-->
<form action="search_ads" method="post">

    <input name="action" value=""/>
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>

    <button type="submit">Search</button>

</form>







<td>Categories :</td>
<a href=ads?category=All&id=${id}&id2=${id2}>ALL</a>
<a href=ads?category=Common&id=${id}&id2=${id2}>Common</a>
<a href=ads?category=Auto&id=${id}&id2=${id2}>Auto</a>
<a href=ads?category=Tech&id=${id}&id2=${id2}>Tech</a>
<a href=ads?category=Realt&id=${id}&id2=${id2}>Realt</a>






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
            <th width="120">Photo</th>


        </tr>
        <sec:authorize access="hasRole('ROLE_USER')">
        <c:forEach items="${listAds}" var="ad">
            <c:if test="${ad.available}">
                <td>${ad.adId}</td>
                <td><a href="user_ads?id=${ad.user.id}">${ad.user.username}</a></td>
                <td>${ad.adCategory.categoryName}</td>
                <td><a href="ad_data?id=${ad.adId}">${ad.content}</a></td>
                <td>${ad.date}</td>
                <td>Is ${ad.numberOfFiles} files inside</td>
            </tr>
            </c:if>
        </c:forEach>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')">
            <c:forEach items="${listAds}" var="ad">
                    <td>${ad.adId}</td>
                    <td><a href="user_ads?id=${ad.user.id}">${ad.user.username}</a></td>
                    <td>${ad.adCategory.categoryName}</td>
                    <td><a href="ad_data?id=${ad.adId}">${ad.content}</a></td>
                    <td>${ad.date}</td>
                    <td>Is ${ad.numberOfFiles} files inside</td>
                    </tr>
            </c:forEach>
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
            <c:forEach items="${listAds}" var="ad">
                <c:if test="${ad.available}">
                    <td>${ad.adId}</td>
                    <td><a href="user_ads?id=${ad.user.id}">${ad.user.username}</a></td>
                    <td>${ad.adCategory.categoryName}</td>
                    <td><a href="ad_data?id=${ad.adId}">${ad.content}</a></td>
                    <td>${ad.date}</td>
                    <td>Is ${ad.numberOfFiles} files inside</td>
                    </tr>
                </c:if>
            </c:forEach>
        </sec:authorize>
    </table>
</c:if>



</body>
</html>
