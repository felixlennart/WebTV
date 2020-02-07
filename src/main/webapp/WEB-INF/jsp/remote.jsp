<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8" />
    <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' />
    <title>WebTV Remote Control</title>
    <link href="/css/main.css" rel="stylesheet">
    <script src="/js/main.js"></script>
</head>
<body>
<div class="blackbox hidden"></div>
<div id="fullguide" class="hidden">
    <a href="javascript:void(0)" class="closeguide">&#128473;</a>
    <br>
    <div style="width: 90vw; height: 90vh; text-align: center; vertical-align: center">
        <img src="/media/loading.gif"/>
    </div>

</div>
<div class="hidden" id="status"><a id="statustext"></a><a id="close" href="javascript:void(0)">X</a></div>
<h1 class="title">Remote Control <a href="javascript:void(0)" id="openguide">&#128712;</a></h1>
<hr>
<div class="container">
<c:forEach items="${senderliste}" var="sender">
<div class="item">
<form action="#${sender[0]}" th:action="@{/remote}" th:object="${remoteHandler}" method="post">
    <input type="hidden" value="${sender[1]}" name="url" th:field="*{url}" />
    <input type="submit" id="${sender[1]}" class="senderbutton" name="sender" value="${sender[0].toUpperCase()}" />
</form>
</div>
</c:forEach>
    <div class="item">
        <form action="#" th:action="@{/remote}" th:object="${remoteHandler}" method="post">
            <input type="hidden" value="closePlayer" name="url" th:field="*{url}" />
            <input type="submit" name="sender" value="Close player" style="margin-top: 30px;"/>
        </form>
    </div>
    <div class="item">
        <form action="#" th:action="@{/remote}" th:object="${remoteHandler}" method="post">
            <input type="hidden" value="killSpring" name="url" th:field="*{url}" />
            <input type="submit" name="sender" id="killerbutton" value="Shutdown" />
        </form>
    </div>
</div>
<div id="guide" class="hidden">
    <span id="guidecontent">${guide}</span>
</div>
</body>
</html>