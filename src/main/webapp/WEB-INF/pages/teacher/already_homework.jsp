<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: 24469
  Date: 2019/9/18
  Time: 16:32
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

<div id="parent_collapse" class="layui-collapse" lay-filter="parent-collapse-filter">
    <c:forEach items="${clazzs}" var="clazzVar" varStatus="clazzStatus">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title" data-loaded="false" data-cozname="${clazzVar.key}" style="font-size: 16px;"
                data-tableid="data_table${clazzStatus.index}" data-tablefilter="data_table${clazzStatus.index}">
                    <%--                一级标题，班级名--%>
                    ${clazzVar.key.className}
            </h2>
            <div class="layui-colla-content">
                <div id="collapse${clazzStatus.index}" class="layui-collapse" lay-filter="collapse-filter">
                    <c:forEach items="${clazzVar.value}" var="taskVar" varStatus="taskStatus">
                        <div class="layui-colla-item">
                            <h2 class="layui-colla-title" data-loaded="false" data-taskid="${taskVar.taskId}"
                                data-clazznum="${clazzVar.key.classNum}" data-taskdeadline="${taskVar.deadline}"
                                data-tableid="data_table${clazzStatus.index}${taskStatus.index}"
                                style="font-size: 16px;"
                                data-tablefilter="data_table${clazzStatus.index}${taskStatus.index}">
                                    <%--                                二级标题，任务名   --%>
                                <div class="layui-fluid">
                                    <div class="layui-row layui-col-space5" style="font-size: 18px; text-align: center;">
                                        <div class="layui-col-md4">
                                            <div style="margin-left: 15%;">${taskVar.taskName}</div>
                                        </div>
                                        <jsp:useBean id="now" class="java.util.Date"/>
                                        <c:set var="state" value="${taskVar.deadline.after(now)}"/>
                                        <div class="layui-col-md4">
                                            <c:choose>
                                                <c:when test="${state}">
                                                    <span style="color: green;">作业状态：未截止</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span>作业状态：已截止</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="layui-col-md4">
                                            <div>
                                                截止日期：
                                                <c:choose>
                                                    <c:when test="${state}">
                                                                <span style="color: green;"><fmt:formatDate type="both"
                                                                                                            value="${taskVar.deadline}"/></span>
                                                    </c:when>
                                                    <c:otherwise>
                                                                <span><fmt:formatDate type="both"
                                                                                                          value="${taskVar.deadline}"/></span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </h2>
                            <div class="layui-colla-content">
                                <table class="layui-hide" id="data_table${clazzStatus.index}${taskStatus.index}"
                                       lay-filter="data_table${clazzStatus.index}${taskStatus.index}">
                                </table>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<script type="text/html" id="toolBar">
    <div class="layui-btn-container">
        <button type="button" class="layui-btn layui-btn-sm" lay-event="correctSubmits">批改作业</button>
        <button type="button" class="layui-btn layui-btn-sm" lay-event="getTaskDetail">查看任务详情</button>
        <button type="button" class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
    </div>
</script>

<script type="text/html" id="bar">
    {{#  if(d.submit !== undefined){ }}
    <a class="layui-btn layui-btn-xs" lay-event="correct">批改作业</a>
    {{#  }else{ }}
    <a class="layui-btn layui-btn-xs layui-btn-disabled">批改作业</a>
    {{#  } }}
</script>

<script src="../layui/layui.js" charset="utf-8"></script>
<script src="../js/teacher/already_homework.js"></script>

</body>
</html>
