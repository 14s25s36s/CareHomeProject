var table = layui.table;
var layer = layui.layer;
var $ = layui.$;
var form = layui.form;
//监听行工具事件
table.on('tool(statelist)', function (obj) {
    //当前行的数据
    var data = obj.data;
    // console.log(data);
    if (obj.event == 'delete') {
        layer.confirm('真的删除行么', function (index) {
            $.ajax({
                url: 'state/deletestate',
                data: data,
                success: function (result) {
                    // console.log(result);
                    //关闭弹出框
                    layer.closeAll();
                    if (result == "删除成功") {
                        layer.msg("删除成功", {icon: 1});
                    } else if (result == "删除失败") {
                        layer.msg("删除失败", {icon: 2});
                    } else {
                        layer.msg("该分类下有住户，不可删除", {icon: 2});
                    }
                    //让表格重新加载数据
                    table.reload("statelist");
                }
            });
        });
    } else if (obj.event == 'update') {
        form.val("updatestate", data);
        layer.open({
            type: 1,
            title: "修改护理记录",
            area: ["400px", "300px"],
            btn: ["修改", "取消"],
            content: $("#updatestate"),
            btn1: function () {
                var formdata = form.val("updatestate");
                $.ajax({
                    url: 'state/updatestate',
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
                        table.reload("statelist");
                    }
                });
            }
        });
    }
});

//头工具栏事件
table.on('toolbar(statelist)', function (obj) {
    var checkStatus = table.checkStatus(obj.config.id);
    if (obj.event == 'check') {
        //执行查询
        var checktext = $("#checktext").val();
        table.reload('statelist', {
            where: {checktext: checktext}
        });
    } else if (obj.event == 'add') {
        // form.val("adduserform", data);
        //弹出层
        layer.open({
            type: 1,
            area: ['500px', '550px'],
            title: '添加状态',
            content: $("#addstateform"),
            btn: ['保存', '取消'],
            btn1: function () {
                //第一个按钮的代码
                //保存数据
                //获取表单数据
                var formdata = form.val("addstateform");
                $.ajax({
                    url: 'state/addstate',
                    data: formdata,
                    success: function (result) {
                        if (result == "添加成功") {
                            //关闭弹出层
                            layer.closeAll();
                            //提示编辑成功
                            layer.msg("添加成功", {icon: 1, time: 3000});
                            //刷新表格
                            table.reload("statelist");
                        } else {
                            layer.msg("添加失败", {icon: 2, time: 3000});
                        }

                    }
                });

            }
        });
    }
});