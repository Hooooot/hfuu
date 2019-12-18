<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: 24469
  Date: 2019/9/19
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <title>首页</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../layui/css/layui.css" media="all">
    <link rel="icon" href="../images/favicon.ico">
</head>
<body>

<div id="collapse" class="layui-collapse" lay-filter="collapse-filter">
    <c:forEach items="${course}" var="courseMap" varStatus="coStatus">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title" data-loaded="false" data-cozname="${courseMap.key}"
                data-tableid="data_table${coStatus.index}"
                data-tablefilter="data_table${coStatus.index}">${courseMap.key}</h2>
            <div class="layui-colla-content">
                <table class="layui-hide" id="data_table${coStatus.index}"
                       lay-filter="data_table${coStatus.index}"></table>
            </div>
        </div>
    </c:forEach>
</div>

<script type="text/html" id="toolBar">
    <div class="layui-btn-container">
        <button type="button" class="layui-btn layui-btn-sm" lay-event="deployTasks">向选中班级发布作业</button>
        <button type="button" class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
        <button type="button" class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
    </div>
</script>

<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-xs" lay-event="deploy">发布作业</a>
    <a class="layui-btn layui-btn-xs" lay-event="detail">作业详情</a>
</script>

<script src="../layui/layui.js" charset="utf-8"></script>
<script src="../js/teacher/console.js"></script>

</body>
</html>