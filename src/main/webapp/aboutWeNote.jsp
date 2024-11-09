<%--
  Created by IntelliJ IDEA.
  User: Misakura
  Date: 2024/11/8
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>aboutWeNote</title>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title">
    <legend>开发本系统的时间线</legend>
    <div class="layui-field-box">
        <ul class="layui-timeline">
            <%--时间节点--%>
            <li class="layui-timeline-item">
                <i class="layui-icon layui-icon-date layui-timeline-axis"></i>
                <%--内容--%>
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">2020年5月8日</h3>
                    <%--文字--%>
                    <div>
                        <blockquote class="layui-elem-quote">发布之弦,一触即发</blockquote>
                    </div>
                </div>
            </li>
            <%--时间节点--%>
            <li class="layui-timeline-item">
                <i class="layui-icon layui-icon-date layui-timeline-axis"></i>
                <%--内容--%>
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">2020年2月8日</h3>
                    <%--文字--%>
                    <div>
                        <blockquote class="layui-elem-quote">一切以准备就绪!</blockquote>
                    </div>
                    <ul>
                        <li>因小而大</li>
                        <li>因强而弱</li>
                    </ul>
                </div>
            </li>
            <%--时间节点--%>
            <li class="layui-timeline-item">
                <i class="layui-icon layui-icon-date layui-timeline-axis"></i>
                <%--内容--%>
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">2019年12月8日</h3>
                    <%--文字--%>
                    <div>
                        <fieldset class="layui-elem-field">
                            <legend>我是最有底线的</legend>
                            <div class="layui-field-box">不枉百余日夜为之为伴</div>
                        </fieldset>
                    </div>
                </div>
            </li>
            <%--时间节点--%>
            <li class="layui-timeline-item">
                <i class="layui-icon layui-icon-date layui-timeline-axis"></i>
                <%--内容--%>
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">2019年7月8日</h3>
                    <%--文字--%>
                    <div>
                        <fieldset class="layui-elem-field">
                            <legend>我是没有底线的</legend>
                            <div class="layui-field-box">无论能走多远,至少曾倾注全心!</div>
                        </fieldset>
                    </div>
                </div>
            </li>
            <%--时间节点--%>
            <li class="layui-timeline-item">
                <i class="layui-icon layui-icon-circle-dot layui-timeline-axis"></i>
                <%--内容--%>
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">过去</h3>
                </div>
            </li>
        </ul>
    </div>
</fieldset>
</body>
</html>
