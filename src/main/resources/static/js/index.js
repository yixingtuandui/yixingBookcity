$(document).ready(function(){
    $("#exampleInputUsername1").blur(function(){
        $.ajax({
            url:"/check",
            type:'post',
            data:{"username":$("#exampleInputUsername1").val()},
            dataType:'json',
            success:function(data){
                if(data.status){
                    $(".key1").css("background","url(../images/right.png) no-repeat 235px 27px;");
                }else{
                    $(".key1").css("background","url(../images/pass.png) no-repeat 235px 27px;");
                }
            }
        })
    })
    $("#exampleInputUsername1").blur(function(){
        var reg=/^(?![\\d]+$)(?![a-zA-Z]+$)(?![^\\da-zA-Z]+$).{6,20}$/;
        if(reg.test($("#exampleInputUsername2").val())){
            $(".key2").css("background","url(../images/right.png) no-repeat 224px 30px;");
        }else{
            $(".key2").css("background","url(../images/pass.png) no-repeat 224px 30px;");
        }
    })
})

