var table = layui.table;
var layer = layui.layer;
var $ = layui.$;
var form = layui.form;

function telcheck() {
    var telephone = document.getElementById("telephone").value;
    var check = /^1[3456789]\d{9}$/; //大写开头 数字字母符号混合 7-13位
    if (!check.test(telephone)) {
        $("#telephone").siblings("label").remove();
        $("#telephone").after("<label style='color: #ff0000'>请输入正确的手机号码</label>");
        return false;
    } else {
        $("#telephone").siblings("label").remove();
        return true;
    }
}

function emercheck() {
    var emergencycall = document.getElementById("emergencycall").value;
    var check = /^1[3456789]\d{9}$/; //大写开头 数字字母符号混合 7-13位
    if (!check.test(emergencycall)) {
        $("#emergencycall").siblings("label").remove();
        $("#emergencycall").after("<label style='color: #ff0000'>请输入正确的应急电话</label>");
        return false;
    } else {
        $("#emergencycall").siblings("label").remove();
        return true;
    }
}

function EnsureUserAccountExist() {
    var result = false;
    var useraccount = $("#useraccount").val();
    if (useraccount === '' || useraccount == null || useraccount == undefined) {
        $("#useraccount").siblings("label").remove();
        $("#useraccount").after("<label style='color: #ff0000'>用户名不能为空</label>");
        result = false;
    } else {
        $.ajax({
            type: "post",
            url: "login/judgeaccount",
            async: false,
            data: {registeruseraccount: $("#useraccount").val()},
            dataType: "text",
            success: function (data) {
                if (data == "用户名已存在") {
                    $("#useraccount").siblings("label").remove();
                    $("#useraccount").after("<label style='color: #ff0000'>用户名已存在，请重新输入</label>");
                    result = false;
                } else if (data == "用户名不存在") {
                    $("#useraccount").siblings("label").remove();
                    result = true;
                }
            }
        });
    }
    return result;
}

$("#telephone").blur(function () {
    telcheck();
});
$("#emergencycall").blur(function () {
    emercheck();
});
$("#useraccount").blur(function () {
    EnsureUserAccountExist();
});

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
            area: ['750px', '600px'],
            title: '添加用户',
            content: $("#adduserform"),
            btn: ['保存', '取消'],
            btn1: function () {
                //第一个按钮的代码
                //保存数据
                //获取表单数据
                if (telcheck() && emercheck() && EnsureUserAccountExist()) {
                    var formdata = form.val("adduserform");
                    $.ajax({
                        url: 'user/adduserinfo',
                        data: formdata,
                        success: function (result) {
                            //关闭弹出层
                            if (result == "添加成功") {
                                //提示添加成功
                                layer.msg("添加成功", {icon: 1, time: 3000});
                                layer.closeAll();
                                //刷新表格
                                table.reload("userlist");
                            } else if (result == "添加失败") {
                                layer.msg("添加失败", {icon: 2, time: 3000});
                                layer.closeAll();
                                table.reload("userlist");
                            }
                        }
                    });
                } else {
                    layer.msg("请正确填写表单信息", {icon: 2, time: 3000});
                }

            }
        });
    } else if (obj.event == 'ban') {
        var value = '0';
        table.reload('userlist', {
            where: {ustate: value}
        });
    }
});
form.on('select(selectustate)', function () {
    var value = $("#selectustate").val();
    console.log(value);
    table.reload('userlist', {
        where: {ustate: value}
    });
});