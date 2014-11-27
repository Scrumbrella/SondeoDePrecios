
$('[data-toggle="confirmation"]').confirmation();
$('#btnDelEstaExe').confirmation({
    onConfirm: function() {
        delEstaExe();
    }
});
$('#btnDelCatExe').confirmation({
    onConfirm: function() {
        delCatExe();
    }
});
$('#btnDelProdExe').confirmation({
    onConfirm: function() {
        delProdExe();
    }
});