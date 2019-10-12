<%--
  Created by IntelliJ IDEA.
  User: 浅忆
  Date: 2019/9/28
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改个管理员个人信息</title>
    <link rel="stylesheet" href="../layui/css/layui.css"/>
    <style>
        body{padding:25px 25px 0 0; font-size:14px; background:#fff; width:90%; margin:0 auto; font-size:14px; line-height:20px; overflow:hidden;}
        p{margin-bottom:10px;}
        input{border:1px solid #999; padding:5px 10px; margin:0 10px 10px 0;}
        .layui-form-select dl { max-height:180px; }
    </style>
</head>
<body>
<form class="layui-form" method="post" enctype="multipart/form-data" id="updateAdminForm">
    <input type="hidden" name="adminId" value="${admin.adminId}">
    <div class="layui-form-item">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-inline"><input class="layui-input" name="adminName" value="${admin.adminName}" autocomplete="off"></div>
        <label class="layui-form-label">工号</label>
        <div class="layui-input-inline"><input class="layui-input" name="adminNum" value="${admin.adminNum}" autocomplete="off" readonly></div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-inline">
            <input type="radio" name="adminSex" value="男" title="男" <c:if test="${admin.adminSex eq '男'}">checked</c:if> >
            <input type="radio" name="adminSex" value="女" title="女" <c:if test="${admin.adminSex eq '女'}">checked</c:if> >
        </div>
        <label class="layui-form-label">头像</label>
        <div class="layui-input-inline"><input type="file" name="avatarPath" style="border: 0px" value="${admin.adminAvatar}"></div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">手机</label>
        <div class="layui-input-block"><input class="layui-input" name="adminPhone" value="${admin.adminPhone}" autocomplete="off" required></div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" type="button" id="editButton">确认修改</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</body>

<script src="../layui/layui.js"></script>
<script type="text/javascript" src="../js/admin/jquery.min.js"></script>
<script>
    layui.use('form', function(){
        var form = layui.form;
    });
</script>
<script type="text/javascript">
    $(function() {
        $("#editButton").click(
            function(){
                var formDate = new FormData($("#updateAdminForm")[0]);
                $.ajax({
                    type: "POST",
                    dataType: "text",
                    cache: false,   //上传文件无需缓存
                    url: "${adm}/editAdminInfoSubmit",
                    data: formDate,
                    cacheL: false,
                    async: true,
                    data: formDate,
                    processData: false, //告诉jQuery不要去处理发送的数据
                    contentType: false, //告诉jQuery不要去设置请求头
                    success: function (message) {
                        if (message == "success") {
                            parent.layer.msg('修改个人信息成功！',{icon:1})
                            self.setInterval(function(){parent.location.reload()},1500);
                        }
                        else {
                            parent.layer.msg('修改失败！',{icon:0})
                            self.setInterval(function(){parent.location.reload()},1500);
                        }
                    },
                    error : function() {
                        alert("数据异常！");
                    }
                });
            }
        );
    });
</script>
</html>
