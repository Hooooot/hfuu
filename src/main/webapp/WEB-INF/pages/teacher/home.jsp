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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>教师界面</title>
    <link rel="stylesheet" href="../layui/css/layui.css">
    <link rel="stylesheet" href="../css/teacher/home.css">
    <script src="../layui/layui.js"></script>
    <script src="../js/teacher/home.js"></script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin" style="width: 100%; height: 100%;">
    <div class="layui-header">
        <div class="layui-logo">作业系统教师端</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <%--            <li class="layui-nav-item"><a href="">控制台</a></li>--%>
            <%--            <li class="layui-nav-item"><a href="">商品管理</a></li>--%>
            <%--            <li class="layui-nav-item"><a href="">用户</a></li>--%>
            <%--            <li class="layui-nav-item">--%>
            <%--                <a href="javascript:;">其它系统</a>--%>
            <%--                <dl class="layui-nav-child">--%>
            <%--                    <dd><a href="">邮件管理</a></dd>--%>
            <%--                    <dd><a href="">消息管理</a></dd>--%>
            <%--                    <dd><a href="">授权管理</a></dd>--%>
            <%--                </dl>--%>
            <%--            </li>--%>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="" class="layui-nav-img">
                    贤心
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree">
                <li class="layui-nav-item layui-nav-itemed layui-this">
                    <a class="home" href="javascript:;">首页</a>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">作业</a>
                    <dl class="layui-nav-child">
                        <dd><a class="new-homework" href="javascript:;">新建作业</a></dd>
                        <dd><a class="already-homework" href="javascript:;">已发布的作业</a></dd>
                        <dd><a href="javascript:;">列表三</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">解决方案</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">列表一</a></dd>
                        <dd><a href="javascript:;">列表二</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">云市场</a></li>
                <li class="layui-nav-item"><a href="">发布商品</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body" style="width: 100%; height: 100%;">
        <!-- 内容主体区域 -->

        <div class="layui-tab layui-tab-brief" lay-filter="tab" lay-allowClose="true" style="width: 100%; height: 87%;">
            <ul class="layui-tab-title">
                <%--                <li class="layui-this" lay-id="0">首页</li>--%>
                <%--                <li>用户基本管理</li>--%>
                <%--                <li>权限分配</li>--%>
                <%--                <li>全部历史商品管理文字长一点试试</li>--%>
                <%--                <li>订单管理</li>--%>
            </ul>
            <div class="layui-tab-content tab-content" style="width: 100%; height: 95%;">
                <%--                <div class="layui-tab-item layui-show" style="width: 100%; height: 100%;">--%>
                <%--                    <iframe frameborder="0" width="100%" height="100%" src="deploy_homework">--%>
                <%--                        您的浏览器暂不支持iframe功能，请更换。--%>
                <%--                    </iframe>--%>
                <%--                </div>--%>
                <%--                <div class="layui-tab-item">2</div>--%>
                <%--                <div class="layui-tab-item">3</div>--%>
                <%--                <div class="layui-tab-item">4</div>--%>
                <%--                <div class="layui-tab-item">5</div>--%>
            </div>
        </div>

    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        <span>www.hfuu.edu.cn</span>
    </div>
</div>
</body>
</html>