$(document).ready(function(){
    $("#exampleInputUsername1").blur(function(){
        $.ajax({
            url:"/check",
            type:'post',
            data:{"username":$("#exampleInputUsername1").val()},
            dataType:'json',
            success:function(data){
                if(data){
                    $(".key1").attr("style","background:url(../images/right.png) no-repeat 390px 60px;");
                }else{
                    console.log($(".key1"))
                    $(".key1").attr("style","background:url(../images/pass.png) no-repeat 390px 60px;");
                }
            }
        })
    })
    $("#exampleInputUsername2").blur(function(){
        var reg=/^(?![\\d]+$)(?![a-zA-Z]+$)(?![^\\da-zA-Z]+$).{6,20}$/;
        if(reg.test($("#exampleInputUsername2").val())){
            $(".key2").attr("style","background:url(../images/right.png) no-repeat 390px 30px;");
        }else{
            $(".key2").attr("style","background:url(../images/pass.png) no-repeat 390px 30px;");
        }
    })
})

