<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Misakura
  Date: 2024/11/8
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>noteCard</title>
</head>
<body>
<%--登录或个人简介面板--%>
<div class="layui-card">
    <c:choose>
        <c:when test="${empty sessionScope.user}">
            <jsp:include page="login.jsp"></jsp:include>
        </c:when>
        <c:otherwise>
            <jsp:include page="/user/profile.jsp"></jsp:include>
        </c:otherwise>
    </c:choose>
</div>
<%--后台管理面板--%>
<c:if test="${not empty sessionScope.user}">
    <jsp:include page="/author/author.jsp"></jsp:include>
</c:if>
<%--笔记类别列表面板--%>
<div class="layui-card">
    <div class="layui-card-header">
        <i class="layui-icon-table"></i>
        笔记类别
    </div>
    <div class="layui-card-body layui-btn-container">
        <c:forEach items="${categoryNameMap}" var="category">
            <a href="${pageContext.request.contextPath}/FetchAllNoteByCategoryNameServlet?categoryName=${category.key}" class="layui-btn layui-bg-gray">
                    ${category.key}
                <span class="layui-badge">${category.value}</span>
            </a>
        </c:forEach>>
    </div>
</div>
<%--笔记标签列表面板--%>
<div class="layui-card">
    <div class="layui-card-header">
        <i class="layui-icon-table"></i>
        笔记标签
    </div>
    <div class="layui-card-body layui-btn-container">
        <c:forEach items="${tagNameMap}" var="tag">
            <a href="${pageContext.request.contextPath}/FetchAllNoteByTagNameServlet?tagName=${tag.key}" class="layui-btn layui-bg-gray">
                    ${tag.key}
                <span class="layui-badge">${tag.value}</span>
            </a>
        </c:forEach>>
    </div>
</div>
<%--其它功能面板--%>
<div class="layui-card">
    <div class="layui-card-header">
        <i class="layui-icon layui-icon-link"></i>
        资源分享
    </div>
    <div class="layui-card-body layui-btn-container">
        <a class="layui-btn layui-bg-gray " href="">资源分享1</a>
        <a class="layui-btn layui-bg-gray " href="">资源分享2</a>
    </div>
</div>
</body>
</html>
