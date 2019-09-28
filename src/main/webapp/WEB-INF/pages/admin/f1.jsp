<%--
  Created by IntelliJ IDEA.
  User: 浅忆
  Date: 2019/9/28
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="../layui/css/layui.css">
    <script src="../js/admin/echarts.js"></script>
</head>
<body>
<%--    <div id="main" style="height: 250px;width: 400px;">--%>

<%--    </div>--%>
    <div>
        <fieldset class="layui-elem-field layui-field-title">
            <legend>各任务完成情况一览</legend>
        </fieldset>
        <table border="1px" style="text-align: center">
            <tr>
                <td>任务名称</td><td>学生人数</td><td>待提交数</td><td>待批阅数</td><td>已批阅数</td>
            </tr>
            <c:set var="i" value="0"></c:set>
            <c:forEach items="${tasks}" var="task">
                <tr>
                    <td>${task.taskName}</td>
                    <td>${total[i]}</td>
                    <td>${dtj[i]}</td>
                    <td>${dpy[i]}</td>
                    <td>${ypy[i]}</td>
                </tr>
            <c:set var="i" value="${i}+1"></c:set>
            </c:forEach>
        </table>

    </div>
    <script>
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: 'ECharts 入门示例'
            },
            tooltip: {},
            legend: {
                data:['销量']
            },
            xAxis: {
                data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
            },
            yAxis: {},
            series: [{
                name: '销量',
                type: 'bar',
                data: [5, 20, 36, 10, 10, 20]
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

        //禁用右键、文本选择功能、
        $(document).bind("contextmenu", function () {
            return false;
        });
        $(document).bind("selectstart", function () {
            return false;
        });
    </script>
</body>
</html>
