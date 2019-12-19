layui.define(function(exports){ //提示：模块也可以依赖其它模块，如：layui.define('layer', callback);
    let obj = {
        // 将数据库中的“:|”格式的路径传入，返回List<Map<>>数据，Map包含文件在服务器的路径、原文件名、文件编号。
        resolvePath : function (filePathString) {
            if (filePathString === undefined || filePathString === '' || filePathString === null) {
                return [];
            }
            let tableData = [];
            let files = filePathString.split("|");
            files.forEach((file, index) => {
                let fileSplit = file.split(":");
                let f = {};
                f["filePath"] = fileSplit[0];
                f["fileName"] = fileSplit[1];
                // 编号
                f["no"] = index + 1;
                tableData.push(f);
            });
            return tableData.slice(0, -1);
        }
    };
    //输出接口
    exports('FilePath', obj);
});
