<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><c:out value="${requestScope.data.title}" /></title>
</head>
<body>
    <div><a href="/board/list">Home</a></div>

    <c:if test="${sessionScope.loginUser.iuser == requestScope.data.writer}">
        <div id="btnContainer" data-iboard="${requestScope.data.iboard}">
            <a href="/board/mod?iboard=${requestScope.data.iboard}"><button>mod</button></a>
            <button id="btnDel">delete</button>
        </div>
    </c:if>

    <%-- 에러 발생시! --%>
    <div>${requestScope.msg}</div>

    <div>
        <div>Number : ${requestScope.data.iboard}</div>
        <div>Title : <c:out value="${requestScope.data.title}" /></div>
        <div>Content : <c:out value="${requestScope.data.ctnt}" /></div>
        <div>Writer : <c:out value="${requestScope.data.writerNm}" /></div>
        <div>Hit : ${requestScope.data.hit}</div>
        <div>Rdt : ${requestScope.data.rdt}</div>
        <div>Mdt : ${requestScope.data.mdt}</div>
    </div>
    <script src="/res/js/board/detail.js"></script>
</body>
</html>