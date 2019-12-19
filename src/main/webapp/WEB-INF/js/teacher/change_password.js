layui.use(['table', 'element', 'layer', "jquery", "upload", "form"], function () {
    let table = layui.table,
        element = layui.element,
        layer = layui.layer,
        $ = layui.jquery,
        form = layui.form;

    form.on("submit(submit_pwd)", function (data) {
        $.post("./change_password_check",data.field,function (callback) {
            if(callback.code === 0){
                layer.alert(callback.msg, function (index) {
                    window.location.reload();
                    layer.close(index);
                });
            }else{
                layer.alert(callback.msg);
            }
        });
    });
});