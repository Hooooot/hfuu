layui.use(['table', 'element', 'layer', "jquery", "upload", "form"], function () {
    let table = layui.table,
        element = layui.element,
        layer = layui.layer,
        $ = layui.jquery,
        form = layui.form;

    form.on('submit(setmyinfo)', function(data){
        $.post("./set_info",
            data.field
            ,function (callback) {
                layer.alert(callback.msg, function () {
                    window.location.reload();
                });
            });
        return false;
    });

    function refreshVerifyCode(){
        $.post("./get_verify_code",
            {
                refresh:Math.random()
            }, function (callback) {
                if(callback.code===0)
                    $("#img_base64").attr("src", "data:image/jpeg;base64," + callback.base64);
                else
                    layer.alert("验证码获取失败！请重试！");
            });
    }

    $(function () {
        refreshVerifyCode();
    });

    $("#img_base64").on("click", function () {
        refreshVerifyCode();
    });


    form.on("submit(next_step)", function (data) {
        $.post("./forget_pwd_next",data.field,function (callback) {
            if(callback.code === 0){
                window.location.href = "./" + callback.url;
            }else if(callback.code === 1){
                layer.alert("验证码错误！请重试！", function (index) {
                    $("#verify_code_text").val("");
                    refreshVerifyCode();
                    layer.close(index);
                })
            }else if(callback.code ===2){
                layer.alert("工号与邮箱不匹配！请确认是否绑定邮箱或输入错误！", function (index) {
                    $("#verify_code_text").val("");
                    refreshVerifyCode();
                    layer.close(index);
                })
            }
        });
    });

});