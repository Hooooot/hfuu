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
    <link rel="icon" href="../images/favicon.ico">
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

            <div class="layui-form-item">
                <div class="layui-input-block clazz_course_select" style="margin-left: 0px;">
                    <label class="layui-form-label">班级</label>
                    <div class="layui-input-inline" style="width: 250px;">
                        <select class="choose_clazz" name="clazz_id[0]" lay-filter="class_id">
                            <option value="">请选择班级</option>
                            <option value="0">写作</option>
                            <option value="1" selected="">阅读</option>
                            <option value="2">游戏</option>
                            <option value="3">音乐</option>
                            <option value="4">旅行</option>
                        </select>
                    </div>
                    <label class="layui-form-label">课程</label>
                    <div class="layui-input-inline" style="width: 350px;">
                        <select class="choose_course" name="course_id[0]" lay-filter="course_id">
                            <option value="">请选择课程</option>
                            <option value="0" selected="">写作</option>
                            <option value="1">阅读</option>
                            <option value="2">游戏</option>
                            <option value="3">音乐</option>
                            <option value="4">旅行</option>
                        </select>
                    </div>
                    <%--   +号图标    --%>
                    <button type="button" id="add_select" class="layui-btn layui-btn-primary layui-btn-sm" style="margin-top: 4px;margin-left: 15px;">
                        <i class="layui-icon">&#xe654;</i>
                    </button>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">提交附件</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="open" lay-skin="switch" lay-filter="switchTest" lay-text="ON|OFF">
                </div>
            </div>

            <div class="layui-upload">
                <div class="layui-upload-drag" id="upload_drag" style="margin-left: 10px;">
                    <i class="layui-icon"></i>
                    <p>点击选择附件，或将附件拖拽到此处</p>
                </div>
                <div class="layui-upload-list">
                    <table class="layui-table">
                        <thead>
                        <tr><th>文件名</th>
                            <th>大小</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr></thead>
                        <tbody id="fileList"></tbody>
                    </table>
                </div>
                <input type="button" class="layui-btn" id="uploadAction" value="开始上传">
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
