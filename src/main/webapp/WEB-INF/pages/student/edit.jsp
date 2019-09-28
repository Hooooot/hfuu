<%--
  Created by IntelliJ IDEA.
  User: 16688
  Date: 2019/9/22
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <link rel="stylesheet" href="weditor/wangEditor.min.css">
    <style type="text/css">
        body {
            margin: 10px;
            min-width: 1000px;
            overflow: auto;
        / / 当页面的可视区域的宽度小于页面设置的最小宽度的时候，出现滚动条
        }

        .button-submit {
            width: 100vw; /*获取页面宽度*/
            margin-top: 10px;
            text-align: center;
        }
    </style>
</head>
<body>


<h1 style="text-align: center;margin: 10px;"></h1>
<div id="editor">
    <p>输入实验报告内容</p>
</div>


<div class="layui-btn-group button-submit">
    <button type="button" class="layui-btn layui-btn-primary" id="btn1">获取html</button>
    <button type="button" class="layui-btn layui-btn-primary" id="btn2">获取text</button>
    <button type="button" class="layui-btn layui-btn-primary" id="btn3">获取JSON</button>
    <button type="button" class="layui-btn layui-btn-primary" id="btn4">预览</button>
</div>
<script src="weditor/wangEditor.min.js"></script>
<script src="layui/layui.all.js"></script>
<script>
    layui.use(['element', 'jquery', 'layer'], function () {
        var $ = layui.$,
            layer = layui.layer,
            element = layui.element;
        var text = localStorage.getItem("text");//获取传值 实验名称
        localStorage.removeItem("text");

        var E = window.wangEditor;
        var editor = new E('#editor'); // 两个参数也可以传入 elem 对象，class 选择器

        //开启debug模式
        editor.customConfig.debug = true;
        // 隐藏“网络图片”tab
        editor.customConfig.showLinkImg = false;
        // 关闭粘贴样式的过滤
        editor.customConfig.pasteFilterStyle = false;
        // 忽略粘贴内容中的图片
        editor.customConfig.pasteIgnoreImg = true;
        // 自定义处理粘贴的文本内容
        editor.customConfig.pasteTextHandle = function (content) {
            // content 即粘贴过来的内容（html 或 纯文本），可进行自定义处理然后返回
            return content + '<p>这是我复制过来的</p>'
        }

        // 上传图片到服务器
        editor.customConfig.uploadImgServer = 'upload';//设置上传文件的服务器路径
        editor.customConfig.uploadFileName = 'image';//设置文件上传的参数名称
        editor.customConfig.uploadImgMaxSize = 3 * 1024 * 1024; // 将图片大小限制为 3M
        //自定义 header

        //监听函数
        editor.customConfig.uploadImgHooks = {
            before: function (xhr, editor, files) {
                // 图片上传之前触发
                // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，files 是选择的图片文件

                // 如果返回的结果是 {prevent: true, msg: 'xxxx'} 则表示用户放弃上传
                // return {
                //     prevent: true,
                //     msg: '放弃上传'
                // }
            },
            success: function (xhr, editor, result) {
                // 图片上传并返回结果，图片插入成功之后触发
                // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
                alert(result);
            },
            fail: function (xhr, editor, result) {
                // 图片上传并返回结果，但图片插入错误时触发
                // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
                alert(result);
            },
            error: function (xhr, editor) {
                // 图片上传出错时触发
                // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
                alert(xhr);
            },
            timeout: function (xhr, editor) {
                // 图片上传超时时触发
                // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
            },

            // 如果服务器端返回的不是 {errno:0, data: [...]} 这种格式，可使用该配置
            // （但是，服务器端返回的必须是一个 JSON 格式字符串！！！否则会报错）
            customInsert: function (insertImg, result, editor) {
                // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
                // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果

                // 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：
                var url = result.url
                insertImg(url)

                // result 必须是一个 JSON 格式字符串！！！否则报错
            }
        };




        editor.create();


        document.getElementById('btn1').addEventListener('click', function () {
            // 读取 html
            alert(editor.txt.html())
        }, false);

        document.getElementById('btn2').addEventListener('click', function () {
            // 读取 text
            alert(editor.txt.text())
        }, false);

        document.getElementById('btn3').addEventListener('click', function () {
            var json = editor.txt.getJSON()  // 获取 JSON 格式的内容
            var jsonStr = JSON.stringify(json)
            console.log(json)
            console.log(jsonStr)
            alert(jsonStr)
        });

        //预览
        document.getElementById('btn4').addEventListener('click', function () {
            var _txt = editor.txt.html();

            layer.open({

                type: 1,
                area: ['100%', '100%'],
                title: [text, 'font-size:18px;text-align: center;'],
                id: '_txt', //设定一个id，防止重复弹出
                shadeClose: true,
                anim:2,
                content: '<!doctype html><html lang="zh"><head><title></title></head><body>'+_txt+'</body></html>',
            });
        });

        $(document).ready(function () {
            getExperimentName();
        });

        function getExperimentName() {
            $("h1").text(text);
        }

    });
</script>
</body>
</html>
