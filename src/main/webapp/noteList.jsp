<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Misakura
  Date: 2024/11/9
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>noteList</title>
</head>
<body>
<fieldset>
    <legend>笔记列表</legend>
    <div class="layui-field-box">
        <ui class="layui-timeline">
            <c:forEach items="${noteList}" var="note">
                <li class="layui-timeline-item">
                    <i class="layui-icon layui-icon-date layui-timeline-axis"></i>
                    <div class="layui-timeline-content layui-text">
                        <h3 class="layui-timeline-title">${note.createTime}</h3>
                        <fieldset class="layui-elem-field">
                            <legend>${note.noteTitle}</legend>
                            <div class="layui-field-box">
                                <span class="layui-badge layui-bg-gray">浏览次数: ${note.visit}</span>
                                <span class="layui-badge layui-bg-gray">笔记类别: ${note.categoryName}</span>
                                <a class="layui-btn layui-btn-xs layui-bg-gray" target="_blank"
                                   href="${pageContext.request.contextPath}/ReadNoteServlet?noteID=${note.noteID}">
                                    阅读笔记全文
                                </a>
                            </div>
                        </fieldset>
                    </div>
                </li>
            </c:forEach>
            <li class="layui-timeline-item">
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">过去</h3>
                </div>
            </li>
        </ui>
    </div>
</fieldset>
</body>
</html>
