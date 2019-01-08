$(function() {
    $("#searchbook").click(function () {
        console.log(1232)
        $("#forms").attr("active","searchbook?pageNum=1");
        $("#forms").submit();
    })
    $("#searchuser").click(function () {
        console.log(4568)
        $("#forms").attr("active","searchuser?pageNum=1");
        $("#forms").submit();
    })
})