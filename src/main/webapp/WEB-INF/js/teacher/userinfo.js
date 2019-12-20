layui.use(['table', 'element', 'layer', "jquery", "upload", "form"], function () {
    let table = layui.table,
        element = layui.element,
        layer = layui.layer,
        $ = layui.jquery,
        form = layui.form,
        upload = layui.upload;

    //普通图片上传
    let uploadInst = upload.render({
        elem: '#upload_btn'
        ,url: './layuiUploadAvatar'
        ,data: {
            tcNum: function(){
                return $('#tc_num').val();
            }
        }
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#img').attr('src', result); //图片链接（base64）
            });
        }
        ,done: function(res){
            //如果上传失败
            if(res.code > 0){
                return layer.msg('上传失败');
            }
            //上传成功
            layer.alert(res.msg, function (index) {
                parent.layui.$("#tc_avatar").attr("src", "../uploaded/" + res.url);
                layer.close(index);
            })

        }
        ,error: function(){
            //演示失败状态，并实现重传
            let demoText = $('#errorText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                uploadInst.upload();
            });
        }
    });

    form.on('submit(setmyinfo)', function(data){
        $.post("./set_info",
            data.field
            ,function (callback) {
                layer.alert(callback.msg, function (index) {
                    window.location.reload();
                    layer.close(index);
                });
            });
        return false;
    });
});