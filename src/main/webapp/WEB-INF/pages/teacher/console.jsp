<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 24469
  Date: 2019/9/19
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>首页</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <link rel="stylesheet" href="../layui/css/layui.css"  media="all">
</head>
<body>

<div class="layui-collapse" lay-filter="collapse-filter">

    <c:forEach items="">

    </c:forEach>

    <div class="layui-colla-item">
        <h2 class="layui-colla-title">为什么JS社区大量采用未发布或者未广泛支持的语言特性？</h2>
        <div class="layui-colla-content">

        </div>
    </div>
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">为什么前端工程师多不愿意用 Bootstrap 框架？</h2>
        <div class="layui-colla-content">
        </div>
    </div>
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">layui 更适合哪些开发者？</h2>
        <div class="layui-colla-content">
            <table class="layui-hide" id="data_table1" lay-filter="data_table1"></table>
        </div>
    </div>
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">贤心是男是女？</h2>
        <div class="layui-colla-content">
            <table class="layui-hide" id="data_table2" lay-filter="data_table2"></table>
        </div>
    </div>
</div>

<script type="text/html" id="toolbar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
        <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
    </div>
</script>

<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script src="../layui/layui.js" charset="utf-8"></script>
<script src="../js/teacher/console.js"></script>

</body>
</html>