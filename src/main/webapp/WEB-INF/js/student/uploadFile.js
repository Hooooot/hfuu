layui.use('upload', function() {
    var $ = layui.jquery,
        upload = layui.upload;


    //拖拽上传 多文件列表示例
    var fileListView = $('#fileList'),
        uploadListIns = upload.render({
            elem: '#uploadList',//绑定元素，点击id为 的时候弹出选择文件窗口
            url: 'uploadFile',//上传接口
            accept: 'file', //
            multiple: true,//开启多文件上传
            auto: false,//选择文件后不自动上传
            bindAction: '#uploadListAction',//指向一个按钮触发上传
            choose: function(obj) {
                var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                //读取本地文件
                obj.preview(function(index, file, result) {
                    var size=file.size;
                    if(size>(20*1024*1024)){
                        layer.msg('文件大小不得超过20M');
                        return;
                    }
                    var tr = $(['<tr id="upload-' + index + '">', '<td>' + file.name + '</td>', '<td>' + (file.size / 1014).toFixed(1) + 'kb</td>', '<td>等待上传</td>', '<td>', '<button class="layui-btn layui-btn-mini file-reload layui-hide">重传</button>', '<button class="layui-btn layui-btn-mini layui-btn-danger file-delete">删除</button>', '</td>', '</tr>'].join(''));

                    //单个重传
                    tr.find('.file-reload').on('click', function() {

                        obj.upload(index, file);
                    });

                    //删除
                    tr.find('.file-delete').on('click', function() {
                        delete files[index]; //删除对应的文件
                        tr.remove();
                        uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                    });

                    fileListView.append(tr);
                });
            },
            done: function(res, index, upload) {
                if (res.code == 0) { //上传成功
                    var tr = fileListView.find('tr#upload-' + index),
                        tds = tr.children();
                    tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                    tds.eq(3).html(''); //清空操作
                    return delete this.files[index]; //删除文件队列已经上传成功的文件
                }
                this.error(index, upload);
            },
            error: function(index, upload) {
                var tr = fileListView.find('tr#upload-' + index),
                    tds = tr.children();
                tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
                tds.eq(3).find('.file-reload').removeClass('layui-hide'); //显示重传
            }
        });
});