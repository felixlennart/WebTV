<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="/css/guidestyle.css" rel="stylesheet">
<script src="/js/guidescript.js"></script>

<div class="headline">
    <a href="javascript:void(0)" onclick="insertGuide();" style="text-decoration: none; float: left;">&#128472;</a>
    &nbsp;<img src="/media/loading.gif" class="loading hidden"/>&nbsp;
    <a href="javascript:void(0)" class="closeguide">&#128473;</a>
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