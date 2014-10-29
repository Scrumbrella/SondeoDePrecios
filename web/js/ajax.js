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
});