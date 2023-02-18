$("#exit").on("click", function () {
    var result = confirm("确定要退出吗？");
    if (result) {
        window.parent.frames.location.href = "login/loginexit";
    }
});