var table = layui.table;
var layer = layui.layer;
var $ = layui.$;
var form = layui.form;

function Pwd() {
    var Pwd = document.getElementById("oldpassword").value;
    var pwd = /^[A-Z][A-Za-z0-9]\w{6,12}.{1,20}$/; //大写开头 数字字母符号混合 7-13位
    if (!pwd.test(Pwd)) {
        $("#oldpassword").siblings("label").remove();
        $("#oldpassword").after("<label style='color: #ff0000'>密码格式错误，请重新输入</label>");
        return false;
    } else {
        $("#oldpassword").siblings("label").remove();
        return true;
    }
}

function NewPwd() {
    var Pwd = document.getElementById("newpassword").value;
    var oldPwd = document.getElementById("oldpassword").value;
    var pwd = /^[A-Z][A-Za-z0-9]\w{6,12}.{1,20}$/; //大写开头 数字字母符号混合 7-13位
    if (!pwd.test(Pwd)) {
        $("#newpassword").siblings("label").remove();
        $("#newpassword").after("<label style='color: #ff0000'>密码格式错误，请重新输入</label>");
        return false;
    } else if (oldPwd == Pwd) {
        $("#newpassword").siblings("label").remove();
        $("#newpassword").after("<label style='color: #ff0000'>新旧密码不能相同</label>");
        return false;
    } else {
        $("#newpassword").siblings("label").remove();
        return true;
    }
}

function EnsurePwd() {
    var newPwd = document.getElementById("newpassword").value;
    var ensurePwd = document.getElementById("ensurenewpassword").value;
    if (newPwd != ensurePwd) {
        $("#ensurenewpassword").siblings("label").remove();
        $("#ensurenewpassword").after("<label style='color: #ff0000'>两次密码输入不一样，请重新输入</label>")
        return false;
    } else {
        $("#ensurenewpassword").siblings("label").remove();
        return true;
    }
}

$("#oldpassword").blur(function () {
    Pwd();
});
$("#newpassword").blur(function () {
    NewPwd();
});
$("#ensurenewpassword").blur(function () {
    EnsurePwd();
});

$(function () {
    $("#updatepassword").on("click", function () {
        layer.open({
            type: 1,
            area: ['500px', '350px'],
            title: '注册',
            content: $("#editpassword"),
            btn: ['确定修改', '取消'],
            btn1: function () {
                if (Pwd() && NewPwd() && EnsurePwd()) {
                    $.ajax({
                        url: 'myinfo/updatepassword',
                        data: {
                            oldpassword: $("#oldpassword").val(),
                            password: $("#newpassword").val()
                        },
                        success: function (result) {
                            layer.closeAll();
                            if (result == "修改成功") {
                                layer.msg("修改成功，请重新登陆", {icon: 1, time: 3000});
                                window.parent.frames.location.href = "login/loginexit"
                            } else if (result = "原密码错误") {
                                layer.msg("修改失败，旧密码不正确，请重新修改", {icon: 2, time: 3000})
                            } else if (result == "修改失败") {
                                layer.msg("修改失败，请重新填写", {icon: 2, time: 3000});
                            }
                        }
                    });
                }
            }
        });
    });
});

$("#exit").on("click", function () {
    var result = confirm("确定要退出吗？");
    if (result) {
        window.parent.frames.location.href = "login/loginexit";
    }
});