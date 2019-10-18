layui.config({
    base: '/hfuu/js/teacher/'      //模块存放的目录
}).extend({
    cookie: 'jquery.cookie'
});
layui.use(['jquery', 'layer', 'form', 'upload', "cookie"], function () {
    const $ = layui.jquery,
        layer = layui.layer,
        form = layui.form,
        cookie = layui.cookie;

    $(function () {
        let name = $.cookie("teacherName");
        let pw = $.cookie("teacherPw");
        if(name != null && pw != null){
            $('#name').val(name);
            $('#pw').val(pw);
        }
    });
    
    $('#submit').on("click", function () {
       let name = $('#name').val();
       let pw = $('#pw').val();
       let url = "teacher/login";
       let urlData = {
           name:name,
           pw:pw
       };
       $.post(url,
           urlData,
           function (data) {
               if(data === "LOGIN_FAIL"){
                   layer.alert("用户名或密码错误！", "登录失败");
               }else{
                   if ($("input[type='checkbox']").is(":checked")) {
                       $.cookie("teacherName", name, {expires: 7, path: '/hfuu'});
                       $.cookie("teacherPw", pw, {expires: 7, path: '/hfuu'});
                   }else{
                       $.cookie("teacherName", "", {expires: -1, path: '/hfuu'});
                       $.cookie("teacherPw", "", {expires: -1, path: '/hfuu'});
                   }
                   window.location.href = data;
               }
           }
       )
    });
});