<%--
  Created by IntelliJ IDEA.
  User: 24469
  Date: 2019/12/19
  Time: 14:42
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
    <script src="../js/teacher/new_password.js"></script>
    <title>新密码</title>
</head>
<body style="margin-top: 20px;background-color: #b2b2b2;">
<div class="layui-fluid">
    <div class="layui-card" style="width: 380px; margin: 0 auto;transform: translateY(70%); padding:10px;">
        <div class="layui-card-header">新密码</div>
        <div class="layui-form">
            <div class="layui-form-item">
                <label class="layui-form-label">工号</label>
                <div class="layui-input-inline">
                    <input id="tc_num" type="text" name="tcNum" readonly value="${forgetPasswordTeacher.tcNum}" lay-verify="number" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-inline">
                    <input type="email" name="tcEmail" readonly value="${forgetPasswordTeacher.tcEmail}" lay-verify="email" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">新密码</label>
                <div class="layui-input-inline">
                    <input id="verify_code_text" type="password" name="verifyCode" lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">再输一次</label>
                <div class="layui-input-inline">
                    <input id="verify_code_text_again" type="password" name="verifyCodeAgain" lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="button" style="width: 190px;" class="layui-btn next-step" lay-submit="" lay-filter="next_step">完成</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
