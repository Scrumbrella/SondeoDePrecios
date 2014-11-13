
function arrayVacio(miArray) {
    var i;
    var res = false;
    for (i in miArray) {
        if (miArray[i] === "") {
            res = true;
            break;
        }
    }
    return res;
}

function showMsgEmpty(id) {
    $(id).html('<div class="alert alert-dismissable alert-danger"><button type="button" class="close" data-dismiss="alert">×</button><strong>¡Error!</strong> Todos los campos son obligatorios.</div>');
}

function showMsg(id, tipo, msg) {
    $(id).html('<div class="alert alert-dismissable alert-'+tipo+'"><button type="button" class="close" data-dismiss="alert">×</button><strong>'+msg+'</strong>.</div>');
}

function enviarDatos(valores, recibe, btn) {
    if (!arrayVacio(valores)) {
        var l = Ladda.create(btn);
        l.start();
        $.post('home', valores, function(responseText) {
            l.stop();
            $(recibe).html(responseText);
        });
    } else {
        showMsgEmpty(recibe);
    }
}

function showCats(recibe) {
    $.post('home', {action: 'showCat'},
    function(responseText) {
        $(recibe).html(responseText);
    });
}

$(document).ready(function() {

    $('#btnSubmitNewProd').click(function(event) {
        event.preventDefault();
        var idCatVar = $('#sltCatNewProd').val();
        var nombreVar = $('#frmNewNameProd').val();
        var descVar = $('#frmNewDescProd').val();
        var unidadVar;
        $("#sltUniNewPro option:selected").each(function() {
            unidadVar = $(this).val();
        });
        var datos = {idCategoria: idCatVar, nombre: nombreVar, descripcion: descVar, unidad: unidadVar, action: 'newProd'};
        enviarDatos(datos, '#frmResNewPro', this);
    });

    $('#btnSubmitNewCat').click(function(event) {
        event.preventDefault();
        var nombreVarCat = $('#frmNewName').val();
        var descVarCat = $('#frmNewDesc').val();
        var datos = {frmNewName: nombreVarCat, frmNewDesc: descVarCat, action: 'newCat'};
        enviarDatos(datos, '#datos', this)
    });

    $('#btnNewProdNav').click(function() {
        $('#frmNewProd').modal('show');
    });
    $('#btnDelCat').click(function() {
        $('#frmDelCat').modal('show');
    });
    $('#btnModCatNav').click(function() {
        $('#frmModCat').modal('show');
    });

    $('#frmNewProd').on('show.bs.modal', function() {
        showCats('#sltCatNewProd');
    });

    $('#frmModCat').on('show.bs.modal', function(e) {
        $('#msgModCat').html(null);
        $('#frmModDesc').val("");
        $('#frmModName').val("");
        showCats('#sltcatMod');
    });

    $('#frmDelCat').on('show.bs.modal', function(e) {
        $('#msgDelCat').html(null);
        $('#desCatDel').html("Selecione una categoría");
        showCats('#sltCatDel');
    });

    $('#sltCatDel').change(function() {
        $("#sltCatDel option:selected").each(function() {
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
        var btn = this;
        $("#sltcatMod option:selected").each(function() {
            var newModDesc = $('#frmModDesc').val();
            var newModName = $('#frmModName').val();
            var idCatSel = $(this).val();
            var datos = {idCat: idCatSel, nombre: newModName, descripcion: newModDesc, action: 'CatMod'};
            if (idCatSel !== "0") {
                enviarDatos(datos, '#msgModCat', btn);
                $('#frmModDesc').val("");
                $('#frmModName').val("");
                showCats('#sltcatMod');
            } else {
                showMsg('#msgModCat', 'warning', "Seleccione una categoría a ser modificada");
            }
        });

    });

    $('#btnDelCatExe').click(function() {
        $("#sltCatDel option:selected").each(function() {
            var idCatSel = $(this).val();
            var value = $(this).text();
            if (idCatSel !== "0") {
                $.post('home', {idCat: idCatSel, action: 'CatDel'},
                function(responseText) {
                    if (responseText === "ok") {
                        showMsg('#msgDelCat', 'success', 'Se ha borrado la categoría: "' + value + '"')
                        $('#desCatDel').html("Selecione una categoría");
                        showCats("#sltCatDel");
                    } else {
                        showMsg('#msgDelCat', 'danger', 'Ocurrio un error al borrar la categoría: "' + value + '"')
                    }
                });
            } else {
                showMsgEmpty('#msgDelCat');
            }
        });
    });

});                          