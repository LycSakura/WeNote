<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Misakura
  Date: 2024/11/8
  Time: 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>noteHeader</title>
</head>
<body>
<%--左侧logo--%>
<div class="layui-col-md4" style="text-align: center;">
    <div class="layui-row">
        <div class="layui-col-md2 layui-col-md-offset1 layui-anim layui-anim-rotate">
            <div class="layui-row" id="logo">
                <i class="layui-icon layui-icon-read" style="font-size: 60px;"></i>
                <br/><i style="font-size: 12px;">WeNote</i>
            </div>
        </div>
    </div>
    <div class="layui-row layui-elip" style="font-size: 28px;">
        ${empty user ? "简单就是美LESS IS　MORE" : user.brief};
        <br/>
        <i style="font-size: 18px;">
            -----By ${empty user ? "administrator" : user.nickName}
        </i>
    </div>
</div>
<%--中列导航--%>
<div class="layui-col-md5">
    <ul class="layui-nav layui-bg-gray">
        <li class="layui-nav-item">
            <a href="${pageContext.request.contextPath}/FetchAllNoteServlet" style="color: gray; font-size: 20px;">
                <i class="layui-icon layui-icon-release" style="color: black;font-size: 20px;"></i>
                学无止境
            </a>
        </li>
        <li class="layui-nav-item">
            <a href="${pageContext.request.contextPath}/IndexServlet" style="color: gray; font-size: 20px;">
                <i class="layui-icon layui-icon-release" style="color: black;font-size: 20px;"></i>
                关于系统
            </a>
        </li>
        <li class="layui-nav-item">
            <c:choose>
                <c:when test="${empty sessionScope.user}">
                    <a href="${pageContext.request.contextPath}/IndexServlet?url=/register.jsp"
                       style="color: gray; font-size: 20px;">
                        <i class="layui-icon layui-icon-add-circle" style="color: black;font-size: 20px;"></i>
                        注册
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/user/FetchMyCommentServlet"
                       style="color: gray; font-size: 20px;">
                        <i class="layui-icon layui-icon-reply-fill" style="color: black;font-size: 20px;"></i>
                        我的评论
                    </a>
                </c:otherwise>
            </c:choose>
        </li>
        >
    </ul>
</div>
<%--右侧模糊查询表单--%>
<div class="layui-col-md3">
    <form class="layui-form" action="${pageContext.request.contextPath}/FetchAllNoteByKeywordServlet">
        <div class="layui-form-mid">
            <input type="text" class="layui-input" name="keyword" lay-verify="required" placeholder="模糊查询">
        </div>
        <div class="layui-form-mid">
            <button class="layui-btn layui-bg-cyan" lay-submit>
                <i class="layui-icon layui-icon-search"></i>搜索
            </button>
        </div>
        <div class="layui-form-mid">
            <span id="today" style="color: gray; font-size: 12px;"></span>
        </div>
    </form>
</div>
<%--考试时间倒计时和logo动画--%>
<script>
    layui.use(['util', 'element'], function () {
        var util = layui.util;
        var $ = layui.jquery;
        var endTime = new Date(2099, 1, 1).getTime();// 结束日期
        var currentTime = new Date().getTime(); // 当前时间
        util.countdown(endTime, currentTime, function (leftTime, currentTime, timer) {
            var str = leftTime[0] + '天' + leftTime[1] + '时' + leftTime[2] + '分' + leftTime[3] + '秒';
            $('#today').html('距离考试时间2099年1月1号还有: ' + str);
        });
        setTimeout(function () {
            $('#logo').removeClass('layui-anim-rotate');
        }, 1000);
        $('#logo').on('click', function () {
            var othis = $(this);
            othis.addClass('layui-anim-rotate');
            setTimeout(function () {
                othis.removeClass('layui-anim-rotate');
            }, 1000);
        });
    });
</script>
</body>
</html>
