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
    <link rel="stylesheet" href="../layui/css/layui.css">
    <link rel="stylesheet" href="../weditor/wangEditor.css">
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
<h3 style="text-align: center;margin: 10px;"></h3>
<div id="editor">
</div>

<div class="layui-btn-group button-submit" style="margin-top: 50px;">
    <button type="button" class="layui-btn" id="save">保存</button>
    <button type="button" class="layui-btn layui-btn-primary" id="preview">预览</button>
    <button type="button" class="layui-btn" id="submit">提交</button>
</div>
<script src="../weditor/wangEditor.js"></script>
<script src="../layui/layui.all.js"></script>
<script>

    layui.use(['element', 'jquery', 'layer', 'upload'], function () {
        var $ = layui.$,
            layer = layui.layer,
            upload = layui.upload,
            element = layui.element;

        var taskName;//实验名称
        var taskDesc;//实验描述
        var taskId;//任务id
        var stuNum = "${studentLogin.stuNum}";//学号
        var subRichTextPath;//富文本内容保存的路径
        var subId;//提交id
        var tableId;//父类表格id


        $(document).ready(function () {
            //实验相关信息
            getExperiment();

            $.ajax({
                type: "POST",
                url: './getContent',
                data: {'subRichTextPath': subRichTextPath},
                dataType: "json",
                success: function (data) {
                    //初始化editor内容
                    editor.txt.html(data.data);
                }, error: function () {
                    layer.msg("加载失败")
                }
            });

        });

        function getExperiment() {
            //从父层获取值，json是父层的全局js变量。eval是将该string类型的json串变为标准的json串
            var parent_json = eval('(' + parent.json + ')');
            tableId = eval('(' + parent.table_json + ')');
            //console.log(tableId);
            taskName = parent_json.taskName;
            taskDesc = parent_json.taskDesc;
            taskId = parent_json.taskId;
            subRichTextPath = parent_json.subRichTextPath;
            subId = parent_json.subId;
            $("h1").text(taskName);
            $("h3").text(taskDesc);
        }


        var E = window.wangEditor;
        var editor = new E('#editor'); // 两个参数也可以传入 elem 对象，class 选择器

        // 通过 url 参数配置 debug 模式。url 中带有 wangeditor_debug_mode=1 才会开启 debug 模式
        editor.customConfig.debug = location.href.indexOf('wangeditor_debug_mode=1') > 0;
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
        };

        // 限制一次最多上传 1张图片
        editor.customConfig.uploadImgMaxLength = 1;
        //自定义 header
        /*editor.customConfig.uploadImgHeaders = {
            'Accept': 'application/json'
        }*/
        // 上传图片到服务器
        editor.customConfig.uploadImgServer = 'uploadImg';//设置上传文件的服务器路径
        editor.customConfig.uploadFileName = 'image';//设置文件上传的参数名称
        editor.customConfig.uploadImgMaxSize = 3 * 1024 * 1024; // 将图片大小限制为 3M
        // 设置 headers（举例）

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

            },
            fail: function (xhr, editor, result) {
                // 图片上传并返回结果，但图片插入错误时触发
                // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
            },
            error: function (xhr, editor) {
                // 图片上传出错时触发
                // xhr 是 XMLHttpRequst 对象，editor 是编辑器对
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
                var url = result.data;
                insertImg(url)
                // result 必须是一个 JSON 格式字符串！！！否则报错
            }
        };


        editor.create();
        //提交页面
        document.getElementById('submit').addEventListener('click', function () {
            if (subRichTextPath == null && subId == null) {
                //编辑后直接提交
                subId = 0;
            }
            $.ajax({
                type: "POST",
                url: './submitHtml',
                data: {
                    'content': editor.txt.html(),
                    'subRichTextPath': subRichTextPath,
                    'subId': subId,
                    'taskId': taskId,
                    'stuNum': stuNum
                },
                dataType: "json",
                success: function (data) {
                    if (data && data.success == "true") {
                        layer.msg("提交成功", {
                            icon: 6
                        });
                        ///////////////////////////////////////////
                        parent.layui.table.reload(tableId);
                    }
                    //等待900毫秒后自动进行当前子窗口的关闭
                    setTimeout(function () {
                        //当你在iframe页面关闭自身时
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                    }, 900);
                }, error: function () {
                    layer.msg("提交失败", {
                        icon: 5
                    })
                }
            })
        });

        //预览
        document.getElementById('preview').addEventListener('click', function () {
            var _txt = editor.txt.html();

            layer.open({
                type: 1,
                area: ['100%', '100%'],
                title: [taskName, 'font-size:18px;text-align: center;'],
                id: '_txt', //设定一个id，防止重复弹出
                shadeClose: true,
                closeBtn: 1,
                anim: 2,
                content: '<!doctype html><html lang="zh"><head><title></title></head><body>' + _txt + '</body></html>',
            });
        });

        //保存页面
        document.getElementById('save').addEventListener('click', function () {
            if (subId == null) {
                subId = 0;
            }
            $.ajax({
                type: "POST",
                url: './saveHtml',
                data: {
                    'content': editor.txt.html(),
                    'taskId': taskId,
                    'stuNum': stuNum,
                    'subRichTextPath': subRichTextPath,
                    'subId': subId
                },
                dataType: "json",
                success: function (data) {
                    if (data && data.success == "true") {
                        layer.msg("保存成功", {
                            icon: 6
                        });
                        ///////////////////////////////////////////
                        parent.layui.table.reload(tableId);
                    }
                    //等待900毫秒后自动进行当前子窗口的关闭
                    setTimeout(function () {
                        //当你在iframe页面关闭自身时
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                    }, 900);
                }, error: function () {
                    layer.msg("保存失败", {
                        icon: 5
                    })
                }
            })
        });
    });
</script>
</body>
</html>
