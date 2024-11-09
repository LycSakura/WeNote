<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Misakura
  Date: 2024/11/9
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>readNote</title>
</head>
<body>
<div class="layui-card">
    <div class="layui-card-header" style="font-size: 32px;">
        ${note.noteTitle}
        <c:if test="${user.isAuthor=='Y'}">
            <a class="layui-btn layui-layout-right"
               href="${pageContext.request.contextPath}/author/EditNoteServlet?noteID=${note.noteID}">编辑笔记</a>
        </c:if>
    </div>
    <div class="layui-card-body">
        <span class="layui-badge layui-bg-gray">作者: ${note.author}</span> |
        <span class="layui-badge layui-bg-gray">发布时间: ${note.createTime}</span> |
        <c:if test="${not empty note.updateTime}">
            <span class="layui-badge layui-bg-gray">修改时间: ${note.updateTime}</span> |
        </c:if>
        <span class="layui-badge layui-bg-gray">浏览次数: ${note.visit}</span> |
        <span class="layui-badge layui-bg-gray">笔记类别: ${note.categoryName}</span>
        <hr class="layui-bg-gray"/>
        笔记标签:
        <c:forEach items="${tagNameList}" var="tagName">
            <span class="layui-badge layui-bg-blue">${tagName}</span>
        </c:forEach>
        <hr class="layui-bg-gray"/>
        ${note.noteContent}
        <hr class="layui-bg-green"/>
        上一篇:
        <a href="${pageContext.request.contextPath}/ReadNoteServlet?noteID=${lastNote.noteID}">${lastNote.noteTitle}</a>
        <hr class="layui-bg-green"/>
        下一篇:
        <a href="${pageContext.request.contextPath}/ReadNoteServlet?noteID=${nextNote.noteID}">${nextNote.noteTitle}</a>
    </div>
    <hr class="layui-bg-cyan"/>
    <div class="layui-card-header" style="font-size: 20px">评论列表</div>
    <div class="layui-card-body">
        <c:forEach items="${commentList}" var="comment">
            <li class="layui-timeline-item">
                <img id="photo" class="layui-timeline-axis" title="${comment.user.brief}"
                     src="${pageContext.request.contextPath}/ShowUserPhotoServlet?fileName=${comment.user.photo}"/>
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">
                        <span class="layui-badge layui-bg-gray">用户名: ${comment.userName}</span>
                        <span class="layui-badge layui-bg-gray">呢称: ${comment.user.nickName}</span>
                        <span class="layui-badge layui-bg-gray">用户名: ${comment.createTime}</span>
                        <span class="layui-badge layui-bg-gray">用户名: ${comment.remoteIP}</span>
                    </h3>
                    <div>
                        <fieldset class="layui-elem-field">
                            <legend>${comment.commentTitle}</legend>
                            <div class="layui-field-box">${comment.commentContent}</div>
                        </fieldset>
                    </div>
                </div>
            </li>
        </c:forEach>
    </div>
</div>
<hr class="layui-bg-green"/>
<div class="layui-card">
    <div class="layui-card-header" style="font-size: 20px;">登录后，可发表评论</div>
    <div class="layui-card-body">
        <c:if test="${not empty user}">
            <form class="layui-form" action="${pageContext.request.contextPath}/user/SaveCommentServlet" method="post">
                <input type="hidden" name="noteID" value="${note.noteID}">
                <input name="commentTitle" lay-verify="required" placeholder="请输入评论的标题" class="layui-input"
                       value="${commentForm.commentTitle}"/>
                <textarea id="commentContent" name="commentContent">${commentForm.commentContent}</textarea>
                <button type="submit" class="layui-btn layui-bg-green">发表评论</button>
                <c:remove var="commentForm"/>
            </form>
        </c:if>
    </div>
</div>
<script src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/resources/ckeditor/zh-cn.js"></script>
<script>
    var commentContent = ClassicEditor.create(document.querySelector('#commentContent'), {
        toolbar: ['heading', '|', 'bold', 'italic', 'link', 'blockQuote'],
        language: 'zh-cn',
    });
</script>
</body>
</html>
