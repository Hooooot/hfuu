layui.define(['element', 'form', "layer"], function(exports) {
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
            success: function() {
                //判断是否设置过头像，如果设置过则修改顶部、左侧和个人资料中的头像，否则使用默认头像
                if (window.sessionStorage.getItem('userFace') && $(".userAvatar").length > 0) {
                    $(".userAvatar").attr("src", $(".userAvatar").attr("src").split("images/")[0] + "images/" + window.sessionStorage.getItem('userFace').split("images/")[1]);
                }
            }
        })
        $(".admin-header-lock-input").focus();
    }
    $(".lockpc").on("click", function() {
        window.sessionStorage.setItem("lockpc", true);
        lockPage();
    })
    // 判断是否显示锁屏
    if (window.sessionStorage.getItem("lockpc") == "true") {
        lockPage();
    }
    // 解锁
    $("body").on("click", "#unlock", function() {
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

    $(document).on('keydown', function(event) {
        var event = event || window.event;
        if (event.keyCode == 13) {
            $("#unlock").click();
        }
    });

    /*获取项目的根路径*/
    function progecepath() {
        return window.document.location.href;
    };

    /*弹出框 */
    $('.showNotice').on('click', function(event) {
        /*layer.tab({
            area: ['600px', '300px'],
            tab: [{
                title: 'TAB1',
                content: '内容1'
            }, {
                title: 'TAB2',
                content: '内容2'
            }, {
                title: 'TAB3',
                content: '内容3'
            }]
        });*/

        layer.open({
            /*type: 2
            ,title: false //不显示标题栏false
            ,closeBtn: 2 //关闭按钮
            ,area: ['600px', '300px']
            ,shade: 0.3
            ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
            ,btn: ['火速围观', '残忍拒绝']
            ,btnAlign: 'c'
            ,moveType: 0//拖拽模式，0或者1
            ,content: '${crx}/personaldeta'
            ,success: function(layero){
                var btn = layero.find('.layui-layer-btn');
                btn.find('.layui-layer-btn0').attr({
                    href: 'http://www.layui.com/'
                    ,target: '_blank'
                });
            }*/


            type: 2,
            area: ['60%', '500px'],
            title: false,
            id: 'LAY_layuipro', //设定一个id，防止重复弹出
            shadeClose: true,
            content: progecepath() + 'login'

        });


    });

    /*导航栏 ----修改密码*/
    $('.showChangePassword').on('click', function(event) {

       $('.changePassword').trigger('click')

    });

    /*导航栏 ----个人信息*/
    $('.showUserInfo').on('click', function(event) {

        $('.userInfo').trigger('click')

    });
    exports('lock');
});