<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>list</title>
    <style>
        * {
            box-sizing: border-box;
        }

        table, tr, td {
            border-collapse: collapse;
            border: 1px solid #000;
            text-align: center;
        }

        th {
            background-color: #d8ecf8;
            color: #6e6e6e;
        }

        tr:hover {
            background-color: rgba(126, 236, 197, 0.5);
            cursor: default;
        }
        tr:nth-child(even):hover {
            background-color: rgba(126, 236, 197, 0.5);
        }

        tr:nth-child(even) {
            background-color: rgba(210, 253, 253, 0.42);
        }
    </style>
</head>
<body>

    <h1>list</h1>
    <%-- login 안되어있으면 글쓰기 할 수 없음 --%>
    <c:if test="${sessionScope.loginUser != null}">
        <div>
            <a href="/user/logout">logout</a>
        </div>
        <div>
            <a href="/board/write">write</a>
        </div>
    </c:if>

    <c:if test="${sessionScope.loginUser == null}">
        <div>
            <a href="/user/login">login</a>
        </div>
    </c:if>

    <div>
        <c:choose>
            <c:when test="${fn:length(requestScope.list) == 0}">
                게시글이 없습니다.
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>조회수</th>
                        <th>날짜</th>
                    </tr>
                    <c:forEach items="${requestScope.list}" var="item">
                        <%-- Event Binding --%>
                        <tr class="record" data-iboard="${item.iboard}">
                            <td>${item.iboard}</td>
                            <td><c:out value="${item.title}"/></td>
                            <td>${item.writerNm}</td>
                            <td>${item.hit}</td>
                            <td>${item.mdt}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
    <script src="/res/js/board/list.js"></script>
</body>
</html>