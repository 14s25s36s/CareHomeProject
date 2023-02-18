function Pwd() {
    var Pwd = document.getElementById("password").value;
    var pwd = /^[A-Z][A-Za-z0-9]\w{6,12}.{1,20}$/; //大写开头 数字字母符号混合 7-13位
    if (!pwd.test(Pwd)) {
        $("#password").siblings("label").remove();
        $("#password").after("<label style='color: #ff0000'>密码格式错误，请重新输入</label>");
        return false;
    } else {
        $("#password").siblings("label").remove();
        // $("#password").after("<label style='color: #00FF00'>√</label>");
        return true;
    }
}

function checkcode() {
    var result = false;
    var code = '';
    var codetext = $("#codetext").val().toLowerCase();
    code = str.toLowerCase();
    console.log(codetext);
    console.log(code);
    if (codetext === '' || codetext === null || codetext === undefined) {
        $("#codetext").siblings("label").remove();
        $("#codetext").after("<label style='color: #ff0000'>验证码不能为空</label>");
        result = false;
    } else if (codetext != code) {
        $("#codetext").siblings("label").remove();
        $("#codetext").after("<label style='color: #ff0000'>验证码不正确</label>");
        str = '';
        run();
        result = false;
    } else {
        $("#codetext").siblings("label").remove();
        result = true;
    }
    return result;
}

function RegisterPwd() {
    var Pwd = document.getElementById("registerpass").value;
    var pwd = /^[A-Z][A-Za-z0-9]\w{6,12}.{1,20}$/; //大写开头 数字字母符号混合 7-13位
    if (!pwd.test(Pwd)) {
        $("#registerpass").siblings("label").remove();
        $("#registerpass").after("<label style='color: #ff0000'>密码格式错误，请重新输入</label>");
        return false;
    } else {
        $("#registerpass").siblings("label").remove();
        // $("#password").after("<label style='color: #00FF00'>√</label>");
        return true;
    }
}

function EnsurePwd() {
    var registerPwd = document.getElementById("registerpass").value;
    var ensurePwd = document.getElementById("ensureregisterpass").value;
    if (registerPwd != ensurePwd) {
        $("#ensureregisterpass").siblings("label").remove();
        $("#ensureregisterpass").after("<label style='color: #ff0000'>两次密码输入不一样，请重新输入</label>")
        return false;
    } else {
        $("#ensureregisterpass").siblings("label").remove();
        return true;
    }
}

$("#registerpass").blur(function () {
    RegisterPwd();
});
$("#ensureregisterpass").blur(function () {
    EnsurePwd();
});

function EnsureUserAccountExist() {
    var result = false;
    var registeruseraccount = $("#registeruseraccount").val();
    if (registeruseraccount === '' || registeruseraccount == null || registeruseraccount == undefined) {
        $("#registeruseraccount").siblings("label").remove();
        $("#registeruseraccount").after("<label style='color: #ff0000'>用户名不能为空</label>");
        result = false;
    } else {
        $.ajax({
            type: "post",
            url: "login/judgeaccount",
            async: false,
            data: {registeruseraccount: $("#registeruseraccount").val()},
            dataType: "text",
            success: function (data) {
                if (data == "用户名已存在") {
                    $("#registeruseraccount").siblings("label").remove();
                    $("#registeruseraccount").after("<label style='color: #ff0000'>用户名已存在，请重新输入</label>");
                    result = false;
                } else if (data == "用户名不存在") {
                    $("#registeruseraccount").siblings("label").remove();
                    result = true;
                }
            }
        });
    }
    return result;
}

$("#registeruseraccount").blur(function () {
    EnsureUserAccountExist();
});

$(function () {
    $("#login").on("click", function () {
        layer.open({
            type: 1,
            area: ['500px', '350px'],
            title: '请输入验证码',
            content: $("#logincodeform"),
            btn: ['确定', '取消'],
            btn1: function () {
                if (checkcode()) {
                    $.ajax({
                        type: "post",
                        url: "login/dologin",
                        data: {
                            useraccount: $("#useraccount").val(),
                            password: $("#password").val()
                        },
                        dataType: "text",
                        success: function (data) {
                            if (data == "超级管理员登陆成功") {
                                alert("超级管理员登陆成功!欢迎!")
                                window.location.href = "login/adminmain";
                            } else if (data == "员工登陆成功") {
                                alert("员工登陆成功!欢迎!")
                                window.location.href = "login/caremain";
                            } else if (data == "登陆成功") {
                                alert("登陆成功!欢迎!")
                                window.location.href = "login/usermain";
                            } else {
                                alert("登录失败，用户名或密码错误！");
                                $("#password").val("");
                                $("#useraccount").focus().select();
                            }
                        }
                    });
                }

            }
        });


    });
});

$("#codetext").blur(function () {
    checkcode();
});

$("#password").blur(function () {
    Pwd();
});
var table = layui.table;
var layer = layui.layer;
var $ = layui.$;
var form = layui.form;

$(function () {
    $("#register").on("click", function () {
        //弹出层
        layer.open({
            type: 1,
            area: ['500px', '350px'],
            title: '注册',
            content: $("#registerform"),
            btn: ['注册', '取消'],
            btn1: function () {
                if (RegisterPwd() && EnsurePwd() && EnsureUserAccountExist()) {
                    $.ajax({
                        url: 'login/register',
                        data: {
                            registeruseraccount: $("#registeruseraccount").val(),
                            registerpass: $("#registerpass").val()
                        },
                        success: function (data) {
                            //关闭弹出层
                            layer.closeAll();
                            if (data == "注册成功") {
                                //提示注册成功
                                layer.msg("注册成功", {icon: 1, time: 3000});
                            } else if (data == "注册失败") {
                                //提示注册失败
                                layer.msg("注册失败", {icon: 1, time: 3000});
                            }

                        }
                    });
                }
            }
        });
    });
});

/* 验证码的js */
var div = document.querySelector('#captcha');
var characters = "QWETYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
var str;

function getRandom(l, r) {
    return parseInt(l + Math.random() * (r - l + 1));
}

var str = '';

function run() {
    while (div.hasChildNodes()) {
        div.removeChild(div.firstChild);
    }
    for (var i = 0; i < 4; i++) {
        var span = document.createElement('span');
        span.innerHTML = characters[getRandom(0, characters.length - 1)];
        span.style.display = 'inline-block';
        span.style.fontSize = getRandom(16, 32) + 'px';
        span.style.color = 'rgb(' + getRandom(0, 200) + ',' + getRandom(0, 200) + ',' + getRandom(0, 200) + ')';
        span.style.transform = 'translate(' + getRandom(-5, 5) + 'px, ' + getRandom(-5, 5) + 'px) rotate(' + getRandom(-20, 20) + 'deg)';
        str += span.innerHTML;
        div.appendChild(span);
    }
}

run();
div.addEventListener('click', function () {
    str = '';
    run(); //每次点击都刷新
});