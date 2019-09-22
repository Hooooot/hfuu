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
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="layui/css/layui.css">
    <style>
        body {
            margin: 10px;
            min-width:1200px;
            overflow:auto;//当页面的可视区域的宽度小于页面设置的最小宽度的时候，出现滚动条
        }

        .demo-carousel {
            height: 200px;
            line-height: 200px;
            text-align: center;
        }

        h2 {
            font-weight: bold;
        }

        em{
            margin-right: 80px;
        }

        .positionRight {
            float: right;
            margin-left: 80px;
            width: 80px;
        }
        .positionLeft {
            float: left;
            margin-left: 80px;
            width: 150px;

        }
        /*使用after伪元素清除浮动*/
        .clearfix:after{/*伪元素是行内元素 正常浏览器清除浮动方法*/
            content: "";
            display: block;
            height: 0;
            clear:both;
            visibility: hidden;
        }
        .experiment{/*实验的css样式*/
            clear: both;
            height: 50px;
            line-height: 50px;
            margin-left: 30px;
        }

    </style>
    <title></title>
</head>

<body>
<div class="layui-collapse " id="content">
    <div class="layui-colla-item clearfix">
        <h2 class="layui-colla-title">C++<span class="positionRight">待加功能<em class="layui-hide">这是个测试</em></span><span class="positionRight time">2019-10-01<em class="layui-hide">最近的截止提交时间</em></span>
            <span class="positionRight">3/5<em class="layui-hide">作业已提交数/总作业数</em></span>
        </h2>
        <div class="layui-colla-content">
            <div class="positionLeft">任课考试：XXX </div>
            <div class="positionLeft">实验课时：XXX </div>
            <div class="experiment"><em>实验：XXXXXXXXXXXXXXXXXXXXX</em><em>分数：00</em><span>截止时间:</span><span class="time">2018-10-10 24:00:00</span><em></em><em>上传时间：--:--:--</em>
                <div class="layui-btn-group" style="float:right;margin-right: 50px;">
                <button type="button" class="layui-btn layui-btn-primary" date-url="实验一编辑">编辑</button>
                <button type="button" class="layui-btn layui-btn-primary" date-url="实验一上传">上传</button>
            </div></div>
            <div class="experiment"><em>实验：XXXXXXXXXXXXXXXXXXXXX</em><em>分数：00</em><span>截止时间:</span><span class="time">2019-09-10 24:00:00</span><em></em><em>上传时间：--:--:--</em>
                <div class="layui-btn-group" style="float:right;margin-right: 50px;">
                    <button type="button" class="layui-btn layui-btn-primary" date-url="实验二编辑">编辑</button>
                    <button type="button" class="layui-btn layui-btn-primary" date-url="实验二上传">上传</button>
                </div></div>
            <div class="experiment"><em>实验：XXXXXXXXXXXXXXXXXXXXX</em><em>分数：00</em><span>截止时间:</span><span class="time">2019-10-10 24:00:00</span><em></em><em>上传时间：--:--:--</em>
                <div class="layui-btn-group" style="float:right;margin-right: 50px;">
                    <button type="button" class="layui-btn layui-btn-primary" date-url="实验三编辑">编辑</button>
                    <button type="button" class="layui-btn layui-btn-primary" date-url="实验三上传">上传</button>
                </div></div>
            <div class="experiment"><em>实验：XXXXXXXXXXXXXXXXXXXXX</em><em>分数：00</em><span>截止时间:</span><span class="time">2019-10-24 24:00:00</span><em></em><em>上传时间：--:--:--</em>
                <div class="layui-btn-group" style="float:right;margin-right: 50px;">
                    <button type="button" class="layui-btn layui-btn-primary" date-url="实验四编辑">编辑</button>
                    <button type="button" class="layui-btn layui-btn-primary" date-url="实验四上传">上传</button>
                </div></div>

        </div>
    </div>
    <div class="layui-colla-item clearfix">
        <h2 class="layui-colla-title">数据结构<span class="positionRight">待加功能<em class="layui-hide">这是个测试</em></span><span class="positionRight time">2019-09-21<em class="layui-hide">最近的截止提交时间</em></span>
            <span class="positionRight">3/5<em class="layui-hide">作业已提交数</em></span></h2>
        <div class="layui-colla-content">测试二</div>
    </div>
    <div class="layui-colla-item clearfix">
        <h2 class="layui-colla-title">IavaWeb<span class="positionRight">待加功能<em class="layui-hide">这是个测试</em></span><span class="positionRight time">2019-11-01<em class="layui-hide">最近的截止提交时间</em></span>
            <span class="positionRight">3/5<em class="layui-hide">作业已提交数</em></span></h2>
        <div class="layui-colla-content ">测试三</div>
    </div>
</div>
<script src="layui/layui.all.js"></script>
<script>
    //测试



    //实现折叠面板间隔变色
    var content = document.getElementById("content");
    var collapses = content.getElementsByClassName("layui-colla-title"); //js获取折叠个数

    for (var i = 0; i < collapses.length; i++) {
        if (i % 2 == 0) {
            collapses[i].style.background = "white";
        }
    }
    layui.use(['element', 'jquery', 'layer'], function() {

        var $ = layui.$,
            layer = layui.layer,
            element = layui.element;

        //禁用右键、文本选择功能、复制按键
        $(document).bind("contextmenu", function() { return false; });
        $(document).bind("selectstart", function() { return false; });
        $(document).keydown(function() { return key(arguments[0]) });


        $(document).ready(function() {
            //获取a标签 date-url 值
            $("button").click(function() {
                alert($(this).attr("date-url"))
            });


            //最后提交时间已过，红色字体；否则，绿色字体
            var times = $(".layui-colla-item").find(".time");
            var stime;
            times.each(function(index, e) {
                stime = $(e).html().substring(0, 10); //2019-10-01

                if (stime < nowDate()) {

                    $(e).css({ "color": "red" });
                }else{
                    $(e).css({ "color": "green" });
                }

            })
            //获取当前时间
            function nowDate() {
                var nowdate = new Date();
                var year = nowdate.getFullYear();
                var month = nowdate.getMonth() + 1;
                var day = nowdate.getDate();
                //console.log(year+"-"+getzf(month)+"-"+getzf(day));
                return year + "-" + getzf(month) + "-" + getzf(day);
            };

            //补0操作
            function getzf(num) {
                if (parseInt(num) < 10) {
                    num = '0' + num;
                }
                return num;
            }

        });


        // 鼠标经过小提示
        var tipIndex;
        $("span").hover(function() {
            var $this = $(this);
            //$this.find('em').text()

            if($this.find('em').text()==""){
                layer.close(tipIndex);
            }else{
                tipIndex = layer.tips($this.find('em').text(), $this);
            }

        }, function() {

            layer.close(tipIndex);
            tipIndex = null
        });


    });
</script>
</body>

</html>