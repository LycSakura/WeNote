<%--
  Created by IntelliJ IDEA.
  User: Misakura
  Date: 2024/11/8
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
    <title>个人笔记系统WeNote</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
</head>
<body bgcolor="#FCFCFC">
<div class="layui-row layui-bg-gray">
    <jsp:include page="noteHeader.jsp"></jsp:include>
</div>
<div class="layui-container" style="padding: 10px;">
    <div class="layui-row layui-col-space10">
        <div class="layui-col-md9">
            <jsp:include page="noteMain.jsp"></jsp:include>
        </div>
        <div class="layui-col-md3">
            <jsp:include page="noteCard.jsp"></jsp:include>
        </div>
    </div>
</div>
<hr class="layui-bg-cyan" style="height: 5px;">
<div class="layui-row layui-bg-cyan" style="text-align: center">
    <jsp:include page="noteFooter.jsp"></jsp:include>
</div>
<hr class="layui-bg-cyan" style="height: 10px;">
</body>
<script>
    layui.use(['util', 'layer'], function () {
        var layer = layui.layer;
        var flashMsgs = [];
        <c:forEach items="${sessionScope.flashMsgs}" var="msg">
        flashMsgs.push("${msg}");
        </c:forEach>
        if (flashMsgs.length > 0) {
            layer.msg(flashMsgs.join("\n"), {time: 0, closeBtn: 2});
            // 移除 session 中的提示信息
            <c:remove var="flashMsgs" scope="session"/>
        }
        var util = layui.util;
        util.fixbar({
            bar1: '赏',
            bar2: '赞',
            click: function (type) {
                if (type === 'bar1') {
                    layer.open({
                        type: 1,
                        title: '赏',
                        offset: ['120px', 'font-size:18px'],
                        content: '<img src="${pageContext.request.contextPath}/ShowUserPhotoServlet?fileName=z.jpg" width="300px" />'
                    })
                }
                if (type === 'bar2') {
                    layer.open({
                        type: 1,
                        offset: '120px',
                        title: ['赞', 'font-size:18px'],
                        content: '<img src="${pageContext.request.contextPath}/ShowUserPhotoServlet?fileName=s.jpg" width="300px" />'
                    })
                }
                if (type === 'top') {
                    layer.msg("扶摇直上九万里!");
                }
            }
        })
    });
</script>
</html>
