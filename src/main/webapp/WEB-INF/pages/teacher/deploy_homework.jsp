<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.UUID" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: 24469
  Date: 2019/9/18
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
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
    <meta name="pageId" content="<%=UUID.randomUUID()%>">
    <title>deploy homework</title>
</head>
<body>
<div>
    <div style="margin-top: 20px;">
        <form class="layui-form">
            <div class="layui-form-item">
                <label class="layui-form-label">作业名称</label>
                <div class="layui-input-block">
                    <input  class="layui-input" type="text" name="taskName" lay-verify="required"
                            lay-verify="title" lay-reqtext="名称不能为空！" placeholder="请输入作业名称" autocomplete="off">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">注意事项</label>
                <div class="layui-input-block">
                    <textarea id="taskTips" style="display: none;" class="layui-textarea" name="description"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">截止日期</label>
                    <div class="layui-input-inline">
                        <input type="text" name="date" id="date" lay-verify="datetime" placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-form-mid layui-word-aux">默认为七天后</div>
                </div>
            </div>

            <div class="layui-form-item course_selects">
                <c:choose>
                    <c:when test="${selectedClass != null && selectedCoz != null}">
                        <c:forEach items='${selectedClass}' var="selectedItem" varStatus="selectedIndex">
                            <div class="layui-input-block clazz_course_select" style="margin-left: 0px; float: left; margin-top: 5px;">
                                <label class="layui-form-label">班级</label>
                                <div class="layui-input-inline" style="width: 250px;">
                                    <select class="choose_clazz" name="clazzId" lay-filter="class_id" lay-verify="required">
                                        <option value="">请选择班级</option>
                                        <c:forEach items='${allCourseAndClass.get("classList")}' var="clazz">
                                            <c:choose>
                                                <c:when test='${!selectedItem.equals(clazz.classNum)}'>
                                                    <option value="${clazz.classNum}">${clazz.className}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${clazz.classNum}" selected="">${clazz.className}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                                <label class="layui-form-label">课程</label>
                                <div class="layui-input-inline" style="width: 350px;">
                                    <select class="choose_course" name="courseId" lay-filter="course_id" lay-verify="required">
                                        <option value="">请选择课程</option>
                                        <c:forEach items='${allCourseAndClass.get("cozList")}' var="course">
                                            <c:choose>
                                                <c:when test='${!selectedCoz[selectedIndex.index].equals(course.cozNum)}'>
                                                    <option value="${course.cozNum}">${course.cozName}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${course.cozNum}" selected="">${course.cozName}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                                    <%--   -号图标    --%>
                                <button type="button" class="layui-btn layui-btn-primary layui-btn-sm del_select" style="margin-top: 4px;margin-left: 15px;">
                                    <i class="layui-icon layui-icon-delete"></i>
                                </button>
                                    <%--   +号图标    --%>
                                <button type="button" class="layui-btn layui-btn-primary layui-btn-sm add_select" style="margin-top: 4px;margin-left: 15px;">
                                    <i class="layui-icon layui-icon-add-1"></i>
                                </button>
                            </div>
                        </c:forEach>
                    </c:when>

                    <c:otherwise>
                        <div class="layui-input-block clazz_course_select" style="margin-left: 0px;">
                            <label class="layui-form-label">班级</label>
                            <div class="layui-input-inline" style="width: 250px;">
                                <select class="choose_clazz" name="clazzId" lay-filter="class_id" lay-verify="required">
                                    <option value="">请选择班级</option>
                                    <c:forEach items='${allCourseAndClass.get("classList")}' var="clazz">
                                        <option value="${clazz.classNum}">${clazz.className}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <label class="layui-form-label">课程</label>
                            <div class="layui-input-inline" style="width: 350px;">
                                <select class="choose_course" name="courseId" lay-filter="course_id" lay-verify="required">
                                    <option value="">请选择课程</option>
                                    <c:forEach items='${allCourseAndClass.get("cozList")}' var="course">
                                        <option value="${course.cozNum}">${course.cozName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                                <%--   -号图标    --%>
                            <button type="button" class="layui-btn layui-btn-primary layui-btn-sm del_select" style="margin-top: 4px;margin-left: 15px;">
                                <i class="layui-icon layui-icon-delete"></i>
                            </button>
                                <%--   +号图标    --%>
                            <button type="button" class="layui-btn layui-btn-primary layui-btn-sm add_select" style="margin-top: 4px;margin-left: 15px;">
                                <i class="layui-icon layui-icon-add-1"></i>
                            </button>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">提交附件</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="files" lay-skin="switch" lay-filter="upload_switch" lay-text="ON|OFF">
                </div>
                <div class="upload_content" style="margin-top: 20px;">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="button" class="layui-btn task_submit" lay-submit="" lay-filter="submit_btn_no_files">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="text/html" id="clazz_course_select_temp">
    <div class="layui-input-block clazz_course_select" style="margin-left: 0px; float: left; margin-top: 5px;">
        <label class="layui-form-label">班级</label>
        <div class="layui-input-inline" style="width: 250px;">
            <select class="choose_clazz" name="clazzId" lay-filter="class_id" lay-verify="required">
                <option value="">请选择班级</option>
                <c:forEach items='${allCourseAndClass.get("classList")}' var="clazz">
                    <option value="${clazz.classNum}">${clazz.className}</option>
                </c:forEach>
            </select>
        </div>
        <label class="layui-form-label">课程</label>
        <div class="layui-input-inline" style="width: 350px;">
            <select class="choose_course" name="courseId" lay-filter="course_id" lay-verify="required">
                <option value="">请选择课程</option>
                <c:forEach items='${allCourseAndClass.get("cozList")}' var="course">
                    <option value="${course.cozNum}">${course.cozName}</option>
                </c:forEach>
            </select>
        </div>
        <%--   -号图标    --%>
        <button type="button" class="layui-btn layui-btn-primary layui-btn-sm del_select" style="margin-top: 4px;margin-left: 15px;">
            <i class="layui-icon layui-icon-delete"></i>
        </button>
        <%--   +号图标    --%>
        <button type="button" class="layui-btn layui-btn-primary layui-btn-sm add_select" style="margin-top: 4px;margin-left: 15px;">
            <i class="layui-icon layui-icon-add-1"></i>
        </button>
    </div>
</script>
</body>
</html>
