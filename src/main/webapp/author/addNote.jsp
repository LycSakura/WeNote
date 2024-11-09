<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>addNote</title>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>添加笔记</legend>
    <div class="layui-field-box">
        <form class="layui-form" method="post" id="note" name="note">
            <div class="layui-form-item">
                <label class="layui-form-label">笔记标题</label>
                <div class="layui-input-block">
                    <input name="noteTitle" class="layui-input" value="${noteForm.noteTitle}"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">笔记类别</label>
                <div class="layui-btn-container">
                    <c:forEach items="${categoryNameList}" var="categoryName">
                        <label class="layui-btn layui-btn-xs layui-bg-cyan"
                               onclick="addCategory(this)">${categoryName}</label>
                    </c:forEach>
                </div>
                <div class="layui-input-block">
                    <input name="categoryName" id="categoryName" class="layui-input" value="${noteForm.categoryName}"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">笔记标签</label>
                <div class="layui-btn-container">
                    <c:forEach items="${tagNameList}" var="tagName">
                        <label class="layui-btn layui-btn-xs layui-bg-cyan"
                               onclick="addTag(this)">${tagName}</label>
                    </c:forEach>
                </div>
                <div class="layui-input-block">
                    <input name="tagNames" id="tagNames" class="layui-input" value="${noteForm.tagNames}"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">笔记内容</label>
                <div class="layui-input-block">
                    <textarea name="noteContent" id="noteContent" style="display:none;">${noteForm.noteContent}</textarea>
                    <div id="noteContentEditor">${noteForm.noteContent}</div> <!-- 编辑器容器 -->
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="button" class="layui-btn layui-btn-sm layui-bg-cyan" onclick="saveNote()">
                        <i class="layui-icon layui-icon-add-circle">保存笔记内容</i>
                    </button>
                    <button type="reset" class="layui-btn layui-btn-sm layui-bg-cyan">
                        <i class="layui-icon layui-icon-fonts-clear">重置笔记内容</i>
                    </button>
                </div>
            </div>
        </form>
    </div>
</fieldset>

<script src="https://cdn.ckeditor.com/ckeditor5/35.3.0/classic/ckeditor.js"></script>
<script>
    let editorInstance;

    ClassicEditor
        .create(document.getElementById('noteContentEditor'), {
            toolbar: ['heading', '|', 'bold', 'italic', 'link', 'imageUpload', 'blockQuote'],
            language: 'zh-cn',
            ckfinder: {
                uploadUrl: '${pageContext.request.contextPath}/author/UploadNotePhotoServlet'
            }
        })
        .then(editor => {
            editorInstance = editor;
        })
        .catch(error => {
            console.error("Error initializing editor:", error);
        });

    // 在表单提交前同步编辑器内容到隐藏的 textarea
    function saveNote() {
        if (editorInstance) {
            document.getElementById('noteContent').value = editorInstance.getData(); // 同步数据到隐藏的 textarea
        }
        document.note.action = "${pageContext.request.contextPath}/author/SaveNoteServlet";
        document.note.submit();
    }

    function addCategory(btn) {
        document.getElementById("categoryName").value = btn.innerText;
    }

    function addTag(btn) {
        const value = btn.innerText;
        const tagNames = document.getElementById("tagNames");
        if (!tagNames.value.includes(value)) {
            tagNames.value += value + "#";
        }
    }
</script>
</body>
</html>
