
$('[data-toggle="confirmation"]').confirmation();
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