<%--
  Created by IntelliJ IDEA.
  User: 浅忆
  Date: 2019/10/12
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改教师信息</title>
    <link rel="stylesheet" href="../layui/css/layui.css"/>
    <style>
        body {
            padding: 25px 25px 0 0;
            font-size: 14px;
            background: #fff;
            margin: 0 auto;
            font-size: 14px;
            line-height: 20px;
            overflow: hidden;
        }
    </style>
</head>
<body>
<c:forEach items="${tcs}" var="tc">
    <form class="layui-form" method="post" action="${adm}/editTcInfoSubmit" target="frame">
        <input type="hidden" name="tcId" value="${tc.tcId}">
        <div class="layui-form-item">
            <label class="layui-form-label">${tc.tcId}</label>
            <label class="layui-form-label">${tc.tcName}</label>
            <label class="layui-form-label">${tc.tcNum}</label>
            <label class="layui-form-label">所属院系</label>
            <div class="layui-input-inline">
                <select name="depNum">
                    <c:forEach items="${deps}" var="dep">
                        <option value="${dep.depNum}"
                                <c:if test="${tc.depEntity.depNum eq dep.depNum}">selected</c:if> >${dep.depName}</option>
                    </c:forEach>
                </select>
            </div>
            <label class="layui-form-label">重置密码</label>
            <div class="layui-input-inline"><input type="password" name="tcPw" class="layui-input" value="${tc.tcPw}">
            </div>
            <div class="layui-input-inline" style="margin-left: 40px">
                <button class="layui-btn" type="submit" id="editButton">更新</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</c:forEach>
<iframe name="frame" frameborder="0"></iframe>
</body>

<script src="../layui/layui.js"></script>
<script type="text/javascript" src="../js/tc/jquery.min.js"></script>
<script>
    layui.use('form', function () {
        var form = layui.form;
    });
</script>
</html>
