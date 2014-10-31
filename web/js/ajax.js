$(document).ready(function() {
    $('#btnSubmitNewCat').click(function(event) {
        
        var nombreVar = $('#frmNewName').val();
        var descVar = $('#frmNewDesc').val();
        // Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
        $.post('home', {
                frmNewName : nombreVar,
                frmNewDesc: descVar,
                action: 'newCat'
        }, function(responseText) {
            $('#datos').html(responseText);
        });
    });
    
    $('#btnDelCat').click(function (){
        $('#frmDelCat').modal('show');
    });
    
    $('#frmDelCat').on('show.bs.modal', function (e) {
        $.post('home', {action: 'showCat'},
        function(responseText) {
           $('#sltcat').html(responseText);
        });
    });
    $('#sltcat').change(function(){
        var idCatSel = $('#sltcat').val();
        $.post('home', {
                idCat: idCatSel,
                action: 'descCatDel'
        }, function(responseText) {
            $('#desCatDel').html(responseText);
        });
        
    });
    
});