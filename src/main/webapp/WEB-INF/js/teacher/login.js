layui.use(['jquery', 'layer', 'form', 'upload'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        form = layui.form,
        upload = layui.upload;

    $(document).on("click", ".test", function () {
        layer.alert("asdas")
    })

})