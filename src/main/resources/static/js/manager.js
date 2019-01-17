$(function() {
    //$(".orders-table").tablesorter();
    $("#searchbook").click(function () {
        $("#forms").attr("action","/searchbook");
        $("#forms").submit();
    });
    $("#searchuser").click(function () {
        $("#forms").attr("action","/searchuser");
        $("#forms").submit();
    });
})