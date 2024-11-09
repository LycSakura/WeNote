<%--
  Created by IntelliJ IDEA.
  User: Misakura
  Date: 2024/11/8
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>changePassword</title>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>修改密码</legend>
    <div class="layui-field-box">
        <form class="layui-form" method="post" action="${pageContext.request.contextPath}/user/ChangePasswordServlet">
            <label class="layui-form-label">旧密码</label>
            <div class="layui-input-inline">
                <input type="password" name="oldPassword" lay-verify="required" class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="password" lay-verify="required" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">确认密码</label>
                <div class="layui-input-inline">
                    <input class="layui-input" type="password" name="confirmPassword" lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button lay-submit class="layui-btn layui-btn-radius layui-btn-sm layui-bg-cyan">
                        <i class="layui-icon layui-icon-set-fill"></i>确认修改
                    </button>
                </div>
            </div>
        </form>
    </div>
</fieldset>
</body>
</html>
