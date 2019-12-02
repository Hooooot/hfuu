/**
 * extPath为本文件所在文件夹
 * */
const extPath="/hfuu/layui/uploader_ext/";

layui.extend({
    webuploader: extPath + 'uploader/webuploader'
}).define(['layer','laytpl','table','element','webuploader', 'form'],function(exports){
    let $ = layui.$
        ,webUploader= layui.webuploader
        ,element = layui.element
        ,layer=layui.layer
        ,table=layui.table
        ,form=layui.form
        //  保存上传文件属性集合,添加table用
        ,rowData=[]
        //  默认上传文件大小100MB，单位B
        ,fileSize=100*1024*1024
        // 上传文件数量默认限制
        ,fileAmount=4
        ,fileType='doc,docx,pdf,xls,xlsx,ppt,pptx,gif,jpg,jpeg,bmp,png,rar,zip'
        ,uplaod
        ,formParams
        ,enclosure = "";
    layui.link(extPath + 'uploader/webuploader.css');

    let Class = function (options) {
        let that = this;
        that.options=options;
        that.windowContent=
            '<div  class="upload_select" style="clear:both;float: left;margin-left:10px;margin-top: 5px;">选择文件</div>'+
            '<blockquote class="layui-elem-quote" style="margin-left: 150px;">Tip: 可以按Ctrl + V 来粘贴截图或者将文件拖拽至表格中上传</blockquote>'+
            '<div class="upload-dnd">'+
            '<table style="margin-top:-10px;" class="layui-table" id="extend-uploader-form" lay-filter="extend-uploader-form">' +
            '  <thead>' +
            '    <tr>' +
            '      <th lay-data="{type:\'numbers\', fixed:\'left\'}"></th>' +
            '      <th lay-data="{field:\'fileName\'}">文件名称</th>'+
            '      <th lay-data="{field:\'fileSize\', width:200}">文件大小</th>'+
            '      <th lay-data="{field:\'validateMd5\', width:150}">文件验证</th>'+
            '      <th lay-data="{field:\'progress\',templet:\'#button-form-optProcess\'}">进度</th>'+
            '      <th lay-data="{field:\'oper\', width:150, templet: \'#button-form-uploadTalbe\'}">操作</th>'+
            '    </tr>'+
            '  </thead>'+
            '</table>'+
            '</div>'+
            '<script type="text/html" id="button-form-uploadTalbe">'+
            '<a class="layui-btn layui-btn-xs layui-hide file-retry" lay-event="reload">重传</a>'+
            '<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>'+
            '</script>'+
            '<script type="text/html" id="button-form-optProcess">'+
            '<div style="margin-top: 5px;" class="layui-progress layui-progress-big" lay-filter="{{d.fileId}}"  lay-showPercent="true">'+
            '<div class="layui-progress-bar layui-bg-blue" lay-percent="0%"></div>'+
            '</div>'+
            '</script>';
        that.register();
        that.init();
        that.events();
    };

    Class.prototype.init=function(){
        let that = this,
            options=that.options;
        if(!that.strIsNull(options.size)){
            fileSize=options.size
        }
        if(!that.strIsNull(that.options.fileType)){
            fileType=that.options.fileType;
        }
        if(!that.strIsNull(options.fileAmount)){
            fileAmount = options.fileAmount;
        }
        if(!that.strIsNull(options.contentDom)){
            this.innerWindow(this);
        }else{
            this.popWindow(this);
        }
    };

    Class.prototype.popWindow = function (that) {
        layer.open({
            type: 1,
            area: ['900px', '550px'], //宽高
            resize:false,
            content:that.windowContent,
            //  弹出层创建成功
            success: function(layero, index){
                this.createUploadTable(that);
            },
            //  关闭弹出层
            // 可以自行添加按钮关闭,关闭请清空rowData
            end: function() {
                rowData=[];
                if(that.options.success){
                    if(typeof that.options.success==='function') {
                        console.log("options.success() run.");
                        that.options.success();
                    }
                }
            }
        });
    };

    Class.prototype.innerWindow = function(that){
        let dom = $(that.options.contentDom);
        dom.html(that.windowContent);
        this.createUploadTable(that);
    };

    Class.prototype.createUploadTable = function(that){
        table.init('extend-uploader-form', {
            unresize:true
        });
        console.log(that.options.url);
        uplaod = webUploader.create({
            // swf文件路径
            swf:  'src/lib/extend/uploader/Uploader.swf',
            // 默认文件接收服务端。
            server: that.options.url,
            // 文件上传携带的参数
            formData: that.options.params,
            dnd: ".upload-dnd",
            pick: {
                id: '.upload_select',
                multiple: true
            },
            paste: document.body,
            //  单个文件大小
            fileSingleSizeLimit:fileSize,
            // 限制文件数量
            fileNumLimit:fileAmount,
            //接收文件类型--自行添加options
            accept:[{
                title: 'file',
                extensions: fileType,
                mimeTypes: that.buildFileType(fileType)
            }],
            // 不压缩image
            compress: false,
            prepareNextFile: true
        });
        that.reloadData(rowData);
    };

    Class.prototype.formatFileSize=function(size){
        let fileSize ="";
        let sizeKB = size/1024;
        let sizeMB = sizeKB/1024;
        if(sizeMB > 1 && sizeMB < 1024){
            fileSize = sizeMB.toFixed(3) +" MB";
        }else if(sizeMB >= 1024){
            let len = sizeMB/1024;
            fileSize = len.toFixed(3)+" GB";
        }else{
            fileSize = sizeKB.toFixed(3)+" KB";
        }
        return fileSize;
    };

    Class.prototype.buildFileType=function(type) {
        let ts = type.split(',');
        let ty='';

        for(let i=0;i<ts.length;i++){
            ty=ty+ "."+ts[i]+",";
        }
        return ty.substring(0, ty.length - 1);
    };

    Class.prototype.strIsNull=function(str) {
        return typeof str == "undefined" || str == null || str === "";
    };

    Class.prototype.events=function () {
        let that = this;
        //  添加文件
        uplaod.on('fileQueued', function(file){
            let fileSize = that.formatFileSize(file.size);
            let row={fileId:file.id,fileName:file.name,fileSize:fileSize,validateMd5:'0%',progress:file.id,state:'就绪'};
            rowData.push(row);
            that.reloadData(rowData);
            element.render('progress');
        });

        //监听进度条,更新进度条信息
        uplaod.on('uploadProgress', function(file, percentage) {
            element.progress(file.id, (percentage * 100).toFixed(0)+'%');
        });


        //错误信息监听
        uplaod.on('error', function(handler){
            if(handler==='F_EXCEED_SIZE'){
                layer.msg('上传文件过大！<br>操作无法进行,如有需求请联系管理员', {icon: 5});
            }else if(handler==='Q_TYPE_DENIED'){
                layer.msg('不允许上传此类文件！<br>操作无法进行,如有需求请联系管理员', {icon: 5});
            }
        });


        //监听按钮事件
        table.on('tool(extend-uploader-form)', function(obj){
            let data = obj.data;
            if(obj.event === 'del'){
                that.removeArray(rowData, data.fileId);
                uplaod.removeFile(data.fileId, true);
                obj.del();
            }else if(obj.event === 'reload'){
                uplaod.retry(data.fileId);
            }
        });

        function getRichTextHTML(index){
            let richTextHTML = $('#LAY_layedit_' + index);
            return "<html>" + richTextHTML.contents().find("html").html() + "</html>";
        }


        //  点击提交按钮
        form.on('submit(' + that.options.formSubmitBtn +')', function (data) {
            enclosure = "";
            $(data.form).find('textarea[name="description"]').text(getRichTextHTML(that.options.layeditID));
            formParams = $(data.form).serialize();
            that.uploadToServer();

        });

        //  单个文件上传成功
        uplaod.on('uploadSuccess', function(file, response) {
            enclosure += response.path;
            that.setTableBtn(file.id,'完成');
        });

        //  只要有文件上传失败，就会触发
        uplaod.on('uploadError', function (file) {
            $('.file-retry').removeClass('layui-hide');
        });

        //  所有文件上传成功后
        uplaod.on('uploadFinished',function(){
            //  成功后
            $.post(that.options.formUrl,
                formParams + "&enclosure=" + enclosure
                ,function (callback) {
                    location.reload();
                    layer.alert(callback.msg);
                });
        });
    };

    Class.prototype.reloadData=function(data){
        layui.table.reload('extend-uploader-form', {
            data : data
        });
    };

    Class.prototype.register=function () {
        let that = this,
            options = that.options;

        if(that.strIsNull(options.md5)) {
            return;
        }
        // 在文件开始发送前做些异步操作。做md5验证
        // WebUploader会等待此异步操作完成后，开始发送文件。
        webUploader.Uploader.register({
            "before-send-file":"beforeSendFile"
        },{
            beforeSendFile: function(file){
                let task = new $.Deferred();
                (new webUploader.Uploader()).md5File(file, 0, 10*1024*1024).progress(function(percentage){
                    let v = that.getTableHead('validateMd5');
                    let table = $("#extend-uploader-form").next().find('div[class="layui-table-body layui-table-main"]').find('table');
                    let pro = table.find('td[data-field="progress"]');
                    for(let i=0;i<pro.length;i++){
                        let d = $(pro[i]).attr('data-content');
                        if(d===file.id ){
                            let t = $(pro[i]).prev();
                            t.empty();
                            t.append('<div class="'+v+'">'+(percentage * 100).toFixed(0)+'%</div>');
                        }
                    }
                }).then(function(val){
                    console.log("本地MD5：" + val);
                    $.ajax({
                        type: "POST"
                        , url: options.md5
                        //  请求参数
                        , data: {
                            type: "md5Check",
                            md5: val
                        }
                        , cache: false
                        , timeout: 3000
                        , dataType: "json"
                    }).then(function(data, textStatus, jqXHR){
                        // 请求成功
                        //TODO
                        console.log("文件秒传");
                        console.log(data);
                        //  返回值为0则表明文件已存在
                        if(data.code===0){
                            task.reject();
                            uplaod.skipFile(file);
                            let filePath = data.path + ":" + file.name + "|";
                            console.log(filePath);
                            enclosure = enclosure + filePath;
                            that.setTableBtn(file.id,'秒传');
                            element.progress(file.id,'100%');
                        }else{
                            task.resolve();
                        }
                    }, function(jqXHR, textStatus, errorThrown){
                        // 请求失败
                        //  任何形式的验证失败，都触发重新上传
                        task.resolve();
                    });
                });
                return $.when(task);
            }
        });
    };


    /***
     * 注意更改了table列的位置,或自行新增了表格,请自行在这修改
     */
    Class.prototype.getTableHead=function (field) {
        //获取table头的单元格class,保证动态设置table内容后单元格不变形
        let div = $("#extend-uploader-form").next().find('div[class="layui-table-header"]');
        let div2 = div[0];
        let table = $(div2).find('table');
        return table.find('th[data-field="' + field + '"]').find('div').attr('class');
    };

    Class.prototype.setTableBtn=function (fileId,val) {
        let td = this.getTableHead('oper');
        //获取操作栏,修改其状态
        let table = $("#extend-uploader-form").next().find('div[class="layui-table-body layui-table-main"]').find('table');
        let pro = table.find('td[data-field="progress"]');
        for(let i=0;i<pro.length;i++){
            let d = $(pro[i]).attr('data-content');
            if(d===fileId ){
                let t = $(pro[i]).next();
                t.empty();
                t.append('<div class="'+td+'"><a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="ok">'+val+'</a></div>')
            }
        }
    };


    Class.prototype.uploadToServer=function () {
        if(rowData.length<=0){
            layer.msg('没有上传的文件', {icon: 5});
            return;
        }
        $("#extent-button-uploader").text("正在上传");
        $("#extent-button-uploader").addClass('layui-btn-disabled');
        uplaod.upload();
    };

    Class.prototype.removeArray=function (array,fileId) {
        for(let i=0;i<array.length;i++){
            if(array[i].fileId===fileId){
                array.splice(i,1);
            }
        }
        return array;
    };

    let layUploader = {
        render:function (options) {
            let inst = new Class(options);
            return inst;
        }

    };

    exports('layUploader', layUploader);
});