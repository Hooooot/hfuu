<%--
  Created by IntelliJ IDEA.
  User: 24469
  Date: 2019/12/19
  Time: 9:20
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
    <script src="../js/teacher/change_password.js"></script>
    <title>修改密码</title>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card" style="margin-top: 20px;">
        <div class="layui-card-header">修改密码</div>
        <div class="layui-form">
            <div class="layui-form-item">
                <label class="layui-form-label">工号</label>
                <div class="layui-input-inline">
                    <input id="tc_num" type="text" name="tcNum" readonly value="${teacher.tcNum}" lay-verify="number" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">原密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="oldPwd" lay-verify="required" class="layui-input">
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
                    <button type="button" style="width: 190px;" class="layui-btn next-step" lay-submit="" lay-filter="submit_pwd">完成</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
