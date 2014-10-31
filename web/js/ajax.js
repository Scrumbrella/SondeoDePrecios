$(document).ready(function() {
    $('#btnSubmitNewCat').click(function(event) {
        var nombreVar = $('#frmNewName').val();
        var descVar = $('#frmNewDesc').val();
        $('#frmNewDesc').val("");
        $('#frmNewName').val("");

        if (nombreVar != "" && descVar != "") {
            $.post('home', {
                frmNewName: nombreVar,
                frmNewDesc: descVar,
                action: 'newCat'
            }, function(responseText) {
                $('#datos').html(responseText);
            });
        } else {
            $('#datos').html('<div class="alert alert-dismissable alert-danger"><button type="button" class="close" data-dismiss="alert">×</button><strong>¡Error!</strong> Todos los campos son obligatorios.</div>');
        }
    });

    $('#btnDelCat').click(function() {
        $('#frmDelCat').modal('show');
    });

    $('#btnModCatNav').click(function() {
        $('#frmModCat').modal('show');
    });

    $('#frmModCat').on('show.bs.modal', function(e) {
        $('#msgModCat').html(null);
        $('#frmModDesc').val("");
        $('#frmModName').val("");
        $.post('home', {action: 'showCat'},
        function(responseText) {
            $('#sltcatMod').html(responseText);
        });
    });

    $('#frmDelCat').on('show.bs.modal', function(e) {
        $('#msgErrorDelCat').html(null);
        $('#desCatDel').html("Selecione una categoría");
        $.post('home', {action: 'showCat'},
        function(responseText) {
            $('#sltcat').html(responseText);
        });
    });

    $('#sltcat').change(function() {
        $("#sltcat option:selected").each(function() {
            var idCatSel = $(this).val();
            if (idCatSel !== "0") {
                $.post('home', {idCat: idCatSel, action: 'descCatDel'
                }, function(responseText) {
                    $('#desCatDel').html(responseText);
                });
            } else {
                $('#desCatDel').html("Selecione una categoría");
            }

        });
    });

    $('#sltcatMod').change(function() {
        $("#sltcatMod option:selected").each(function() {
            var idCatSel = $(this).val();
            if (idCatSel !== "0") {
                $.post('home', {idCat: idCatSel, action: 'getCatValues'
                }, function(responseText) {
                    var datos = responseText.split("|");
                    $('#frmModDesc').val(datos[0]);
                    $('#frmModName').val(datos [1]);
                    $('#msgModCat').html(null);
                });
            } else {
                $('#frmModDesc').val("");
                $('#frmModName').val("");
            }

        });
    });

    $('#btnFrmModCatExe').click(function() {
        $("#sltcatMod option:selected").each(function() {
            var newModDesc = $('#frmModDesc').val();
            var newModName = $('#frmModName').val();
            var idCatSel = $(this).val();
            if (idCatSel !== "0") {
                $.post('home', {idCat: idCatSel, nombre: newModName, descripcion: newModDesc, action: 'CatMod'},
                function(responseText) {
                    $('#msgModCat').html(responseText);
                    $('#frmModDesc').val("");
                    $('#frmModName').val("");
                    $.post('home', {action: 'showCat'},
                    function(responseText) {
                        $('#sltcatMod').html(responseText);
                    });
                });
            } else {
                $('#msgModCat').html('<div class="alert alert-dismissable alert-warning"><button type="button" class="close" data-dismiss="alert">×</button><strong>¡Por Favor!</strong> Seleccione una categoría a ser modificada.</div>');
            }

        });

    });

    $('#btnDelCatExe').click(function() {
        $("#sltcat option:selected").each(function() {
            var idCatSel = $(this).val();
            var value = $(this).text();
            if (idCatSel !== "0") {
                $.post('home', {idCat: idCatSel, action: 'CatDel'},
                function(responseText) {
                    if (responseText === "ok") {
                        $('#msgErrorDelCat').html('<div class="alert alert-dismissable alert-success"><button type="button" class="close" data-dismiss="alert">×</button><strong>¡Exito!</strong> Se ha borrado la categoría: "' + value + '".</div>');
                        $('#desCatDel').html("Selecione una categoría");
                        $.post('home', {action: 'showCat'},
                        function(responseText) {
                            $('#sltcat').html(responseText);
                        });
                    } else {
                        $('#msgErrorDelCat').html('<div class="alert alert-dismissable alert-danger"><button type="button" class="close" data-dismiss="alert">×</button><strong>¡Rayos!</strong> Ocurrio un error al borrar la categoría: "' + value + '".</div>');
                    }
                });
            } else {
                $('#msgErrorDelCat').html('<div class="alert alert-dismissable alert-warning"><button type="button" class="close" data-dismiss="alert">×</button><strong>¡Por Favor!</strong> Seleccione una categoría a ser eliminada.</div>');
            }

        });

    });

});                          