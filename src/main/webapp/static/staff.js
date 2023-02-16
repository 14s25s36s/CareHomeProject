var table=layui.table;
var layer=layui.layer;
var $=layui.$;
//监听行工具事件
  table.on('tool(datalist)', function(obj){
   //当前行的数据
    var data = obj.data;
    console.log(data);
    if(obj.event == 'delete'){
      layer.confirm('真的删除行么', function(index){
       		$.ajax({
       			url:'staff/delete',
       			data:data,
       			success:function(result){
       				//关闭弹出框
       				layer.closeAll();
       				layer.msg("删除成功",{icon:1});
       				//让表格重新加载数据
       				table.reload("datalist");
       			}
       		});
      });
    } else if(obj.event == 'update'){
    	location.href="staff/page?id="+data.id;
    }
  });