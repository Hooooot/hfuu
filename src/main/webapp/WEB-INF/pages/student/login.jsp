<%--
  Created by IntelliJ IDEA.
  User: 16688
  Date: 2019/9/13
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="layui/css/layui.css">
    <link rel="stylesheet" href="layui/css/login.css">
    <link rel="icon" href="/favicon.ico">
    <title>登录</title>
</head>
<body class="login-wrap">
    <div class="login-container">
        <form class="login-form">
            <div class="input-group">
                <input type="text" id="username" class="input-field">
                <label for="username" class="input-label">
                    <span class="label-title">用户名</span>
                </label>
            </div>
            <div class="input-group">
                <input type="password" id="password" class="input-field">
                <label for="password" class="input-label">
                    <span class="label-title">密码</span>
                </label>
            </div>
            <button type="button" class="login-button">登录<i class="ai ai-enter"></i></button>
        </form>
    </div>
</body>
<script src="layui/layui.js"></script>
<script src="js/student/login.js"></script>
</html>