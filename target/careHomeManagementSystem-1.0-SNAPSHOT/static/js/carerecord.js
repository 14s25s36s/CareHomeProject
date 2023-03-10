var table = layui.table;
var layer = layui.layer;
var $ = layui.$;
var form = layui.form;
//监听行工具事件
table.on('tool(carerecord)', function (obj) {
    //当前行的数据
    var data = obj.data;
    // console.log(data);
    if (obj.event == 'delete') {
        layer.confirm('真的删除行么', function (index) {
            $.ajax({
                url: 'docare/deletecarerecord',
                data: data,
                success: function (result) {
                    // console.log(result);
                    //关闭弹出框
                    layer.closeAll();
                    if (result == "删除成功") {
                        layer.msg("删除成功", {icon: 1});
                    } else {
                        layer.msg("删除失败", {icon: 2});
                    }
                    //让表格重新加载数据
                    table.reload("carerecord");
                }
            });
        });
    } else if (obj.event == 'update') {
        // location.href = "docare/toupdatecarerecord?careid=" + data.careid;
        form.val("updatecarerecord", data);
        layer.open({
            type: 1,
            title: "修改护理记录",
            area: ["400px", "300px"],
            btn: ["修改", "取消"],
            content: $("#updatecarerecord"),
            btn1: function () {
                var formdata = form.val("updatecarerecord");
                $.ajax({
                    url: 'docare/updatecarerecord',
                    data: formdata,
                    success: function (data) {
                        //关闭弹出层
                        layer.closeAll();
                        //提示编辑成功
                        if (data == "修改成功") {
                            layer.msg("修改成功", {icon: 1, time: 3000});
                        } else if (data == "修改失败") {
                            layer.msg("修改失败", {icon: 2, time: 3000});
                        }
                        //刷新表格
                        table.reload("carerecord");
                    }
                });
            }
        });
    }
});

//头工具栏事件
table.on('toolbar(carerecord)', function (obj) {
    var checkStatus = table.checkStatus(obj.config.id);
    if (obj.event == 'check') {
        //执行查询
        var checktext = $("#checktext").val();
        table.reload('carerecord', {
            where: {lname: checktext}
        });
    } else if (obj.event == 'add') {
        //弹出层
        layer.open({
            type: 1,
            area: ['500px', '350px'],
            title: '添加护理记录',
            content: $("#addcarerecord"),
            btn: ['保存', '取消'],
            btn1: function () {
                //第一个按钮的代码
                //保存数据
                //获取表单数据
                var formdata = form.val("addcarerecord");
                $.ajax({
                    url: 'docare/addcarerecord',
                    data: formdata,
                    success: function () {
                        //关闭弹出层
                        layer.closeAll();
                        //提示编辑成功
                        layer.msg("添加成功", {icon: 1, time: 3000});
                        //刷新表格
                        table.reload("carerecord");
                    }
                });

            }
        });
    }
});