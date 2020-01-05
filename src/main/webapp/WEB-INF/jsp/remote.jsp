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
<div class="hidden" id="status"><a id="statustext"></a><a id="close" href="javascript:void(0)">X</a></div>
<h1 class="title">Remote Control</h1>
<hr>
<div class="container">
<c:forEach items="${senderliste}" var="sender">
<div class="item">
<form action="#${sender[0]}" th:action="@{/remote}" th:object="${remoteHandler}" method="post">
    <input type="hidden" value="${sender[1]}" name="url" th:field="*{url}" />
    <input type="submit" class="senderbutton" value="${sender[0].toUpperCase()}" />
</form>
</div>
</c:forEach>
    <div class="item">
        <form action="#" th:action="@{/remote}" th:object="${remoteHandler}" method="post">
            <input type="hidden" value="closePlayer" name="url" th:field="*{url}" />
            <input type="submit" value="Close player" style="margin-top: 30px;"/>
        </form>
    </div>
    <div class="item">
        <form action="#" th:action="@{/remote}" th:object="${remoteHandler}" method="post">
            <input type="hidden" value="killSpring" name="url" th:field="*{url}" />
            <input type="submit" id="killerbutton" value="Shutdown" />
        </form>
    </div>
</div>
</body>
</html>