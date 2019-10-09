<%--
  Created by IntelliJ IDEA.
  User: 浅忆
  Date: 2019/10/4
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../layui/css/layui.css">
</head>
<body>
<div>
    <fieldset class="layui-elem-field layui-field-title">
        <legend>各院系提交情况一览</legend>
    </fieldset>
    <table border="1px" style="text-align: center">
        <tr>
            <td>院系名称</td>
            <td>报告总数</td>
            <td>待提交数</td>
            <td>待批阅数</td>
            <td>已批阅数</td>
            <td>学生完成率</td>
            <td>教师批改率</td>
        </tr>
        <c:set var="i" value="0"></c:set>
        <c:forEach items="${deps}" var="dep">
            <tr>
                <td>${dep.depName}</td>
                <td>${total[i]}</td>
                <td>${dtj[i]}</td>
                <td>${dpy[i]}</td>
                <td>${ypy[i]}</td>
                <c:if test="${total[i]==0}">
                    <td>0%</td>
                </c:if>
                <c:if test="${total[i]!=0}">
                    <td><fmt:formatNumber type="percent" maxIntegerDigits="3" maxFractionDigits="1" value="${1-dtj[i]/total[i]}"/></td>
                </c:if>
                <c:if test="${(total[i]-dtj[i])==0}">
                    <td>0%</td>
                </c:if>
                <c:if test="${(total[i]-dtj[i])!=0}">
                    <td><fmt:formatNumber type="percent" maxIntegerDigits="3" value="${dpy[i]/(total[i]-dtj[i])}"/></td>
                </c:if>
            </tr>
            <c:set var="i" value="${i+1}"></c:set>
        </c:forEach>
    </table>
</div>
</body>
</html>
