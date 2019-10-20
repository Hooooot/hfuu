layui.extend({
    layUploader: '../layui/uploader_ext/layUploader'
}).use(['jquery', 'layer', 'upload', "laydate", "layedit", "layUploader"], function () {
    let $ = layui.jquery,
        laydate = layui.laydate,
        layer = layui.layer,
        upload = layui.upload,
        layedit = layui.layedit,
        layUploader = layui.layUploader;

    layedit.build("taskTips");

    //常规用法
    laydate.render({
        elem: '#date'
        ,type: "datetime"
    });

    $("#plupload").on("click",(function () {
        layUploader.render({
            url:'/up',//上传文件服务器地址，必填
            md5:'/md5',//md5验证地址，不填无md5验证，可不填
            size:200*1024*1024,//单个文件大小，有默认值，可不填，单位B
            fileType:'doc,docx,iso'//允许上传文件格式,有默认值，可不填
        });
    }));


    //多文件列表示例
    let fileListView = $('#fileList')
        ,uploadListIns = upload.render({
        elem: '#upload_drag'
        ,url: './upload/'
        ,accept: 'file'
        ,multiple: true
        ,auto: false
        ,bindAction: '#uploadAction'
        ,data: {"pageId": $('meta[name="pageId"]').attr("content")}
        ,choose: function(obj){
            let files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
            //读取本地文件
            obj.preview(function(index, file, result){
                let fileSizeHtml = "";
                let sizeKB = file.size/1024.0;
                let sizeMB = sizeKB/1024.0;
                if(sizeMB>750){
                    layer.alert("上传附件大小不能超过750MB！");
                    return;
                }
                if(sizeMB > 1){
                    fileSizeHtml = '<td>'+ sizeMB.toFixed(2) +'MB</td>';
                }else{
                    fileSizeHtml = '<td>'+ sizeKB.toFixed(2) +'KB</td>';
                }
                let tr = $(['<tr id="upload-'+ index +'">'
                    ,'<td>'+ file.name +'</td>'
                    ,fileSizeHtml
                    ,'<td>等待上传</td>'
                    ,'<td>'
                    ,'<button type="button" class="layui-btn layui-btn-xs file-reload layui-hide">重传</button>'
                    ,'<button type="button" class="layui-btn layui-btn-xs layui-btn-danger file-delete">删除</button>'
                    ,'</td>'
                    ,'</tr>'].join(''));

                //单个重传
                tr.find('.file-reload').on('click', function() {
                    obj.upload(index, file);
                });

                //删除
                tr.find('.file-delete').on('click', function(){
                    delete files[index]; //删除对应的文件
                    tr.remove();
                    uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                });
                fileListView.append(tr);
            });
        }
        ,done: function(res, index, upload){
            if(res.code === 0){ //上传成功
                let tr = fileListView.find('tr#upload-'+ index)
                    ,tds = tr.children();
                tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                tds.eq(3).html(''); //清空操作
                return delete this.files[index]; //删除文件队列已经上传成功的文件
            }
            this.error(index, upload);
        }
        ,error: function(index, upload){
            let tr = fileListView.find('tr#upload-'+ index)
                ,tds = tr.children();
            tds.eq(2).html('<span >上传失败</span>');
            tds.eq(3).find('.file-reload').removeClass('layui-hide'); //显示重传
        }
    });

    /**
     *对Date的扩展，将 Date 转化为指定格式的String
     *月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
     *年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
     *例子：
     *(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
     *(new Date()).Format("yyyy-M-d h:m:s.S")  ==> 2006-7-2 8:9:4.18
     */
    Date.prototype.format = function (fmt) {
        let o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        for (let k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    };

    layer.ready(function () {
        let day = new Date();
        day.setDate(day.getDate() + 7);
        $("#date").attr("placeholder", day.format("yyyy-MM-dd hh:mm:ss"));
    })
});