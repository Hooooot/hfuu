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
        <table class="layui-table" style="table-layout:fixed">
            <thead>
            <tr>
                <th>文件名</th>
                <th>大小</th>
                <th>状态</th>
                <th>进度</th>
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
<script>
    //创建监听函数
    var xhrOnProgress=function(fun) {
        xhrOnProgress.onprogress = fun; //绑定监听
        //使用闭包实现监听绑
        return function() {
            //通过$.ajaxSettings.xhr();获得XMLHttpRequest对象
            var xhr = $.ajaxSettings.xhr();
            //判断监听函数是否为函数
            if (typeof xhrOnProgress.onprogress !== 'function')
                return xhr;
            //如果有监听函数并且xhr对象支持绑定时就把监听函数绑定上去
            if (xhrOnProgress.onprogress && xhr.upload) {
                xhr.upload.onprogress = xhrOnProgress.onprogress;
            }
            return xhr;
        }
    }
    layui.use('upload', function() {
        var $ = layui.jquery,
            upload = layui.upload;


        //拖拽上传 多文件列表示例
        var fileListView = $('#fileList'),
            uploadListIns = upload.render({
                elem: '#uploadList',
                url: '',
                accept: 'file',
                multiple: true,
                auto: false,
                bindAction: '#uploadListAction',
                xhr:xhrOnProgress,
                progress: function(index,value){//上传进度回调 value进度值
                    element.progress('progressBar'+index, value+'%');//设置页面进度条
                },
                choose: function(obj) {
                    var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                    //读取本地文件
                    obj.preview(function(index, file, result) {
                        var tr = $(['<tr id="upload-' + index + '">', '<td>' + file.name + '</td>', '<td>' + (file.size / 1014).toFixed(1) + 'kb</td>', '<td>等待上传</td>', '<td>'
                            ,'<div class="layui-progress layui-progress-big layui-progress-radius-fix" lay-showpercent="true" lay-filter="progressBar'+index+'">'
                            ,'<div class="layui-progress-bar" lay-percent="0%">'
                            ,'<span class="layui-progress-text">0%</span>'
                            ,'</div>'
                            ,'</div>'
                            ,'</td>'
                            ,'<td>', '<button class="layui-btn layui-btn-mini file-reload layui-hide">重传</button>', '<button class="layui-btn layui-btn-mini layui-btn-danger file-delete">删除</button>', '</td>', '</tr>'].join(''));

                        //单个重传
                        tr.find('.file-reload').on('click', function() {
                            obj.upload(index, file);
                        });

                        //删除
                        tr.find('.file-delete').on('click', function() {
                            delete files[index]; //删除对应的文件
                            tr.remove();
                            uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                        });

                        fileListView.append(tr);
                    });
                },
                done: function(res, index, upload) {
                    if (res.code == 0) { //上传成功
                        var tr = fileListView.find('tr#upload-' + index),
                            tds = tr.children();
                        tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                        tds.eq(3).find('.layui-progress-bar').css('background-color','#86EAA1');
                        tds.eq(4).html(''); //清空操作
                        return delete this.files[index]; //删除文件队列已经上传成功的文件
                    }
                    this.error(index, upload);
                },
                error: function(index, upload) {
                    var tr = fileListView.find('tr#upload-' + index),
                        tds = tr.children();
                    tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
                    tds.eq(3).find('.layui-progress-bar').css('background-color','#ff5722');
                    tds.eq(4).find('.file-reload').removeClass('layui-hide'); //显示重传
                }
            });
    });
</script>
</html>
