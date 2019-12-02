layui.use(['table', 'element', 'layer', "jquery"], function(){
    let table = layui.table,
        element = layui.element,
        layer = layui.layer,
        $ = layui.jquery;


    let Collapse = function(tableId, layFilter, taskId, clazzNum, taskDeadline){
        this.tableId = tableId;
        this.layFilter = layFilter;
        this.taskId = taskId;
        this.clazzNum = clazzNum;
        this.taskDeadline = taskDeadline;
    };

    Collapse.prototype.loadDataTable = function(){
        let script = document.createElement("script");
        script.innerHTML = `layui.use(['table', 'jquery', 'layer'], function(){
            let table = layui.table,
                $ = layui.jquery,
                layer = layui.layer,
                preOne = [];
        
            table.render({
                elem: '#${this.tableId}' // 修改
                ,url:'./student_table_json'
                ,even: true
                ,where: {
                    "taskId": "${this.taskId}",
                    "clazzNum": "${this.clazzNum}"
                }
                ,toolbar: '#toolBar' //开启头部工具栏，并为其绑定左侧模板
                ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                    title: '提示'
                    ,layEvent: 'LAYTABLE_TIPS'
                    ,icon: 'layui-icon-tips'
                }]
                ,title: '用户数据表'
                ,cols: [[
                    {type: 'checkbox', fixed: 'left'}
                    ,{field:'stuName', title:'姓名', width:120, sort: true}
                    ,{field:'stuNum', title:'学号', align:"center", sort: false, templet: function(d){
                            return '<div style="text-align: center;">' + d.stuNum + '</div>'
                        }}
                    ,{field:'stuPhone', title:'手机号', align:"center", width:150, sort: true, templet: function(d){
                            return '<div style="text-align: center;">' + d.stuPhone + '</div>'
                        }}
                    ,{field:'className', title:'班级名', align:"center", sort: true, templet: function(d){
                            return '<div style="text-align: center;">' + d.className + '</div>'
                        }}
                    ,{field:'submit', title:'作业状态', align:"center", width:150, sort: true, templet: function(d){
                            console.log(d);
                            if(d.submit !== undefined){
                                if(d.submit.subState === '待批阅'){
                                    return '<div style="text-align: center;color: blue;">'+ d.submit.subState + '</div>';
                                }else{
                                    //return '<div style="text-align: center;color: green;">'+ d.submit.subState + '</div>';
                                }
                            }else{
                                return '<div style="text-align: center;color: red;">未提交</div>';
                            }
                           
                        }}
                    ,{field:'', title:'分数', align:"center", width:150, sort: true, templet: function(d){
                            if(d.submit !== undefined){
                                return '<div style="text-align: center;color: green;">' + d.submit.score?d.submit.score:'未批改' + '</div>'
                            }else{
                                return '<div style="text-align: center;color: red;">作业未提交</div>'
                            }
                           
                        }}
                    ,{fixed: 'right', title:'操作', align:"center", toolbar: '#bar', width:160}
                    ]]
                ,done:function(res){
                    tableData = res.data;
                    console.log("done: ")
                    console.log(tableData);
                    if (res.count == 0) {
                        layer.msg('这个班级没人(ง •_•)ง', {
                            icon: 1,
                            time: 1500
                        });
                    }
                }
            });
                
                // 头工具栏事件
                table.on('toolbar(${this.layFilter})', function(obj){ // 修改
                    let checkStatus = table.checkStatus(obj.config.id);
                    let data;
                    switch(obj.event){
                        case 'correctSubmits':
                            data = checkStatus.data;
                            //layer.alert(JSON.stringify(data));
                            break;
                        case 'getCheckLength':
                            data = checkStatus.data;
                            layer.msg('选中了：'+ data.length + ' 个');
                            break;
                        case 'isAll':
                            layer.msg(checkStatus.isAll ? '全选': '未全选');
                            break;
                        //自定义头工具栏右侧图标 - 提示
                        case 'LAYTABLE_TIPS':
                            layer.alert('这是工具栏右侧自定义的一个图标按钮');
                            break;
                    }
                });
                // 监听行工具事件
                table.on('tool(${this.layFilter})', function(obj){
                    let rowData = obj.data;
                    // 批改作业
                    if(obj.event === 'correct'){
                        correctSubmit(table.cache.${this.tableId}, rowData);
                    }
                });
            });`;
        document.body.appendChild(script);
    };

    //  行 点击批改作业事件
    window.correctSubmit = function(tableData, rowData) {
        console.log("table data:");
        console.log(tableData);
        //示范一个公告层
        parent.layer.open({
            type: 1
            ,title: false //不显示标题栏
            ,closeBtn: true
            ,area: '300px;'
            ,shade: 0.8
            ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
            ,btn: ['上一页', '不改了', '下一页']
            ,btnAlign: 'c'
            ,moveType: 1 //拖拽模式，0或者1
            ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">你知道吗？亲！<br>layer ≠ layui</div>'
            ,success: function (layero) {
                let btn = layero.find('.layui-layer-btn');
                btn.find('.layui-layer-btn0').attr({
                    href: 'http://www.layui.com/'
                    , target: '_blank'
                });
                btn.find('.layui-layer-btn1').addClass("");
            }
        });
    };
    parent.layui.$("body").on('click', '.layui-layer-btn1', correctSubmit);

    //监听折叠
    element.on('collapse(collapse-filter)', function(data){
        let tableId = $(this).data("tableid");
        if (!$(this).data("loaded")){
            let tableId = $(this).data("tableid");
            let tableFilter = $(this).data("tablefilter");
            let taskId = $(this).data("taskid");
            let taskDeadline = $(this).data("taskdeadline");
            let clazzNum = $(this).data("clazznum");
            let co = new Collapse(tableId, tableFilter, taskId, clazzNum, taskDeadline);
            co.loadDataTable();
            $(this).data("loaded", "true");
        }
    });

});