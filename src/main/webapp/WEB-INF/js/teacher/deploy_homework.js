let date_extend = document.createElement("script");
date_extend.src='../js/common/date_extend.js';
document.body.appendChild(date_extend);

layui.extend({
    layUploader: '../layui/uploader_ext/layUploader'
}).use(['jquery', 'layer', "laydate", "layedit", "layUploader", "form"], function () {
    let $ = layui.jquery,
        laydate = layui.laydate,
        layer = layui.layer,
        layedit = layui.layedit,
        layUploader = layui.layUploader,
        form = layui.form;

    layedit.build("taskTips");

    //常规用法
    laydate.render({
        elem: '#date'
        ,type: "datetime"
    });

    form.on("switch(upload_switch)", function (data) {
        if(!data.elem.checked){
            $('.upload_content').html("");
            return;
        }
        layUploader.render({
            //  上传文件服务器地址，必填
            url: './upload/',
            //  上传文件url携带的参数
            params: {
                "pageId": $('meta[name="pageId"]').attr("content")
            },
            contentDom: ".upload_content",
            //  表单提交的url
            formUrl: './newTask/',
            // 提交表单额外的参数
            formUrlParams: {},
            fileAmount: 4,
            //  md5验证地址，不填无md5验证，可不填
            md5: './md5check/',
            //  单个文件大小，有默认值，可不填，单位B
            size: 10240 * 1024 * 1024,
            //  允许上传文件格式,有默认值，可不填
            fileType: 'doc,docx,iso,bat,jpg,jpeg,png,mp4,exe,pdf,bmp'
        });
    });

    layer.ready(function () {
        let day = new Date();
        day.setDate(day.getDate() + 7);
        $("#date").attr("placeholder", day.format("yyyy-MM-dd hh:mm:ss"));
    })
});