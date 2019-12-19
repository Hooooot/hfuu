layui.extend({
    FilePath: '../js/common/resolve_file_path'
}).use(['FilePath', 'jquery', 'table', 'element'], function () {
    let table = layui.table,
        FilePath = layui.FilePath,
        $ = layui.jquery;

    let tableData = FilePath.resolvePath($('#enclosure').data("task_enclosure"));
    table.render({
        elem: '#enclosure'
        , even: true
        , title: '附件表'
        , data: tableData
        , text: {
            none: "该任务没有附件！"
        }
        , cols: [[
            {field:'no', width:80, title: '编号', fixed: 'left'}
            ,{field:'fileName', title: '文件名'}
            ,{fixed: 'right', width:80, title: '操作', templet: function(d) {
                    return '<a href="../uploaded/'+ d.filePath +'" download="'+ d.fileName +
                        '" class="layui-btn layui-btn-xs" style="margin-top:3px;">下载</a>';
                }
            }
        ]]
    });

    $(function(){
        document.getElementById("desc").contentWindow.document.body.contentEditable= "false";
    });
});