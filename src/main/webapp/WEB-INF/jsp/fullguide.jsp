<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8"/>
    <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'/>
    <title>WebTV Remote Control</title>
    <link href="/css/guidestyle.css" rel="stylesheet">
    <script src="/js/guidescript.js"></script>
</head>
<body>
<table>
    <tbody>
    <tr>
        <td>
            Channel
        </td>
        <td>
            Guide
        </td>
    </tr>
    <c:forEach var="sender" items="${guideData}">
        <tr>
            <td>
                <c:out value="${sender[0].toUpperCase()}"></c:out>
            </td>
            <td>
                <c:out value="${sender[1]}"></c:out>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>