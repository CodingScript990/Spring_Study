<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Join</title>
</head>
<body>
    <h1>Join</h1>
    <div>
        <form action="/user/join" method="post">
            <div><input type="text" name="uid" placeholder="id"></div>
            <div><input type="password" name="upw" placeholder="password"></div>
            <div><input type="text" name="nm" placeholder="name"></div>
            <div>
                <label>Man<input type="radio" name="gender" value="1" checked></label>
                <label>Woman<input type="radio" name="gender" value="2"></label>
            </div>
            <div><input type="submit" value="Join"></div>
        </form>
    </div>
</body>
</html>