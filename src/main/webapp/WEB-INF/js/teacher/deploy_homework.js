layui.use(['jquery', 'element', 'layer', 'upload', "form", "laydate", "layedit"], function () {
    let $ = layui.jquery,
        laydate = layui.laydate,
        element = layui.element,
        layer = layui.layer,
        upload = layui.load,
        form = layui.form,
        layedit = layui.layedit;

    var edit = layedit.build("taskTips");

    //常规用法
    laydate.render({
        elem: '#date',
        type: "datetime"
    });

    layer.ready(function () {
    })
});