layui.use(['table', 'element', 'layer'], function(){
    let table = layui.table,
        element = layui.element,
        layer = layui.layer;

    let Collapse = function(){

    };

    Collapse.prototype.createCollapse = function(){

    };

    Collapse.prototype.createDataTable = function(){
        let script = document.createElement("script");
        script.innerText="layui.use(['table','element','layer'],function(){let table=layui.table,element=layui.element,layer=layui.layer;table.render({" +
            "elem:'#data_table1'" +   // 修改
            ",url:'./json_test',even:true,where:{\"page\":\"1\",\"limit\":\"30\"}," +
            "request:{pageName:\"page\",limitName:\"limit\"},toolbar:'#toolbar',defaultToolbar:['filter','exports','print'," +
            "{title:'提示',layEvent:'LAYTABLE_TIPS',icon:'layui-icon-tips'}],title:'用户数据表',cols:[[{type:'checkbox'," +
            "fixed:'left'},{field:'age',title:'ID',width:80,fixed:'left',unresize:true,sort:true},{field:'username',title:'用户名'," +
            "width:120,edit:'text'},{fixed:'right',title:'操作',toolbar:'#bar',width:150}]]});" +
            "table.on('toolbar(data_table1)'," + // 修改
            "function(obj){let checkStatus=table.checkStatus(obj.config.id);let data;switch(obj.event){case 'getCheckData':data=checkStatus.data;" +
            "layer.alert(JSON.stringify(data));break;case'getCheckLength':data=checkStatus.data;layer.msg('选中了：'+data.length+'个');break;" +
            "case 'isAll':layer.msg(checkStatus.isAll?'全选':'未全选');break;case 'LAYTABLE_TIPS':layer.alert('这是工具栏右侧自定义的一个图标按钮');break;}});" +
            "table.on('tool(data_table1)'" +   //  修改
            ",function(obj){let data=obj.data;if(obj.event==='del'){layer.confirm('真的删除行么',function(index){obj.del();" +
            "layer.close(index);});}else if(obj.event==='edit'){layer.prompt({formType:2,value:data.email},function(value,index){obj.update({email:value});" +
            "layer.close(index);});}});" +
            "});";
        document.body.appendChild(script);
    };



    //监听折叠
    element.on('collapse(collapse-filter)', function(data){
        let colla = new Collapse();
        colla.createDataTable();
        console.log(data)
    });
});