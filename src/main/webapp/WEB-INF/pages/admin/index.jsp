<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../layui/css/layui.css">
    <link rel="stylesheet" href="../layui/css/admin.css">
    <%--自定义图标--%>
    <link rel="stylesheet" href="../css/teacher/lock.css">
    <script src="../layui/layui.js"></script>
    <script src="../js/teacher/lock.js"></script>
    <script src="../js/teacher/home.js"></script>
    <link rel="icon" href="../favicon.ico">
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


        <%--顶部信息--%>
        <ul class="layui-nav layui-layout-right">
            <%--显示时间--%>
            <li class="layui-nav-item">
                <i id="nowTime"></i>
            </li>
            <li class="layui-nav-item lockpc">
                <a href="javascript:;">锁屏</a>
            </li>
            <%-- 右上角的用户 --%>
            <li class="layui-nav-item " lay-unselect>
                <a href="javascript:;"><img src="../images/face.jpg" class="layui-nav-img">我</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" class="showUserInfo">个人资料</a></dd>
                    <dd><a href="javascript:;" class="showChangePassword">修改密码</a></dd>
                    <dd><a href="javascript:;">退出</a></dd>
                </dl>
            </li>
        </ul>
    </div>

    <%--  侧边栏  --%>
    <div class="layui-side custom-admin">
        <div class="layui-side-scroll">
            <div class="custom-logo">
                <img src="../layui/images/logo.png" alt="logo"/>
                <h1>作业提交系统管理员端</h1>
            </div>
            <ul id="Nav" class="layui-nav layui-nav-tree">
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <i class="layui-icon">&#xe609;</i>
                        <em>模块一</em>
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">功能一</a></dd>
                        <dd><a href="javascript:;">功能二</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <i class="layui-icon">&#xe857;</i>
                        <em>模块二</em>
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">功能三</a></dd>
                        <dd>
                            <a href="javascript:;">功能四</a>
                        </dd>
                    </dl>
                </li>
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
        <p class="ai-enter maker-footer">2019<a href="http://www.hfuu.edu.cn/" target="_blank">合肥学院</a></p>
    </div>

    <div class="mobile-mask"></div>
</div>
</body>
</html>