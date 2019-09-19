layui.use(['jquery', 'layer', 'element', 'upload'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        element = layui.element,
        upload = layui.upload;

    //触发事件
    var active = {
        tabAdd: function (title, content, layid) {
            //新增一个Tab项
            element.tabAdd('tab', {
                title: title
                , content: content
                , id: layid
            })
            $(".layui-tab-item").height("100%");
        }
        , tabDelete: function (othis, layid) {
            //删除指定Tab项
            element.tabDelete('tab', layid); //删除：lay-id为layid的tab
            othis.addClass('layui-btn-disabled');
        }
        , tabChange: function (layid) {
            //切换到指定Tab项
            element.tabChange('tab', layid); //切换到：lay-id为layid的tab
        }
    };

    // 页面加载完成后显示首页tab
    layer.ready(function () {
        active['tabAdd'].call(this, "首页", "<iframe frameborder='0' width='100%' height='100%' " +
            "src='console'>您的浏览器暂不支持iframe功能，请更换。</iframe>", "0")
        active['tabChange'].call(this, "0")
    })

    $(document).on("click", ".home", function () {
        var exist = $("li[lay-id='0']").length;
        if (exist === 0) {
            active['tabAdd'].call(this, "首页", "<iframe frameborder='0' width='100%' height='100%' " +
                "src='deploy_homework'>您的浏览器暂不支持iframe功能，请更换。</iframe>", "0")
        }
        active['tabChange'].call(this, "0")
    })

    $(document).on("click", ".new-homework", function () {
        var exist = $("li[lay-id='1']").length;
        if (exist === 0) {
            active['tabAdd'].call(this, "新建作业", "<iframe frameborder='0' width='100%' height='100%' " +
                "src='deploy_homework'>您的浏览器暂不支持iframe功能，请更换。</iframe>", "1")
        }
        active['tabChange'].call(this, "1")
    })

    $(document).on("click", ".already-homework", function () {
        var exist = $("li[lay-id='2']").length;
        if (exist === 0) {
            active['tabAdd'].call(this, "已发布的作业", "<iframe frameborder='0' width='100%' height='100%' " +
                "src='already_homework'>您的浏览器暂不支持iframe功能，请更换。</iframe>", "2")
        }
        active['tabChange'].call(this, "2")
    });


})