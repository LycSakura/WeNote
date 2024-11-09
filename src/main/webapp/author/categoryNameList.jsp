<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Misakura
  Date: 2024/11/10
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>categoryNameList</title>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>笔记类别管理(双击笔记类别名称即可编辑)</legend>
    <div class="layui-field-box">
        <c:forEach items="${categoryNameList}" var="categoryName">
            <blockquote class="layui-elem-quote layui-quote-nm">
                <form action="${pageContext.request.contextPath}/author/ChangeCategoryNameServlet" method="post"
                      class="layui-form" name="${categoryName}">
                    <input type="hidden" name="oldCategoryName" value="${categoryName}">
                    <div class="layui-form-item layui-inline">
                        <div class="layui-input-inline">
                            <input type="text" name="categoryName" value="${categoryName}" lay-verfify="required"
                                   class="layui-input" readonly="true" ondblclick="this.removeAttribute('readonly')"
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
