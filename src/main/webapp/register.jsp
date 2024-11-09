<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Misakura
  Date: 2024/11/8
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register</title>
</head>
<body>
<fieldset>
    <legend>用户注册</legend>
    <div class="layui-field-box">
        <form class="layui-form" method="post" action="${pageContext.request.contextPath}/RegisterUserServlet">
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-inline">
                    <input name="userName" lay-verify="required" class="layui-input" value="${userForm.userName}"
                           onblur="msg(this)"/>
                </div>
                <div class="layui-form-mid" id="userNameCheckedMessage"></div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">昵称</label>
                <div class="layui-input-inline">
                    <input name="nickName" lay-verify="required" class="layui-input" value="${userForm.nickName}"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="password" lay-verify="required" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">确认密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="confirmPassword" lay-verify="required" class="layui-input"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">电话号码</label>
                <div class="layui-input-inline">
                    <input name="telephone" lay-verify="required|phone" class="layui-input"
                           value="${userForm.telephone}"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">个人简介</label>
                <div class="layui-input-inline">
                    <textarea name="brief" lay-verify="required" class="layui-textarea"
                              placeholder="座右铭,不在于多而在于精">
                        ${userForm.brief}
                    </textarea>
                </div>
            </div>
            <c:remove var="userForm"/>
            <div class="layui-form-item">
                <label class="layui-form-label">
                    <img id="checkCode" onclick="changeCheckCode(this)"
                         src="${pageContext.request.contextPath}/CheckCodeServlet"/>
                </label>
                <div class="layui-input-inline">
                    <input type="text" name="checkCodeInput" lay-verify="required" class="layui-input"
                           placeholder="验证码"/>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button lay-submit class="layui-btn layui-btn-sm layui-bg-cyan">
                        <i class="layui-icon layui-icon-add-circle">注册</i>
                    </button>
                    <button type="reset" class="layui-btn layui-btn-sm layui-bg-cyan">
                        <i class="layui-icon layui-icon-fonts-clear">重新输入</i>
                    </button>
                </div>
            </div>
        </form>
    </div>
</fieldset>
<script type="text/javascript">
    layui.use('form', function () {
    });
    var msg = function (input) {
        if (input.value.trim() === "") {
            return;
        }
        var request = new XMLHttpRequest();
        var userName = encodeURI(input.value);
        var path = "${pageContext.request.contextPath}/CheckUserNameServlet?userName=";
        request.open("GET", path + userName);
        request.send();
        request.onreadystatechange = function () {
            if (request.readyState === 4 && request.status === 200) {
                var text = request.responseText;
                var userNameCheckedMessage = document.getElementById("userNameCheckedMessage");
                userNameCheckedMessage.innerHTML = text;
            }
        }
    }
    var changeCheckCode = function (img) {
        var date = new Date().getTime();
        img.src = "${pageContext.request.contextPath}/CheckCodeServlet?" + date;
    }
</script>
</body>
</html>
