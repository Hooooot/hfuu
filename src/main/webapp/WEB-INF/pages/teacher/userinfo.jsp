<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: 24469
  Date: 2019/9/21
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../layui/css/layui.css" media="all">
    <link rel="icon" href="../images/favicon.ico">
    <script src="../layui/layui.js"></script>
    <script src="../js/teacher/userinfo.js"></script>
    <title>用户信息</title>
</head>
<body style="margin-top: 20px;">

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-card">
            <div class="layui-card-header">设置我的资料</div>
            <div class="layui-card-body" pad15="">
                <div class="layui-form" lay-filter="">
                    <div class="layui-form-item">
                        <div class="layui-input-inline" style="margin-left: 120px;">
                            <div class="layui-upload">
                                <div style="width: 150px;height: 150px;position:relative;">

                                    <img style="width: 150px;height: 150px; border: 1px dashed #e2e2e2;"
                                         src="../uploaded/${teacher.tcAvatar}" id="img" alt="">
                                    <p id="errorText" style="position: absolute; bottom:0; right:0;"></p>
                                </div>
                                <button type="button" style="width: 152px;margin-top: 3px;"
                                        class="layui-btn" id="upload_btn"><i class="layui-icon"></i>上传新头像</button>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">工号</label>
                        <div class="layui-input-inline">
                            <input id="tc_num" type="text" name="tcNum" value="${teacher.tcNum}" readonly="" class="layui-input">
                        </div>
                        <div class="layui-form-mid layui-word-aux">不可修改</div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">用户名</label>
                        <div class="layui-input-inline">
                            <input type="text" name="tcName" value="${teacher.tcName}" lay-verify="required" autocomplete="off"
                                   placeholder="请输入昵称" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">性别</label>
                        <div class="layui-input-block">
                            <c:choose>
                                <c:when test='${teacher.tcSex.equals("男")}'>
                                    <input type="radio" name="sex" value="男" title="男" checked="">
                                </c:when>
                                <c:otherwise>
                                    <input type="radio" name="sex" value="男" title="男">
                                </c:otherwise>
                            </c:choose>
                            <div class="layui-unselect layui-form-radio">
                                <i class="layui-anim layui-icon"></i>
                                <div>男</div>
                            </div>
                            <c:choose>
                                <c:when test='${teacher.tcSex.equals("男")}'>
                                    <input type="radio" name="sex" value="女" title="女">
                                </c:when>
                                <c:otherwise>
                                    <input type="radio" name="sex" value="女" title="女" checked="">
                                </c:otherwise>
                            </c:choose>
                            <div class="layui-unselect layui-form-radio layui-form-radioed">
                                <i class="layui-anim layui-icon"></i>
                                <div>女</div>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">手机</label>
                        <div class="layui-input-inline">
                            <input type="text" name="tcPhone" value="${teacher.tcPhone}" lay-verify="phone" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">邮箱</label>
                        <div class="layui-input-inline">
                            <input type="email" name="tcEmail" value="${teacher.tcEmail}" lay-verify="email" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button type="button" class="layui-btn set-my-info" lay-submit="" lay-filter="setmyinfo">确认修改</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
