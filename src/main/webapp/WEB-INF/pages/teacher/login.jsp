<%--
  Created by IntelliJ IDEA.
  User: 24469
  Date: 2019/9/17
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>先登录</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <script src="layui/layui.js"></script>
    <script src="js/teacher/login.js"></script>
    <link rel="icon" href="images/favicon.ico">
</head>
<body class="layui-main">
<form class="layui-form" style="width: 350px; margin: 0 auto;">
    <div class="layui-form-item">
        <label class="layui-form-label" for="name">用户名</label>
        <div class="layui-input-inline">
            <input id="name" class="layui-input" type="text" name="name" placeholder="请输入用户名">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label" for="pw">密码</label>
        <div class="layui-input-inline">
            <input id="pw" class="layui-input" type="password" name="pw" placeholder="请输入密码" autocomplete="off">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <input lay-skin="primary" type="checkbox" title="记住密码" name="remember_pw" checked>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <input id="submit" class="layui-btn" style="width: 150px;" type="button" value="登录">
        </div>
    </div>
</form>
</body>
</html>