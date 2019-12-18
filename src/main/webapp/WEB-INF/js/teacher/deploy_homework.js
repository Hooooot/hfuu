/**
 * 导入时间类的扩展方法
 * @see js/common/date_extend.js
 * */
let date_extend = document.createElement("script");
date_extend.src = '../js/common/date_extend.js';
document.head.appendChild(date_extend);

layui.extend({
    layUploader: '../layui/uploader_ext/layUploader'
}).use(['jquery', 'layer', "laydate", "layedit", "layUploader", "form"], function () {
    let $ = layui.jquery,
        laydate = layui.laydate,
        layer = layui.layer,
        layedit = layui.layedit,
        layUploader = layui.layUploader,
        form = layui.form;

    let layeditID = layedit.build('taskTips', {
        'uploadImage': {
            url: './layuiUploadImg/',
            type: 'post'
        },
        height: 350
    });

    //常规用法
    laydate.render({
        elem: '#date'
        , type: "datetime"
        , value: new Date(new Date().setDate(new Date().getDate() + 7))
    });

    form.on("switch(upload_switch)", function (data) {
        let submitBtn = $('.task_submit');
        if (!data.elem.checked) {
            $('.upload_content').html("");
            submitBtn.attr("lay-filter", "submit_btn_no_files");
            return;
        }
        submitBtn.attr("lay-filter", "submit_btn");
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
            formSubmitBtn: 'submit_btn',
            fileAmount: 4,
            //  md5验证地址，不填无md5验证，可不填
            md5: './md5check/',
            //  单个文件大小，有默认值，可不填，单位B
            size: 10240 * 1024 * 1024,
            //  允许上传文件格式,有默认值，可不填
            fileType: 'doc,docx,iso,bat,jpg,jpeg,png,mp4,exe,pdf,bmp',
            layeditID: layeditID
        });
    });

    function getRichTextHTML(index) {
        let richTextHTML = $('#LAY_layedit_' + index);
        return "<html>" + richTextHTML.contents().find("html").html() + "</html>";
    }

    form.on("submit(submit_btn_no_files)", function (data) {
        $(data.form).find('textarea[name="description"]').text(getRichTextHTML(layeditID));
        let param = $(data.form).serialize();
        $.post('./newTask/',
            param
            , function (callback) {
                layer.msg(callback.msg, function () {
                    location.reload();
                });
            }
        );
    });

    function addClick() {
        let selectContent = $('#clazz_course_select_temp').html();
        let selects = $('.course_selects');
        let num = $('.clazz_course_select').length;
        if (num >= 1) {
            $('.del_select').show();
        }
        selects.append(selectContent);
        form.render('select');
    }

    function delClick() {
        let parent = $(this).parent();
        let num = $('.clazz_course_select').length;
        if (num <= 2) {
            $('.del_select').hide();
        }
        parent.remove();
        form.render('select');
    }

    layer.ready(function () {
        $("body").on('click', '.add_select', addClick);
        $('body').on('click', '.del_select', delClick);
        let num = $('.clazz_course_select').length;
        if (num <= 1) {
            $('.del_select').hide();
        }
    });
});