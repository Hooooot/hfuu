<%--
  Created by IntelliJ IDEA.
  User: 16688
  Date: 2019/9/22
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <style type="text/css">
        body {
            margin: 10px;
            min-width:1000px;
            overflow:auto;//当页面的可视区域的宽度小于页面设置的最小宽度的时候，出现滚动条
        }
        .toolbar {
            border: 1px solid #ccc;
        }
        .text {
            border: 1px solid #ccc;
            height: 400px;
        }
    </style>
</head>
<body>
<body>
<div id="div1" class="toolbar">
</div>
<h2 style="text-align: center;margin-top: 10px;"></h2>
<div style="padding: 5px 0; color: #ccc"></div>
<div id="div2" class="text"> <!--可使用 min-height 实现编辑区域自动增加高度-->
    <p>请输入实验报告内容</p>
</div>


<script type="text/javascript" src="weditor/wangEditor.min.js"></script>
<script src="layui/layui.all.js"></script>
<script type="text/javascript">


    var E = window.wangEditor
    var editor1 = new E('#div1', '#div2')  // 两个参数也可以传入 elem 对象，class 选择器
    editor1.create()

    layui.use(['element', 'jquery', 'layer'], function() {
        var $ = layui.$,
            layer = layui.layer,
            element = layui.element;
        $(document).ready(function() {
            getExperimentName();


        });

        function getExperimentName(){
            var text=localStorage.getItem("text");//获取传值
            $("h2").text(text);
            localStorage.removeItem("text");
        }

    });
</script>
</body>
</html>
