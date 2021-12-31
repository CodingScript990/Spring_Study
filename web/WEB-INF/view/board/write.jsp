<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Write</title>
</head>
<body>
    <h1>Write</h1>
    <div>${requestScope.msg}</div>
    <form action="/board/write" method="post">
        <div><input type="text" name="title" placeholder="title" value="${requestScope.data.title}"></div>
        <div><textarea name="ctnt" placeholder="content">${requestScope.data.ctnt}</textarea></div>
        <div>
            <input type="submit" value="save">
        </div>
    </form>
</body>
</html>