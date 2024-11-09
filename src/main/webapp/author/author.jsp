<%--
  Created by IntelliJ IDEA.
  User: Misakura
  Date: 2024/11/8
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>author</title>
</head>
<body>
    <div class="layui-card">
        <div class="layui-card-header">
            <i class="layui-icon layui-icon-set"></i>
            后台管理
        </div>
        <div class="layui-card-body layui-btn-container">
            <a class="layui-btn layui-bg-gray" href="${pageContext.request.contextPath}/author/AddNoteServlet">添加笔记</a>
            <a class="layui-btn layui-bg-gray" href="${pageContext.request.contextPath}/author/FetchAllCategoryNameServlet">笔记类别管理</a>
            <a class="layui-btn layui-bg-gray" href="${pageContext.request.contextPath}/author/FetchAllTagNameServlet">笔记标签管理</a>
            <a class="layui-btn layui-bg-gray" href="">正在升级</a>
        </div>
    </div>
</body>
</html>
