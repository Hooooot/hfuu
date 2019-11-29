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
        script.innerHTML =`layui.use(['table', 'jquery'], function(){
    let table = layui.table,
        $ = layui.jquery,
        tableData;
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
            ,{field:'status', title:'提交状态', align:"center", width:150, sort: true, templet: function(d){
                    if(d.status === 1){
                        return '<div style="text-align: center;color: green;">已提交</div>'
                    }else{
                        return '<div style="text-align: center;color: red;">未提交</div>'
                    }
                   
                }}
            ,{field:'', title:'分数', align:"center", width:150, sort: true, templet: function(d){
                    if(d.status === 1){
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
                //Layui启用initSort后数据表格在查询不到数据时显示无数据,待实现
                layer.msg('这个班级没人(ง •_•)ง', {
                    icon: 1,
                    time: 1500
                });
            }
        }
    });
    
        function correctSubmit(tableData){
            console.log(tableData);
        }
        
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
            let data = obj.data;
            // console.log(obj)
            // 批改作业
            if(obj.event === 'correct'){
                correctSubmit(table.cache.${this.tableId});
            }
        });
    });`;
        document.body.appendChild(script);
    };

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