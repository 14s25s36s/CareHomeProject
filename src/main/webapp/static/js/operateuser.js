var table = layui.table;
var layer = layui.layer;
var $ = layui.$;
var form = layui.form;
//监听行工具事件
table.on('tool(userlist)', function (obj) {
    //当前行的数据
    var data = obj.data;
    // console.log(data);
    if (obj.event == 'delete') {
        layer.confirm('真的删除行么', function (index) {
            $.ajax({
                url: 'user/deleteuserinfo',
                data: data,
                success: function (result) {
                    // console.log(result);
                    //关闭弹出框
                    layer.closeAll();
                    if (result == "该用户有家属入住，不可删除") {
                        layer.msg("该用户有家属入住，不可删除", {icon: 2});
                    } else {
                        layer.msg("删除成功", {icon: 1});
                    }
                    //让表格重新加载数据
                    table.reload("userlist");
                }
            });
        });
    } else if (obj.event == 'update') {
        location.href = "user/toupdateuserinfo?uid=" + data.uid;
    } else if (obj.event == 'ban') {
        layer.confirm('真的要注销该用户吗', function (index) {
            $.ajax({
                url: "user/updateuserstate",
                data: data,
                success: function (result) {
                    console.log(result);
                    layer.closeAll();
                    if (result == "修改成功") {
                        layer.msg("修改状态成功", {icon: 1});
                    } else if ("修改失败") {
                        layer.msg("修改状态失败", {icon: 2});
                    }
                    table.reload("userlist");
                }
            });
        });
    } else if (obj.event == 'unban') {
        layer.confirm('真的要解封该用户吗', function (index) {
            $.ajax({
                url: "user/updateuserstate",
                data: data,
                success: function (result) {
                    console.log(result);
                    layer.closeAll();
                    if (result == "修改成功") {
                        layer.msg("修改状态成功", {icon: 1});
                    } else if ("修改失败") {
                        layer.msg("修改状态失败", {icon: 2});
                    }
                    table.reload("userlist");
                }
            });
        });
    }
});

//头工具栏事件
table.on('toolbar(userlist)', function (obj) {
    var checkStatus = table.checkStatus(obj.config.id);
    if (obj.event == 'check') {
        //执行查询
        var checktext = $("#checktext").val();
        table.reload('userlist', {
            where: {checktext: checktext}
        });
    } else if (obj.event == 'add') {
        // form.val("adduserform", data);
        //弹出层
        layer.open({
            type: 1,
            area: ['500px', '350px'],
            title: '添加用户',
            content: $("#adduserform"),
            btn: ['保存', '取消'],
            btn1: function () {
                //第一个按钮的代码
                //保存数据
                //获取表单数据
                var formdata = form.val("adduserform");
                $.ajax({
                    url: 'user/adduserinfo',
                    data: formdata,
                    success: function () {
                        //关闭弹出层
                        layer.closeAll();
                        //提示编辑成功
                        layer.msg("编辑成功", {icon: 1, time: 3000});
                        //刷新表格
                        table.reload("userlist");
                    }
                });

            }
        });
    }
});