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
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="layui/css/layui.css">
    <style>
        body {
            margin: 10px;
            min-width: 1200px;
            overflow: auto;
        / / 当页面的可视区域的宽度小于页面设置的最小宽度的时候，出现滚动条
        }

        h2 {
            font-weight: bold;
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
        .clearfix:after { /*伪元素是行内元素 正常浏览器清除浮动方法*/
            content: "";
            display: block;
            height: 0;
            clear: both;
            visibility: hidden;
        }

        .experiment { /*实验的css样式*/
            clear: both;
            height: 50px;
            line-height: 50px;
            margin-left: 30px;
        }

        .floatright {
            float: right;
            text-align: left;
            margin-right: 50px;
        }


    </style>
    <title></title>
</head>

<body>
<div class="layui-collapse " id="content">
    <c:set var="i" value="0"></c:set>
    <c:forEach items="${student}" var="stu">
        <c:forEach items="${stu.classEntity.coursesFromClass}" var="clas">
            <div class="layui-colla-item clearfix">
                <h2 class="layui-colla-title">${clas.cozName}<span class="positionRight">待加功能<em
                        class="layui-hide">这是个测试</em></span><span class="positionRight time">2019-11-01<em
                        class="layui-hide">最近的截止提交时间</em></span>
                    <span class="positionRight">${ytj[i]}/${total[i]} <em class="layui-hide">作业已提交数</em></span></h2>

                <div class="layui-colla-content">
                    <div class="positionLeft">任课老师：${clas.tcEntity.tcName}</div>
                    <div class="positionLeft">实验课时：XXX</div>
                    <c:forEach items="${clas.tasksFromCoz}" var="task">
                        <div class="experiment"><span>${task.taskName}</span>
                            <div class="layui-btn-group floatright">
                                <button type="button" class="layui-btn layui-btn-primary edit"
                                        date-experiment="${task.taskName}">
                                    编辑
                                </button>
                                <button type="button" class="layui-btn layui-btn-primary"
                                        date-experiment="${task.taskName}">
                                    上传
                                </button>
                            </div>
                            <em class="floatright">提交时间：${submit.subTime}
                            </em>
                            <span class="time floatright" style="margin-left: -50px;">${task.pubTime}</span>
                            <span class="floatright">截止时间:--:--:--</span>
                            <c:forEach items="${task.submitsFromTask}" var="submit">
                                <%--<c:if test="${submit.stuEntity.stuNum eq studentLogin.stuNum}">--%>
                                <em class="floatright">分数：${submit.score}
                                </em>
                               <%-- </c:if>--%>
                            </c:forEach>

                        </div>
                    </c:forEach>
                </div>
            </div>
            <c:set var="i" value="${i+1}"></c:set>
        </c:forEach>
    </c:forEach>


</div>
<script src="layui/layui.all.js"></script>
<script>

    //实现折叠面板间隔变色
    var content = document.getElementById("content");
    var collapses = content.getElementsByClassName("layui-colla-title"); //js获取折叠个数

    for (var i = 0; i < collapses.length; i++) {
        if (i % 2 == 0) {
            collapses[i].style.background = "white";
        }
    }
    layui.use(['element', 'jquery', 'layer'], function () {

        var $ = layui.$,
            layer = layui.layer,
            element = layui.element;//注意点！！！！！


        //禁用右键、文本选择功能、
        $(document).bind("contextmenu", function () {
            return false;
        });
        $(document).bind("selectstart", function () {
            return false;
        });


        $(document).ready(function () {

            //获取button标签 date-url 值
            $(".edit").click(function () {
                var date = $(this).attr("date-experiment");
                localStorage.setItem("text", date);//传值

                //tab子页面使父页面添加tab
                parent.layui.element.tabAdd('tabs', {
                    title: '实验报告',
                    content: '<iframe data-frameid="1" frameborder="0" name="content" scrolling="no" width="100%" src="${crx}/edits"></iframe>',
                    id: date
                });

                //Tab切换
                parent.layui.element.tabChange('tabs', date);

            });

            //最后提交时间已过，红色字体；否则，绿色字体
            var times = $(".layui-colla-item").find(".time");
            var stime;
            times.each(function (index, e) {
                stime = $(e).html().substring(0, 10); //2019-10-01

                if (stime < nowDate()) {

                    $(e).css({"color": "red"});
                } else {
                    $(e).css({"color": "green"});
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
        $("span").hover(function () {
            var $this = $(this);
            //$this.find('em').text()

            if ($this.find('em').text() == "") {
                layer.close(tipIndex);
            } else {
                tipIndex = layer.tips($this.find('em').text(), $this);
            }

        }, function () {

            layer.close(tipIndex);
            tipIndex = null
        });


    });
</script>
</body>

</html>