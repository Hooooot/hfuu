layui.define(['element', 'form', "layer"], function (exports) {
    var form = layui.form,
        $ = layui.jquery,
        layer = layui.layer;


    //锁屏
    function lockPage() {
        layer.open({
            title: false,
            type: 1,
            content: '<div class="admin-header-lock" id="lock-box">' +
                '<div class="admin-header-lock-img"><img src="images/face.jpg" class="userAvatar"/></div>' +
                '<div class="admin-header-lock-name" id="lockUserName">Starry</div>' +
                '<div class="input_btn">' +
                '<input type="password" class="admin-header-lock-input layui-input" autocomplete="off" placeholder="请输入密码解锁.." name="lockPwd" id="lockPwd" />' +
                '<button class="layui-btn" id="unlock">解锁</button>' +
                '</div>' +
                '<p>请输入“123456”，否则不会解锁成功哦！！！</p>' +
                '</div>',
            closeBtn: 0,
            shade: 0.9,
            success: function () {
                //判断是否设置过头像，如果设置过则修改顶部、左侧和个人资料中的头像，否则使用默认头像
                if (window.sessionStorage.getItem('userFace') && $(".userAvatar").length > 0) {
                    $(".userAvatar").attr("src", $(".userAvatar").attr("src").split("images/")[0] + "images/" + window.sessionStorage.getItem('userFace').split("images/")[1]);
                }
            }
        });
        $(".admin-header-lock-input").focus();
    }

    $(".lockpc").on("click", function () {
        window.sessionStorage.setItem("lockpc", true);
        lockPage();
    });
    // 判断是否显示锁屏
    if (window.sessionStorage.getItem("lockpc") == "true") {
        lockPage();
    }
    // 解锁
    $("body").on("click", "#unlock", function () {
        if ($(this).siblings(".admin-header-lock-input").val() == '') {
            layer.msg("请输入解锁密码！");
            $(this).siblings(".admin-header-lock-input").focus();
        } else {
            if ($(this).siblings(".admin-header-lock-input").val() == "123456") {
                window.sessionStorage.setItem("lockpc", false);
                $(this).siblings(".admin-header-lock-input").val('');
                layer.closeAll("page");
            } else {
                layer.msg("密码错误，请重新输入！");
                $(this).siblings(".admin-header-lock-input").val('').focus();
            }
        }
    });

    $(document).on('keydown', function (event) {
        var event = event || window.event;
        if (event.keyCode == 13) {
            $("#unlock").click();
        }
    });

    /*弹出框 */
    $('.showNotice').on('click', function (event) {
        layer.open({

            type: 2,
            area: ['60%', '500px'],
            title: false,
            shadeClose: true,
            content: 'http://localhost:8080/hfuu/student/' + 'logins',

        });


    });

    /*导航栏 ----修改密码*/
    $('.showChangePassword').on('click', function (event) {

        $('.changePassword').trigger('click')

    });

    /*导航栏 ----个人信息*/
    $('.showUserInfo').on('click', function (event) {

        $('.userInfo').trigger('click')

    });
    exports('lock');
});