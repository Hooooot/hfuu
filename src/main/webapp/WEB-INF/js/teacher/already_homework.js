layui.use('jquery', function () {
    let $ = layui.jquery;
    $('.layui-btn').on('click', function () {
        let data = [{"tcName":"张向东","taskCount":{"notClosed":0,"closed":0},
            "cozName":"微型计算机原理与接口技术","className":"16计科一班",
            "term":"181","classNum":"1604011","cozNum":"041310801","cozId":17},
            {"tcName":"张向东","taskCount":{"notClosed":0,"closed":0},
                "cozName":"微型计算机原理与接口技术","className":"16计科二班",
                "term":"181","classNum":"1604012","cozNum":"041310801","cozId":18},
            {"tcName":"张向东","taskCount":{"notClosed":0,"closed":0},
                "cozName":"微型计算机原理与接口技术","className":"17软工二班",
                "term":"181","classNum":"1704092","cozNum":"041310801","cozId":19}];
        $.get('./console', {
            data:data
        }, function (backData) {

        });
    });
});
