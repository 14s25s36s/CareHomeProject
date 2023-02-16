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
                url: 'care/deletecareinfo',
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
    } else if (obj.event == 'update') {
        // location.href = "user/toupdatecareinfo?uid=" + data.uid;
        form.val("updatecareform", data);
        //弹出层
        layer.open({
            type: 1,
            area: ['500px', '350px'],
            title: '添加用户',
            content: $("#updatecareform"),
            btn: ['保存', '取消'],
            btn1: function () {
                //第一个按钮的代码
                //保存数据
                //获取表单数据
                var formdata = form.val("updatecareform");
                $.ajax({
                    url: 'care/updatecareinfo',
                    data: formdata,
                    success: function () {
                        //关闭弹出层
                        layer.closeAll();
                        //提示编辑成功
                        layer.msg("编辑成功", {icon: 1, time: 3000});
                        //刷新表格
                        table.reload("carelist");
                    }
                });

            }
        });
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
    } else if (obj.event == 'add') {
        // form.val("addcareform", data);
        //弹出层
        layer.open({
            type: 1,
            area: ['500px', '350px'],
            title: '添加用户',
            content: $("#addcareform"),
            btn: ['保存', '取消'],
            btn1: function () {
                //第一个按钮的代码
                //保存数据
                //获取表单数据
                var formdata = form.val("addcareform");
                $.ajax({
                    url: 'care/addcareinfo',
                    data: formdata,
                    success: function () {
                        //关闭弹出层
                        layer.closeAll();
                        //提示编辑成功
                        layer.msg("编辑成功", {icon: 1, time: 3000});
                        //刷新表格
                        table.reload("carelist");
                    }
                });

            }
        });
    }
});