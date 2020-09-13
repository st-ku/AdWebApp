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
<html>
<body>
<c:if test="${!empty listAds}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Username</th>
            <th width="120">Category</th>
            <th width="120">Content</th>
            <th width="120">Date</th>
            <th width="120">Available</th>
            <th width="120">Photo</th>


        </tr>
        <c:forEach items="${listAds}" var="ad">
            <tr>
                <td>${ad.adId}</td>
                <td>${ad.user.username}</td>
                <td>${ad.adCategory}</td>
                <td><a href="ad_data?id=${ad.adId}">${ad.content}</a></td>
                <td>${ad.date}</td>
                <td>${ad.available}</td>
                <td>Is ${ad.numberOfFiles} files inside</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
