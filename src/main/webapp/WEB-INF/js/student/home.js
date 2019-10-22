layui.use(['table', 'element', 'layer', "jquery", "form"], function () {
    let table = layui.table,
        element = layui.element,
        layer = layui.layer,
        $ = layui.jquery;

    let Collapse = function (tableId, layFilter, cozName, stuNum) {
        this.tableId = tableId;
        this.layFilter = layFilter;
        this.cozName = cozName;
        this.stuNum = stuNum;
    };

    /** @deprecated 请优先使用JSTL表达式创建Collapse，<h2></h2>元素中应当添加一些data属性才能被loadDataTable()使用*/
    Collapse.prototype.createCollapse = function (collapsesTitle) {
        let co = document.getElementById("collapse");
        let item = document.createElement("div");
        item.setAttribute("class", "layui-colla-item");
        item.innerHTML = `<h2 class="layui-colla-title">${collapsesTitle}</h2><div class="layui-colla-content">
<table class="layui-hide" id="${this.tableId}" lay-filter="${this.layFilter}"></table></div>`;
        co.appendChild(item);
    };

    Collapse.prototype.loadDataTable = function () {
        let script = document.createElement("script");
        script.innerHTML = `layui.use(['table'], function(){
    let table = layui.table;
    table.render({
        elem: '#${this.tableId}' // 修改
        ,url:'./json_test'
        ,even: true
        ,where: {
            "stuNum": "${this.stuNum}"
            ,"cozName": "${this.cozName}"
            
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
           
            ,{field:'cozName', title:'课程名',align:"center", width:200, sort: false, templet: function(d){
                    return '<div style="text-align: center;">' + d.cozName + '</div>'
                }}
            ,{field:'tcName', title:'任课老师',align:"center", width:102, sort: true}
            ,{field:'taskName', title:'实验',align:"center", width:150, sort: true}
            ,{field:'taskDesc', title:'实验描述',align:"center", width:200, sort: false, templet: function(d){
                    return '<div style="text-align: center;">' + d.taskDesc + '</div>'
                }}
            ,{field:'deadline', title:'截止时间',align:"center", width:110, sort: true, templet:"<div>{{layui.util.toDateString(d.deadline, 'yyyy-MM-dd')}}</div>" 
                }
            ,{field:'pubTime', title:'提交时间',align:"center", width:110, sort: true, templet:"<div>{{layui.util.toDateString(d.pubTime, 'yyyy-MM-dd')}}</div>" 
                }
            ,{field:'subState', title:'状态', width:85, sort: true}
            ,{field:'score', title:'分数', width:75,align:"center", sort: true}
            
              
            
            
            
            
            
            //,{field:'taskCount', title:'未截止/已截止', width:150, sort: true, templet: function(d){
             //        return '<div style="text-align: center;">' + d.taskCount.notClosed + " / " + d.taskCount.closed + '</div>'
             //   }}
           
            ,{fixed: 'right', title:'<div style="text-align: center;">操作</div>', toolbar: '#bar', width:160}
        ]]
    });
    //头工具栏事件
    table.on('toolbar(${this.layFilter})', function(obj){ // 修改
        let checkStatus = table.checkStatus(obj.config.id);
        let data;
        switch(obj.event){
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
    //监听行工具事件
    table.on('tool(${this.layFilter})', function(obj){
        let data = obj.data;
        //console.log(obj)
        if(obj.event === 'deleterow'){
            layer.confirm('真的删除行么', function(index){
                obj.del();
                layer.close(index);
            });
        } else if(obj.event === 'deploy'){
            //这行是监听到的表格行数据信息，复制给json全局变量。  
            json = JSON.stringify(data);
            layer.open({
                type: 2,
                area: ['80%', '90%'],
                title: false,
                shadeClose: true,
                content: './edits',
            });
        }
    });
});`;
        document.body.appendChild(script);
    };

    //监听折叠
    element.on('collapse(collapse-filter)', function (data) {
        if (!$(this).data("loaded")) {
            let tableId = $(this).data("tableid");
            let tableFilter = $(this).data("tablefilter");
            let cozName = $(this).data("cozname");
            let stuNum = $(this).data("stunum");
            let co = new Collapse(tableId, tableFilter, cozName, stuNum);
            co.loadDataTable();
            $(this).data("loaded", "true");
        }
    });

});