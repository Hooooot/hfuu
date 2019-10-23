<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%--
  Created by IntelliJ IDEA.
  User: 16688
  Date: 2019/9/22
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link rel="stylesheet" href="layui/css/layui.css">
</head>
<body>
<div id="collapse" class="layui-collapse" lay-filter="collapse-filter">
    <c:forEach items="${course}" var="courseMap" varStatus="coStatus">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title" data-loaded="false" data-cozname="${courseMap.key}"
                data-tableid="data_table${coStatus.index}" data-tablefilter="data_table${coStatus.index}" data-stunum="${studentLogin.stuNum}"><b>${courseMap.key}</b></h2>

            <div class="layui-colla-content" style="margin-top: -20px;">
                <table class="layui-hide" id="data_table${coStatus.index}" lay-filter="data_table${coStatus.index}"></table>
            </div>
        </div>
    </c:forEach>
</div>


<script type="text/html" id="toolBar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
        <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
    </div>
</script>

<script type="text/html" id="bar">
   <%-- <a class="layui-btn layui-btn-xs layui-btn-disabled" lay-event="deploy">提交作业</a>--%>
   {{#  if((new Date(d.deadline) >new Date())&&(d.subState!="已批阅")){ }}
    <a class="layui-btn layui-btn-xs" lay-event="deploy">提交作业</a>
    {{#  } }}
   {{#  if((new Date(d.deadline) < new Date())||(d.subState=="已批阅")){ }}
   <a class="layui-btn layui-btn-xs layui-btn-disabled" >提交作业</a>
   {{#  } }}

   <a class="layui-btn layui-btn-xs" lay-event="deleterow">删除行</a>
</script>
<script src="layui/layui.js"></script>
<script src="js/student/home.js"></script>
</body>
</html>