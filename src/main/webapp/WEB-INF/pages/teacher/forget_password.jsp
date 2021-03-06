<%--
  Created by IntelliJ IDEA.
  User: 24469
  Date: 2019/12/19
  Time: 9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../layui/css/layui.css" media="all">
    <link rel="icon" href="../images/favicon.ico">
    <script src="../layui/layui.js"></script>
    <script src="../js/teacher/forget_password.js"></script>
    <title>忘记密码</title>
</head>
<body style="margin-top: 20px;background-color: #b2b2b2;">
    <div class="layui-fluid">
        <div class="layui-card" style="width: 380px; margin: 0 auto;transform: translateY(70%); padding:10px;">
            <div class="layui-card-header">忘记密码</div>
            <div class="layui-form">
                <div class="layui-form-item">
                    <label class="layui-form-label">工号</label>
                    <div class="layui-input-inline">
                        <input id="tc_num" type="text" name="tcNum" lay-verify="number" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">邮箱</label>
                    <div class="layui-input-inline">
                        <input type="text" name="tcEmail" lay-verify="email" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">验证码</label>
                    <div class="layui-input-inline" style="width: 80px;">
                        <input id="verify_code_text" type="text" name="verifyCode" lay-verify="required" autocomplete="off" maxlength="4" class="layui-input">
                    </div>
                    <div>
                        <img id="img_base64" style="width: 100px;height: 38px; border: 1px dashed #e2e2e2;">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button type="button" style="width: 190px;" class="layui-btn next-step" lay-submit="" lay-filter="next_step">下一步</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
