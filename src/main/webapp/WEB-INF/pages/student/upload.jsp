<%--
  Created by IntelliJ IDEA.
  User: 16688
  Date: 2019/9/18
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="layui/css/layui.css">
    <link rel="icon" href="/favicon.ico">
    <title>上传</title>
</head>
<body>
<div class="layui-upload">
   <%-- <button type="button" class="layui-btn layui-btn-normal" id="testList">选择多文件</button>--%>
       <div class="layui-upload-drag" id="uploadList">
           <i class="layui-icon"></i>
           <p>点击上传，或将文件拖拽到此处</p>
       </div>
       <div class="layui-upload-list">
           <table class="layui-table">
               <thead>
               <tr>
                   <th>文件名</th>
                   <th>大小</th>
                   <th>状态</th>
                   <th>操作</th>
               </tr>
               </thead>
               <tbody id="fileList"></tbody>
           </table>
       </div>
       <button type="button" class="layui-btn" id="uploadListAction">开始上传</button>
</div>
</div>

</body>
<script src="layui/layui.all.js"></script>
<script src="js/student/upload.js"></script>

</html>