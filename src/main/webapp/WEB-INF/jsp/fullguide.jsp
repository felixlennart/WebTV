<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="${pageContext.request.contextPath}/css/guidestyle.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/guidescript.js"></script>

<div class="headline">
    <a href="javascript:void(0)" onclick="insertGuide();" style="text-decoration: none; float: left;"><img src="${pageContext.request.contextPath}/media/refresh.svg" class="iconimg"/></a>
    &nbsp;<img src="${pageContext.request.contextPath}/media/loading.gif" class="loading hidden"/>&nbsp;
    <a href="javascript:void(0)" class="closeguide"><img src="${pageContext.request.contextPath}/media/close.svg" class="iconimg"/></a>
</div>
<hr/>
<table id="guidetable">
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
            <td class="sendercell">
                <c:out value="${sender[0].toUpperCase()}"></c:out>
            </td>
            <td class="programcell">
                <c:out value="${sender[1]}"></c:out>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>