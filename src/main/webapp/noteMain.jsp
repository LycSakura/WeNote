<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Misakura
  Date: 2024/11/8
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>noteMain</title>
</head>
<body>
    <div class="layui-col-md9">
        <c:choose>
            <c:when test="${empty param.url}">
                <jsp:include page="/aboutWeNote.jsp"></jsp:include>
            </c:when>
            <c:otherwise>
                <jsp:include page="${param.url}"></jsp:include>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
