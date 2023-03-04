var table = layui.table;
var layer = layui.layer;
var $ = layui.$;
var form = layui.form;
//监听行工具事件
table.on('tool(carelist)', function (obj) {
    //当前行的数据
    var data = obj.data;
    console.log(data);
    if (obj.event == 'delete') {
        layer.confirm('真的删除行么', function (index) {
            $.ajax({
                url: 'user/deleteuserinfo',
                data: data,
                success: function (result) {
                    // console.log(result);
                    //关闭弹出框
                    layer.closeAll();
                    if (result == null) {
                        layer.msg("删除失败", {icon: 1});
                    } else {
                        layer.msg("删除成功", {icon: 1});
                    }
                    //让表格重新加载数据
                    table.reload("carelist");
                }
            });
        });
    } else if (obj.event == 'select') {
        $.ajax({
            url: "care/getlivebycare",
            data: data,
            success: function (result) {
                result = JSON.parse(result);
                layer.open({
                    type: 1,
                    content: $("#carelive"),
                    area: ['700px', '600px'],
                    btn: ['确定'],
                    btn1: function (index) {
                        layer.close(index);
                        window.location.reload();
                    },
                    success: function () {
                        table.render({
                            elem: "#opencare",
                            height: 150,
                            data: result,
                            page: false,
                            cols: [[
                                {field: 'lname', title: '住户姓名', fixed: 'left'},
                                {field: 'lstate', title: '住户自理能力', fixed: 'left'}
                            ]]
                        });
                    }
                });
            }
        });
    } else {
        alert("失败");
    }
});

//头工具栏事件
table.on('toolbar(carelist)', function (obj) {
    var checkStatus = table.checkStatus(obj.config.id);
    if (obj.event == 'check') {
        //执行查询
        var checktext = $("#checktext").val();
        table.reload('carelist', {
            where: {checktext: checktext}
        });
    }
});