layui.use(['table', 'element', 'layer', "jquery", "upload", "form"], function () {
    let table = layui.table,
        element = layui.element,
        layer = layui.layer,
        $ = layui.jquery,
        form = layui.form;
    let coldDownSecond = 60;

    form.on("submit(next_step)", function (data) {
        $.post("./check_email_ic",data.field,function (callback) {
            if(callback.code === 0){
                window.location.href=callback.url;
            }else{
                layer.alert("验证码错误！");
            }
        });
    });

    window.sendEmailClick = function (){
        let btn = $("#send_email_btn");
        let interval;
        btn.addClass("layui-btn-disabled");
        btn.attr("disable", true);
        btn.removeAttr("onclick");
        interval = setInterval(function () {
            let btn = $("#send_email_btn");
            btn.html("等待" + coldDownSecond + "秒");
            coldDownSecond--;
            if(coldDownSecond < 0){
                clearInterval(interval);
                btn.html("发送验证码");
                btn.removeClass("layui-btn-disabled");
                btn.removeAttr("disabled");
                btn.attr("onclick", "sendEmailClick();");
                coldDownSecond = 60;
            }
        },1000);
        $.post("./send_email",
            {refresh:Math.random()},
            function (callback) {
                layer.alert(callback.msg);
            })
    }
});