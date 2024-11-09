<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Misakura
  Date: 2024/11/10
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>tagNameList</title>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>笔记标签管理(双击笔记标签名称即可编辑)</legend>
    <div class="layui-field-box">
        <c:forEach items="${tagNameList}" var="tagName">
            <blockquote class="layui-elem-quote layui-quote-nm">
                <form action="${pageContext.request.contextPath}/author/ChangeTagNameServlet" method="post"
                      class="layui-form" name="${tagName}">
                    <input type="hidden" name="oldTagName" value="${tagName}">
                    <div class="layui-form-item layui-inline">
                        <div class="layui-input-inline">
                            <input type="text" name="tagName" value="${tagName}" lay-verfify="required"
                                   class="layui-input" readonly ondblclick="this.removeAttribute('readonly')"
                                   onblur="this.setAttribute('readonly',true)">
                        </div>
                        <div class="layui-input-inline">
                            <button class="layui-btn layui-btn-sm layui-bg-cyan" lay-submit>
                                <i class="layui-icon layui-icon-edit"></i>更新
                            </button>
                        </div>
                    </div>
                </form>
            </blockquote>
        </c:forEach>
    </div>
</fieldset>
</body>
</html>
