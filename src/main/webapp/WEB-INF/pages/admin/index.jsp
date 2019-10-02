<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../layui/css/layui.css">
    <link rel="stylesheet" href="../layui/css/admin.css">
    <link rel="stylesheet" href="../css/admin/lock.css">
    <link rel="icon" href="../images/favicon.ico">
    <title>主页</title>

</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header custom-header">
        <%--顶部左侧信息--%>
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item slide-sidebar" lay-unselect>
                <a href="javascript:;" class="icon-font"><i class="ai ai-menufold"></i></a>
            </li>
        </ul>
        <%--顶部右侧信息--%>
        <ul class="layui-nav layui-layout-right">
            <%--显示时间--%>
            <li class="layui-nav-item">
                <i id="nowTime"></i>
            </li>
            <li class="layui-nav-item lockpc">
                <a href="javascript:;">锁屏</a>
            </li>
            <%-- 右上角的用户 --%>
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="/admin/${admin.adminAvatar}" class="layui-nav-img"/>
                    ${admin.adminName}&nbsp;&nbsp;
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" class="showChangePassword">修改密码</a></dd>
                    <dd><a href="${adm}/login">退出</a></dd>
                </dl>
            </li>
        </ul>
    </div>

    <%--  侧栏  --%>
    <div class="layui-side custom-admin">
        <div class="layui-side-scroll">
            <div class="custom-logo">
                <img src="../layui/images/logo.png" alt=""/>
                <h1>作业提交情况</h1>
            </div>
            <ul id="Nav" class="layui-nav layui-nav-tree">
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <i class="layui-icon layui-icon-chart"></i>
                        <em>统计分析</em>
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="${adm}/f1">按任务</a></dd>
                        <dd><a href="javascript:;">按院系</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <i class="layui-icon layui-icon-app"></i>
                        <em>其他功能</em>
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">功能三</a></dd>
                        <dd><a href="javascript:;">功能四</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <i class="layui-icon layui-icon-username"></i>
                        <em>用户管理</em>
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="${adm}/editAdminInfo">修改个人资料</a></dd>
                        <dd><a href="javascript:;">功能六</a></dd>
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

    <div class="layui-footer" style="text-align: center">
        © 2019<a href="http://www.hfuu.edu.cn/" target="_blank">&nbsp;&nbsp;合肥学院</a></p>
    </div>

    <div class="mobile-mask"></div>
</div>
<%--script  script  script  script--%>
<script src="../layui/layui.js"></script>
<script src="../js/admin/lock.js"></script>
<script src="../js/admin/index.js"></script>
</body>
</html>