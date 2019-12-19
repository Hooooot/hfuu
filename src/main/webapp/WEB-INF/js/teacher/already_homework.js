layui.extend({
    FilePath:'../js/common/resolve_file_path'
}).use(['table', 'element', 'layer', "jquery", "FilePath"], function () {
    let table = layui.table,
        element = layui.element,
        layer = layui.layer,
        $ = layui.jquery,
        FilePath = layui.FilePath;
    window.preSubmit = [];
    window.nextSubmit = [];


    let Collapse = function (tableId, layFilter, taskId, clazzNum, taskDeadline) {
        this.tableId = tableId;
        this.layFilter = layFilter;
        this.taskId = taskId;
        this.clazzNum = clazzNum;
        this.taskDeadline = taskDeadline;
    };

    Collapse.prototype.loadDataTable = function () {
        let script = document.createElement("script");
        script.innerHTML = `layui.use(['table', 'jquery', 'layer'], function(){
            let table = layui.table,
                $ = layui.jquery,
                layer = layui.layer;
        
            table.render({
                elem: '#${this.tableId}'
                ,url:'./student_table_json'
                ,even: true
                ,where: {
                    "taskId": "${this.taskId}",
                    "clazzNum": "${this.clazzNum}"
                }
                ,toolbar: '#toolBar' //开启头部工具栏，并为其绑定左侧模板
                ,defaultToolbar: ['filter', 'exports', 'print']
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
                            if(d.submit !== undefined){
                                if(d.submit.subState === '待批阅'){
                                    return '<div style="text-align: center;color: blue;">待批阅</div>';
                                }else if(d.submit.subState === '待提交') {
                                    return '<div style="text-align: center;color: red;">未提交</div>';
                                } else {
                                    return '<div style="text-align: center;color: green;">'+ d.submit.subState + '</div>';                                
                                }
                            }else{
                                return '<div style="text-align: center;color: red;">未提交</div>';
                            }
                        }}
                    ,{field:'submit.score', title:'分数', align:"center", width:150, sort: true, templet: function(d){
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
                        case 'getTaskDetail':
                            layer.open({
                                        type: 2,
                                        title: '任务详情',
                                        content: './task_detail?taskId=${this.taskId}',
                                        area: ['80%', '80%'],
                                        maxmin: true,
                                        });
                            break;
                        case 'isAll':
                            layer.msg(checkStatus.isAll ? '全选': '未全选');
                            break;
                    }
                });
                // 监听行工具事件
                table.on('tool(${this.layFilter})', function(obj){
                    let rowData = obj.data;
                    // 批改作业
                    if(obj.event === 'correct'){
                    let selectedRow = [];
                        window.tableID = ${this.tableId}
                        window.nextSubmit = table.cache.${this.tableId}.concat();
                        for(let i = 0; i < window.nextSubmit.length; i++){
                            if(window.nextSubmit[i].stuNum === rowData.stuNum){
                                selectedRow = window.nextSubmit.splice(i,1);
                                i--;
                                continue;
                            }
                            if(!window.nextSubmit[i].submit || window.nextSubmit[i].submit.subState !== '待批阅'){
                                window.nextSubmit.splice(i,1);
                                i--;
                            }
                        }
                        correctSubmit(selectedRow.pop());
                    }
                });
            });`;
        document.body.appendChild(script);
    };

    //  行 点击批改作业事件
    window.correctSubmit = function (rowData, fullScreen, width, height) {
        function submitScore(subId, score) {
            if (score === '') {
                return;
            }
            $.post("./correct",
                {
                    subId: subId,
                    score: score
                },
                function (callback) {
                    if (callback.code === 1) {
                        table.reload(window.tableID.id);
                    }
                    parent.layui.layer.msg(callback.msg);
                })
        }

        let fullScreenHeight = '85%';
        let unfullScreenHeight = '77%';
        let layIndex = parent.layui.layer.open({
            type: 1
            , title: ' '
            , closeBtn: 1
            , area: width && height ? fullScreen ? ['60%', '80%'] : [width, height] : ['60%', '80%']
            , shade: 0.8
            //,id: 'LAY_layuipro' //设定一个id，防止重复弹出
            , btn: ['上一份', '查看附件', '下一份']
            , yes: function (index, layero) {
                let scoreChange = true;
                let score = layero.find('.submitScore').val();
                if ((score <= 100 && score >= 0) || score === '') {
                    if(rowData.submit.score.toString() === score){
                        scoreChange = false;
                    }else{
                        rowData.submit.score = score;
                    }
                } else {
                    parent.layui.layer.alert("分数只能为0-100的整数！");
                    return false;
                }
                let pre = preSubmit.pop();
                if (pre !== undefined) {
                    nextSubmit.unshift(rowData);
                    let lay = parent.layui.$("#layui-layer" + index);
                    correctSubmit(pre, fullScreen, lay.width(), lay.height());
                }
                parent.layui.layer.close(index);
                if(scoreChange){
                    submitScore(rowData.submit.subId, score);
                }
            }
            , btn2: function (index, layero) {
                let tableData = [];
                tableData = FilePath.resolvePath(rowData.submit.subFile);
                parent.layui.layer.open({
                    type: 1
                    , closeBtn: 1
                    , area: ['600px', '350px']
                    , shade: 0.8
                    , title: "附件"
                    , content: `<div>
                                    <table id="enclosure_table" class="layui-hide"></table>
                                </div>
                                
                                <script>
                                console.log(${JSON.stringify(tableData)});
                                layui.use(['table'], function () {
                                    let table = layui.table;
                                    table.render({
                                        elem: '#enclosure_table'
                                        //, width: '100%'
                                        , even: true
                                        , title: '作业附件表'
                                        , data: ${JSON.stringify(tableData)}
                                        , cols: [[
                                          {field:'no', width:80, title: '编号', fixed: 'left'}
                                          ,{field:'fileName', title: '文件名'}
                                          ,{fixed: 'right', width:80, title: '操作', templet: function(d) {
                                              return '<a href="../uploaded/'+ d.filePath +'" download="'+ d.fileName +
                                                '" class="layui-btn layui-btn-xs" style="margin-top:3px;">下载</a>';
                                          }}
                                        ]]
                                    });
                                })
                                </script>`
                    , btn: ["全部下载"]
                    , btnAlign: 'c'
                    , moveType: 1 //拖拽模式，0或者1
                    , yes: function (index, layero) {
                        let a = document.createElement('a');
                        tableData.forEach((file)=>{
                            a.href = '../uploaded/'+file.filePath;
                            a.download = file.fileName;
                            a.click();
                        });
                        a.remove(); // 移除掉 <a>
                    }
                });
                return false;
            }
            , btn3: function (index, layero) {
                let scoreChange = true;
                let score = layero.find('.submitScore').val();
                if ((score < 100 && score > 0) || score === '') {
                    if(rowData.submit.score.toString() === score){
                        scoreChange = false;
                    }else{
                        rowData.submit.score = score;
                    }
                } else {
                    parent.layui.layer.alert("分数只能为0-100的整数！");
                    return false;
                }
                if(scoreChange){
                    submitScore(rowData.submit.subId, score);
                }
                let ne = nextSubmit.shift();
                if (ne === undefined) {
                    return true;
                }
                rowData.submit.score = score;
                preSubmit.push(rowData);
                let lay = parent.layui.$("#layui-layer" + index);
                correctSubmit(ne, fullScreen, lay.width(), lay.height());
            }
            , btnAlign: 'c'
            , moveType: 1 //拖拽模式，0或者1
            , moveOut: false
            , maxmin: true
            , content: `<div style="height: 100%;width: 100%;">
                           <div class="layui-fluid layui-elem-quote">
                               <div class="layui-row layui-col-space5" style="font-size: 18px;">
                                    <div class="layui-col-md4">
                                        <div style="margin-left: 15%;">姓名：${rowData.stuName}</div>
                                    </div>
                                    <div class="layui-col-md4">
                                        <div>学号：${rowData.stuNum}</div>
                                    </div>
                                    <div class="layui-col-md4">
                                        <div>班级：${rowData.className}</div>
                                    </div>
                                </div>
                           </div>
                           <iframe class="submitContent" src="../uploaded/${rowData.submit.subRichTextPath}" height=${unfullScreenHeight} width="99%"
                           >您的浏览器无法正常显示，请更换浏览器！</iframe>
                           <div class="layui-form layui-form-pane" style="margin:0 auto;width: 309px;">
                                 <div class="layui-form-item">
                                    <label class="layui-form-label">分数：</label>
                                    <div class="layui-input-inline">
                                        <input type="number" placeholder="请输入分数：0-100" value="${rowData.submit.score ? rowData.submit.score : ''}"
                                        autocomplete="off" class="layui-input submitScore" oninput="value=value.replace(/[^\\d^\\.]/g,'').slice(0,3)"
                                        maxlength="3">
                                    </div>
                                 </div>
                           </div>
                       </div>`
            , success: function (layero) {
                let btn = layero.find('.layui-layer-btn');
                let btn0 = btn.find('.layui-layer-btn0');
                let btn1 = btn.find('.layui-layer-btn1');
                let btn2 = btn.find('.layui-layer-btn2');
                btn0.attr({
                    style: 'background-color:RGB(53,169,255);'
                });
                btn2.attr({
                    style: 'background-color:RGB(53,169,255);color:white;'
                });
                if (preSubmit.length === 0) {
                    btn0.text(" 提交 ");
                }
                if (rowData.submit.subFile===undefined || rowData.submit.subFile==='' || rowData.submit.subFile===null) {
                    btn1.text(" 无附件 ");
                    btn1.addClass("layui-btn-disabled");
                }
                if (nextSubmit.length === 0) {
                    btn2.text(" 提交 ");
                }
                console.log(nextSubmit)
            }
            , full: function (layero) {
                let content = layero.find('.submitContent');
                content.attr({
                    height: fullScreenHeight
                });
                fullScreen = true;
            }
            , restore: function (layero) {
                let content = layero.find('.submitContent');
                content.attr({
                    height: unfullScreenHeight
                });
                fullScreen = false;
            }
            , cancel: function () {
                window.preSubmit = [];
                window.nextSubmit = [];
            }
        });

        if (fullScreen) {
            parent.layui.layer.full(layIndex);
            parent.layui.$(".submitContent").attr({
                height: fullScreenHeight
            });
        }
    };

    //监听折叠
    element.on('collapse(collapse-filter)', function (data) {
        if (!$(this).data("loaded")) {
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