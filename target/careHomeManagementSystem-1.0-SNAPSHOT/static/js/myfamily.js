var table = layui.table;
var layer = layui.layer;
// var $ = layui.$;
var form = layui.form;
//监听行工具事件
table.on('tool(familylist)', function (obj) {
    //当前行的数据
    var data = obj.data;
    // console.log(data);
    if (obj.event == 'delete') {
        layer.confirm('真的删除行么', function (index) {
            $.ajax({
                url: 'live/deleteliveinfo',
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
                    table.reload("familylist");
                }
            });
        });
    } else if (obj.event == 'update') {
        location.href = "live/toupdateliveinfo?lid=" + data.lid;

    }
});


$.ajax({
    url: "myinfo/myfamilyname",
    data: {},
    success: function (result) {
        result = JSON.parse(result);
        for (var i = 0; i < result.length; i++) {
            var item = result[i];
            console.log(item);
            console.log(item.lname);
            var $option = $("<option></option>").val(item.lname).text(item.lname);
            $("#familyinfo").append($option);

        }
        form.render();
    }
});
//在id选择器$("#familyinfo")改变后，表格内容也会随之更改
$("#familyinfo").change(function (obj) {
    console.log("1231231");
    //执行查询
    var lname = $("#familyinfo").val();
    table.reload('familylist', {
        where: {lname: lname}
    });
});