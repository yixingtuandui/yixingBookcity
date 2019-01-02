$(function() {
    // books();
    userall();
    author();
    member();
})
function userall() {
    $("#userall").click(function(){//普通用户展示
        $.ajax({
            url:"userall",
            type:'post',
            dataType:'json',
            success:function(data){
                $("#talk").val("userall");
                $(".slate_user").empty();
                $(".slate_user").append(
                    "<div class='page-header'>"
                    +"<h2><i class='icon-shopping-cart pull-right'></i>所有浏览用户</h2>"
                    +"</div>"
                    +"<table class='orders-table table'>"
                    +"<thead>"
                    +"<th>序号</th><th>用户名</th><th>手机号</th><th>书券</th><th>性别</th><th>连签天数</th><th>上一次签到</th><th>注册时间</th>"
                    +"</thead>"
                    +"<tbody></tbody></table>"+
                    "<div class='pages'><ul class='pages1'><li id='nowpage'>当前是第1页</li><li id='totalpage'>共页</li>" +
                    "<li><a>首页</a></li><li><a>上一页</a></li><li><a>下一页</a></li><li><a>尾页</a></li></ul></div>"
                );
                users(data);
            }
        })
    })
}
function author() {
    $("#author").click(function(){//作者展示
        $.ajax({
            url:"author",
            type:'post',
            dataType:'json',
            success:function(data){
                $("#talk").val("author");
                $(".slate_user").empty();
                $(".slate_user").append(
                    "<div class='page-header'>"
                    +"<h2><i class='icon-shopping-cart pull-right'></i>所有作者</h2>"
                    +"</div>"
                    +"<table class='orders-table table'>"
                    +"<thead>"
                    +"<th>序号</th><th>用户名</th><th>手机号</th><th>书券</th><th>性别</th><th>连签天数</th><th>上一次签到</th><th>注册时间</th>"
                    +"</thead>"
                    +"<tbody></tbody></table>"+
                "<div class='pages'><ul class='pages1'><li id='nowpage'>当前是第1页</li><li id='totalpage'>共页</li>" +
                "<li><a>首页</a></li><li><a>上一页</a></li><li><a>下一页</a></li><li><a>尾页</a></li></ul></div>"
                );
                users(data);
            }
        })
    })
}
function member() {
    $("#member").click(function(){//会员展示
        $.ajax({
            url:"member",
            type:'post',
            dataType:'json',
            success:function(data){
                $("#talk").val("member");
                $(".slate_user").empty();
                $(".slate_user").append(
                    "<div class='page-header'>"
                    +"<h2><i class='icon-shopping-cart pull-right'></i>全部会员</h2>"
                    +"</div>"
                    +"<table class='orders-table table'>"
                    +"<thead>"
                    +"<th>序号</th><th>用户名</th><th>手机号</th><th>书券</th><th>性别</th><th>连签天数</th><th>上一次签到</th><th>注册时间</th>"
                    +"</thead>"
                    +"<tbody></tbody></table>"+
                    "<div class='pages'><ul class='pages1'><li id='nowpage'>当前是第1页</li><li id='totalpage'>共页</li>" +
                    "<li><a>首页</a></li><li><a>上一页</a></li><li><a>下一页</a></li><li><a>尾页</a></li></ul></div>"
                );
                users(data);
            }
        })
    })
}
function booksall() {
    $("#booksall").click(function(){//书籍全部展示
        $.ajax({
            url:"booksall",
            type:'post',
            dataType:'json',
            success:function(data){
                $("#talk").val("booksall");
                $(".slate_user").empty();
                $(".slate_user").append(
                    "<div class='page-header'>"
                    +"<h2><i class='icon-shopping-cart pull-right'></i>全部书籍</h2>"
                    +"</div>"
                    +"<table class='orders-table table'>"
                    +"<thead>"
                    +"<th>序号</th><th>书名</th><th>作者</th><th>价格</th><th>点击量</th>" +
                    "<th>类型</th><th>更新状态</th><th>类别</th><th>购买量</th>"
                    +"</thead>"
                    +"<tbody></tbody></table>"+
                    "<div class='pages'><ul class='pages1'><li id='nowpage'>当前是第1页</li><li id='totalpage'>共页</li>" +
                    "<li><a id='first'>首页</a></li><li><a id='above'>上一页</a></li><li><a id='next'>下一页</a></li><li><a id='end'>尾页</a></li></ul></div>"
                );
                books(data);
            }
        })
    })
}
//首页
function firstpages() {

}
//尾页
function endpages() {

}
//下一页
function nextpages() {

}
//上一页
function abovepage() {
    $("#nowpage").click(function () {
        if($(this).val()!="1"){

        }
    })
}
//user表展示(普通用户、作者、会员)
function users(data2) {
    $(".orders-table tbody").empty();
    for(var i=0;i<data2.length;i++){
        $(".orders-table tbody").append(
            "<tr><td>"+(i+1)+"</td><td><a href='#'>"+data2[i].name+"</a></td><td>"+data2[i].phone+"</td><td>"+data2[i].money+"</td>" +
            "<td>"+data2[i].sex+"</td><td>"+data2[i].day+"</td><td>"+data2[i].date_sign+"</td><td>"+data2[i].date_reg+"</td></tr>"
        )
    }
}
//books表展示(已上架、审核新书、更新)
function books(data2) {
    for(var i=0;i<data2.length;i++){
        $(".orders-table tbody").empty();
        $(".orders-table tbody").append(
            "<tr><td>"+(i+1)+"</td><td><a href='#'>"+data2[i].book_name+"</a></td><td>"+data2[i].author+"</td><td>"+data2[i].price+"</td>" +
            "<td>"+data2[i].number+"</td><td>"+data2[i].type+"</td><td>"+data2[i].status+"</td><td>"+data2[i].kinds+"</td><td>"+data2[i].amount+"</td></tr>"
        )
    }
}
//首次进入
function books() {
    $.ajax({
        url:"bookall",
        type:'post',
        dataType:'json',
        success:function(data){
            $(".slate_user").append(
            "<div class='page-header'>"
                +"<h2><i class='icon-shopping-cart pull-right'></i>书籍总点击量排行前十</h2>"
            +"</div>"
            +"<table class='orders-table table'>"
                +"<thead>"
                +"<tr>名次</tr><tr>书名及作者</tr><tr>点击量</tr>"
            +"</thead>"
            +"<tbody></tbody></table>"
            );
            for(var i=0;i<data.length;i++){
                $(".orders-table tbody").append(
                "<tr><td>i</td><td><a href='#'>data[i].book_name</a> <span class='label label-info'>data[i].author</span></td>"
                +"<td>data[i].number</td></tr>"
                )
            }
        }
    })
}
// //str为传入的字符串为毫秒值
// function getMyDate(str){
//     var oDate = new Date(str),
//         oYear = oDate.getFullYear(),
//         oMonth = oDate.getMonth()+1,
//         oDay = oDate.getDate(),
//         oHour = oDate.getHours(),
//         oMin = oDate.getMinutes(),
//         oSen = oDate.getSeconds(),
//         oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);
//     //最后拼接时间为 1999-10-02 11:23:01样式
//     return oTime;
// };
// //补0操作
// function getzf(num){
//     if(parseInt(num) < 10){
//         num = '0'+num;
//     }
//     return num;
// }