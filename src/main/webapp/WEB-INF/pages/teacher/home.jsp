<%--
  Created by IntelliJ IDEA.
  User: 24469
  Date: 2019/9/13
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <link rel="stylesheet" href="../layui/css/layui.css">
    <link rel="stylesheet" href="../layui/css/admin.css">
    <link rel="stylesheet" href="../css/teacher/lock.css">
    <script src="../layui/layui.js"></script>
    <script src="../js/teacher/lock.js"></script>
    <script src="../js/teacher/home.js"></script>
    <link rel="icon" href="../images/favicon.ico">
    <title>主页</title>

</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header custom-header">

        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item slide-sidebar" lay-unselect>
                <a href="javascript:" class="icon-font"><i class="ai ai-menufold"></i></a>
            </li>
        </ul>


        <%--顶部信息--%>
        <ul class="layui-nav layui-layout-right">
            <%--显示时间--%>
            <li class="layui-nav-item">
                <i id="nowTime"></i>
            </li>
            <li class="layui-nav-item lockpc">
                <a href="javascript:">锁屏</a>
            </li>
            <%-- 右上角的用户 --%>
            <li class="layui-nav-item " lay-unselect>
                <a href="javascript:"><img src="../images/face.jpg" class="layui-nav-img">我</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:" class="showUserInfo"><i class="iconfont">&#xe847</i><cite>个人资料</cite></a>
                    </dd>
                    <dd><a href="javascript:" class="showChangePassword">修改密码</a></dd>
                    <dd><a href="javascript:">退出</a></dd>
                    <%--                    公告系统，待定         --%>
                    <%--                    <dd><a href="javascript:;;" class="showNotice"><i--%>
                    <%--                            class="layui-icon">&#xe645;</i><cite>系统公告</cite><span class="layui-badge-dot"></span></a>--%>
                    <%--                    </dd>--%>
                </dl>
            </li>
        </ul>
    </div>

    <%--  侧边栏  --%>
    <div class="layui-side custom-admin">
        <div class="layui-side-scroll">
            <div class="custom-logo">
                <a href="/hfuu" style="color: white;" title="回到首页">
                    <img src="../layui/images/logo.png" alt="logo"/>
                    <h1>作业系统--教师端</h1>
                </a>
            </div>
            <ul id="Nav" class="layui-nav layui-nav-tree">
                <li class="layui-nav-item">
                    <a href="console?tcNum=${teacher.tcNum}">
                        <i class="layui-icon">&#xe609;</i>
                        <em>首页</em>
                    </a>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:">
                        <i class="layui-icon">&#xe857;</i>
                        <em>作业</em>
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a class="deployTask" href="deploy_homework">发布作业</a></dd>
                        <dd>
                            <a class="alreadyTask" href="already_homework">已发布作业</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:">
                        <i class="layui-icon">&#xe612;</i>
                        <em>用户</em>
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="login">登录</a></dd>
                    </dl>
                </li>

<%--                &lt;%&ndash;隐藏区域--!!!!!!!!!!!!!!!!!!! &ndash;%&gt;--%>
<%--                <li class="layui-nav-item layui-hide">--%>
<%--                    <dl class="layui-nav-child">--%>
<%--                        <dd><a href="userinfos" class="userInfo">个人信息</a></dd>--%>
<%--                        <dd><a href="personaldetas" class="changePassword">修改密码</a></dd>--%>
<%--                    </dl>--%>
<%--                </li>--%>
            </ul>

        </div>
    </div>

    <%--  中央信息区域  --%>
    <div class="layui-body">
        <div class="layui-tab app-container" lay-allowClose="true" lay-filter="tabs">
            <ul id="appTabs" class="layui-tab-title custom-tab"></ul>
            <div id="appTabPage" class="layui-tab-content"></div>
        </div>
    </div>

    <div class="layui-footer">
        <p class="maker-footer"><i class="layui-icon layui-icon-website"></i>&nbsp;2019&nbsp;<a href="http://www.hfuu.edu.cn/" target="_blank">合肥学院</a></p>
    </div>

    <div class="mobile-mask"></div>
</div>
</body>
</html>