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
    <link rel="stylesheet" href="layui/css/admin.css">
    <link rel="stylesheet" href="layui/css/iconfont.css">
    <%--自定义图标--%>
    <link rel="stylesheet" href="css/student/lock.css">
    <link rel="icon" href="/favicon.ico">
    <title>主页</title>

</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header custom-header">

        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item slide-sidebar" lay-unselect>
                <a href="javascript:;" class="icon-font"><i class="ai ai-menufold"></i></a>
            </li>
        </ul>


        <%--和下方隐藏区域对应--%>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item lockpc">
                <a href="javascript:;">锁屏</a>
            </li>
            <li class="layui-nav-item " lay-unselect>
                <a href="javascript:;"><img src="images/face.jpg" class="layui-nav-img">我</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" class="showUserInfo"><i class="iconfont">&#xe847</i><cite>个人资料</cite></a>
                    </dd>
                    <dd><a href="javascript:;" class="showChangePassword">修改密码</a></dd>
                    <dd><a href="javascript:;">退出</a></dd>
                    <dd><a href="javascript:;" class="showNotice"><i
                            class="layui-icon">&#xe645;</i><cite>系统公告</cite><span class="layui-badge-dot"></span></a>
                    </dd>
                </dl>
            </li>
        </ul>
    </div>

    <div class="layui-side custom-admin">
        <div class="layui-side-scroll">

            <div class="custom-logo">
                <img src="layui/images/logo.png" alt=""/>
                <h1>作业提交系统</h1>
            </div>
            <ul id="Nav" class="layui-nav layui-nav-tree">
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <i class="layui-icon">&#xe609;</i>
                        <em>主页</em>
                    </a>
                    <dl class="layui-nav-child">
                        <dd class="comm"><a href="${crx}/console">控制台</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <i class="layui-icon">&#xe857;</i>
                        <em>作业</em>
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="${crx}/personaldeta">表单</a></dd>
                        <dd>
                            <a href="javascript:;">页面</a>
                            <dl class="layui-nav-child">
                                <dd>
                                    <a href="${crx}/test">其他页面</a>
                                </dd>
                            </dl>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <i class="layui-icon">&#xe612;</i>
                        <em>用户</em>
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="${crx}/login">登录</a></dd>
                    </dl>
                </li>


                <%--隐藏区域--!!!!!!!!!!!!!!!!!!! --%>
                <li class="layui-nav-item layui-hide">
                    <dl class="layui-nav-child">
                        <dd><a href="${crx}/userinfo" class="userInfo">个人信息</a></dd>
                        <dd><a href="${crx}/personaldeta" class="changePassword">修改密码</a></dd>
                    </dl>
                </li>
            </ul>

        </div>
    </div>

    <div class="layui-body">
        <div class="layui-tab app-container" lay-allowClose="true" lay-filter="tabs">
            <ul id="appTabs" class="layui-tab-title custom-tab"></ul>
            <div id="appTabPage" class="layui-tab-content"></div>
        </div>
    </div>

    <div class="layui-footer">
        <p class="ai-enter maker-footer">© 2019 <a href="http://www.hfuu.edu.cn/" target="_blank">合院</a></p>
    </div>

    <div class="mobile-mask"></div>
</div>
<script src="layui/layui.js"></script>
<script src="js/student/lock.js"></script>
<script src="js/student/home.js"></script>
</body>
</html>