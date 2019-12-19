<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%--
  Created by IntelliJ IDEA.
  User: 24469
  Date: 2019/12/9
  Time: 9:09
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../layui/css/layui.css">
    <link rel="stylesheet" href="../layui/css/admin.css">
    <script src="../layui/layui.js"></script>
    <script src="../js/teacher/task_detail.js"></script>
    <link rel="icon" href="../images/favicon.ico">
    <title>task detail</title>
</head>
<body style="padding: 0 20px;">
    <div>
        <div class="layui-form layui-form-pane">
            <div class="layui-form-item">
                <label class="layui-form-label">作业名称</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" value="${task.taskName}" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">注意事项</label>
                <div class="layui-input-block">
                    <iframe id="desc" src='../uploaded/${task.taskDesc}' class="layui-textarea"></iframe>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">发布日期</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" value='<fmt:formatDate type="both"
                                        value="${task.pubTime}"/>' readonly>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">截止日期</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" value='<fmt:formatDate type="both"
                                        value="${task.deadline}"/>' readonly>
                    </div>
                    <jsp:useBean id="now" class="java.util.Date"/>
                    <c:set var="state" value="${task.deadline.after(now)}"/>
                    <div class="layui-input-inline">
                        <c:choose>
                            <c:when test="${state}">
                                <div class="layui-form-mid layui-word-aux"> <div  style="color: green;">未截止</div></div>
                            </c:when>
                            <c:otherwise>
                                <div class="layui-form-mid layui-word-aux">已截止</div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
            <div>
                <table id="enclosure" class="layui-hide" data-task_enclosure="${task.taskFiles}"></table>
            </div>
        </div>
    </div>
</body>
</html>

