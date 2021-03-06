layui.config({
    base: '/hfuu/js/common/'      //模块存放的目录
}).extend({
    cookie: 'jquery.cookie'
});
layui.use(['jquery', 'layer', 'form', 'upload', "cookie"], function () {
    const $ = layui.jquery,
        layer = layui.layer,
        form = layui.form,
        cookie = layui.cookie;

    $(function () {
        let name = $.cookie("teacherNum");
        let pw = $.cookie("teacherPw");
        if (name != null && pw != null) {
            $('#name').val(name);
            $('#pw').val(pw);
        }
    });

    $('#submit').on("click", function () {
        let num = $('#tc_num').val();
        let pw = $('#pw').val();
        let url = "teacher/login";
        let urlData = {
            tcNum: num,
            pw: pw
        };
        $.post(url,
            urlData,
            function (callback) {
                if (callback.code === 1) {
                    layer.alert(callback.msg, "登录失败");
                } else {
                    if ($("input[type='checkbox']").is(":checked")) {
                        $.cookie("teacherNum", name, {expires: 7, path: '/hfuu'});
                        $.cookie("teacherPw", pw, {expires: 7, path: '/hfuu'});
                    } else {
                        $.cookie("teacherNum", "", {expires: -1, path: '/hfuu'});
                        $.cookie("teacherPw", "", {expires: -1, path: '/hfuu'});
                    }
                    window.location.href = callback.url;
                }
            }
        )
    });
});