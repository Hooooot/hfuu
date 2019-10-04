<%--
  Created by IntelliJ IDEA.
  User: 24469
  Date: 2019/9/18
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <link rel="stylesheet" href="../layui/css/layui.css">
    <link rel="stylesheet" href="../layui/css/admin.css">
    <script src="../layui/layui.js"></script>
    <script src="../js/teacher/deploy_homework.js"></script>
    <title>deploy homework</title>
</head>
<body>
<div>
    <div style="margin-top: 20px;">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <label class="layui-form-label">作业名称</label>
                <div class="layui-input-block">
                    <input  class="layui-input" type="text" name="username" lay-verify="required"
                            lay-verify="title" lay-reqtext="名称不能为空！" placeholder="请输入作业名称" autocomplete="off">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">注意事项</label>
                <div class="layui-input-block">
                    <textarea id="taskTips" style="display: none;" class="layui-textarea"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">截止日期</label>
                    <div class="layui-input-inline">
                        <input type="text" name="date" id="date" lay-verify="date" placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">默认为七天后</div>
                </div>
            </div>

            <div class="layui-form-item" style="">
                <label class="layui-form-label">单行选择框</label>
                <div class="layui-input-block">
                    <select name="interest" lay-filter="aihao">
                        <option value=""></option>
                        <option value="0">写作</option>
                        <option value="1" selected="">阅读</option>
                        <option value="2">游戏</option>
                        <option value="3">音乐</option>
                        <option value="4">旅行</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">分组选择框</label>
                    <div class="layui-input-inline">
                        <select name="quiz">
                            <option value="">请选择问题</option>
                            <optgroup label="城市记忆">
                                <option value="你工作的第一个城市">你工作的第一个城市</option>
                            </optgroup>
                            <optgroup label="学生时代">
                                <option value="你的工号">你的工号</option>
                                <option value="你最喜欢的老师">你最喜欢的老师</option>
                            </optgroup>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">搜索选择框</label>
                    <div class="layui-input-inline">
                        <select name="modules" lay-verify="required" lay-search="">
                            <option value="">直接选择或搜索选择</option>
                            <option value="1">layer</option>
                            <option value="2">form</option>
                            <option value="3">layim</option>
                            <option value="4">element</option>
                            <option value="5">laytpl</option>
                            <option value="6">upload</option>
                            <option value="7">laydate</option>
                            <option value="8">laypage</option>
                            <option value="9">flow</option>
                            <option value="10">util</option>
                            <option value="11">code</option>
                            <option value="12">tree</option>
                            <option value="13">layedit</option>
                            <option value="14">nav</option>
                            <option value="15">tab</option>
                            <option value="16">table</option>
                            <option value="17">select</option>
                            <option value="18">checkbox</option>
                            <option value="19">switch</option>
                            <option value="20">radio</option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">要发布的班级/专业</label>
                <div class="layui-input-inline">
                    <select name="grade">
                        <option value="">请选择年级</option>
                        <option value="16">16级</option>
                        <option value="17">17级</option>
                        <option value="18">18级</option>
                        <option value="19">19级</option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <select name="quiz2">
                        <option value="">请选择专业</option>
                        <option value="计算机科学与技术">计算机科学与技术</option>
                        <option value="软件工程" disabled="">软件工程</option>
                        <option value="网络工程">网络工程</option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <select name="quiz3">
                        <option value="">请选择县/区</option>
                        <option value="西湖区">西湖区</option>
                        <option value="余杭区">余杭区</option>
                        <option value="拱墅区">临安市</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">复选框</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="like[write]" title="写作">
                    <input type="checkbox" name="like[read]" title="阅读" checked="">
                    <input type="checkbox" name="like[game]" title="游戏">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">开关-默认开</label>
                <div class="layui-input-block">
                    <input type="checkbox" checked="" name="open" lay-skin="switch" lay-filter="switchTest" lay-text="ON|OFF">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">单选框</label>
                <div class="layui-input-block">
                    <input type="radio" name="sex" value="男" title="男" checked="">
                    <input type="radio" name="sex" value="女" title="女">
                    <input type="radio" name="sex" value="禁" title="禁用" disabled="">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
