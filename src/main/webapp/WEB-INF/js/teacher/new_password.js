layui.use(['table', 'element', 'layer', "jquery", "upload", "form"], function () {
    let table = layui.table,
        element = layui.element,
        layer = layui.layer,
        $ = layui.jquery,
        form = layui.form;

    form.on("submit(next_step)", function (data) {
        $.post("./update_password",data.field,function (callback) {
            if(callback.code === 0){
                layer.alert(callback.msg, function (index) {
                    window.location.href="../logintPage";
                    layer.close(index);
                });
            }else{
                layer.alert(callback.msg);
            }
        });
    });
});