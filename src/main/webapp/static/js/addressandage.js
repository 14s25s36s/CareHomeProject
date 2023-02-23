var form = layui.form;
var $ = layui.$;
$.ajax({
    url: "address/province",
    data: {},
    success: function (result) {
        result = JSON.parse(result);
        for (var i = 0; i < result.length; i++) {
            var item = result[i];
            var $option = $("<option></option>").val(item.provinceid).text(item.province);
            $("#province").append($option);
        }
        form.render();
    }

});

form.on('select(province)', function () {
    $("#city").empty();
    $("#area").empty();
    var value = $("#province").val();
    $.ajax({
        url: "address/city",
        data: {provinceid: value},
        success: function (result) {
            result = JSON.parse(result);
            for (var i = 0; i < result.length; i++) {
                var item = result[i];
                var $option = $("<option></option>").val(item.cityid).text(item.city);
                $("#city").append($option);
            }
            form.render();
        }
    });
});
form.on('select(city)', function () {
    $("#area").empty();
    var value = $("#city").val();
    $.ajax({
        url: "address/area",
        data: {cityid: value},
        success: function (result) {
            result = JSON.parse(result);
            for (var i = 0; i < result.length; i++) {
                var item = result[i];
                var $option = $("<option></option>").val(item.areaid).text(item.area);
                $("#area").append($option);
            }
            form.render();
        }
    });
});
//日期
layui.use('laydate', function () {
    var laydate = layui.laydate;
    //执行一个laydate实例
    laydate.render({
        elem: '#date1' //指定元素
    });
});