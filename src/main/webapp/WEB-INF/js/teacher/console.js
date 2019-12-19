layui.use(['table', 'element', 'layer', "jquery"], function () {
    let table = layui.table,
        element = layui.element,
        layer = layui.layer,
        $ = layui.jquery;

    let Collapse = function (tableId, layFilter, cozName) {
        this.tableId = tableId;
        this.layFilter = layFilter;
        this.cozName = cozName;
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
        script.innerHTML = `layui.use(['table', 'jquery'], function(){
    let table = layui.table,
        $ = layui.jquery;
    table.render({
        elem: '#${this.tableId}' // 修改
        ,url:'./task_table_json'
        ,even: true
        ,where: {
            "cozName": "${this.cozName}"
        }
        ,toolbar: '#toolBar' //开启头部工具栏，并为其绑定左侧模板
        ,defaultToolbar: ['filter', 'exports', 'print']
        ,title: '用户数据表'
        ,cols: [[
            {type: 'checkbox', fixed: 'left'}
            ,{field:'cozNum', title:'课程代码', width:120, sort: true}
            ,{field:'cozName', title:'课程名', align:"center", sort: false, templet: function(d){
                    return '<div style="text-align: center;">' + d.cozName + '</div>'
                }}
            ,{field:'classNum', title:'班级代码', align:"center", width:150, sort: true, templet: function(d){
                    return '<div style="text-align: center;">' + d.classNum + '</div>'
                }}
            ,{field:'className', title:'班级名', align:"center", sort: true, templet: function(d){
                    return '<div style="text-align: center;">' + d.className + '</div>'
                }}
            ,{field:'taskCount', title:'未截止/已截止', width:150, sort: true, templet: function(d){
                     return '<div style="text-align: center;">' + d.taskCount.notClosed + " / " + d.taskCount.closed + '</div>'
                }}
            ,{fixed: 'right', title:'操作', align:"center", toolbar: '#bar', width:160}
            ]]
        });
        
        // 头工具栏事件
        table.on('toolbar(${this.layFilter})', function(obj){ // 修改
            let checkStatus = table.checkStatus(obj.config.id);
            let data;
            switch(obj.event){
                case 'deployTasks':
                    data = checkStatus.data;
                    deployTasks(data);
                    //layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    data = checkStatus.data;
                    layer.msg('选中了：'+ data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选': '未全选');
                    break;
            }
        });
        //监听行工具事件
        table.on('tool(${this.layFilter})', function(obj){
            let data = obj.data;
            //console.log(obj)
            if(obj.event === 'detail'){
                parent.layui.$('.alreadyTask', parent.document).trigger('click');
            } else if(obj.event === 'deploy'){
                deployTasks(data);
            }
        });
    });`;

        document.body.appendChild(script);
    };

    // 触发发布作业事件
    window.deployTasks = function (data) {
        let deployTaskBtn = $('.deployTask', parent.document);
        let param = '?';
        if (typeof data.forEach === 'function') {
            data.forEach((item, index, data) => {
                param = param + 'cozNum=' + item.cozNum + '&';
                param = param + 'classNum=' + item.classNum + '&';
            });
        } else {
            param = param + 'cozNum=' + data.cozNum + '&';
            param = param + 'classNum=' + data.classNum;
        }
        let url = deployTaskBtn.attr('href');
        deployTaskBtn.attr('href', url + param);
        parent.layui.$('.deployTask', parent.document).trigger('click');
        deployTaskBtn.attr('href', url);
    };

    //监听折叠
    element.on('collapse(collapse-filter)', function (data) {
        if (!$(this).data("loaded")) {
            let tableId = $(this).data("tableid");
            let tableFilter = $(this).data("tablefilter");
            let cozName = $(this).data("cozname");
            let co = new Collapse(tableId, tableFilter, cozName);
            co.loadDataTable();
            $(this).data("loaded", "true");
        }
    });
});