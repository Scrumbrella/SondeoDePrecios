
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
    $(id).html('<div class="alert alert-dismissable alert-' + tipo + '"><button type="button" class="close" data-dismiss="alert">×</button><strong>' + msg + '</strong>.</div>');
}

function procesarRespuesta(respuesta, recibe, cosa, accion) {
    var tipo;
    var mensaje;
    switch (respuesta) {
        case '1':
            tipo = "success";
            mensaje = "se " + accion + " correctamente";
            break;
        case '2':
            tipo = "warning";
            mensaje = "ya existe";
            break;
        case '0':
            tipo = "danger";
            mensaje = "no se " + accion;
            break;
        default:
            tipo = "info";
            mensaje = "ocurrio un error";

    }
    showMsg(recibe, tipo, cosa + " " + mensaje);
}

function enviarDatos(valores, recibe, btn, cosa, accion) {
    if (!arrayVacio(valores)) {
        var l = Ladda.create(btn);
        l.start();
        $.post('home', valores, function(responseText) {
            l.stop();
            procesarRespuesta(responseText, recibe, cosa, accion);
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
        $("#sltUniNewProd option:selected").each(function() {
            unidadVar = $(this).val();
            console.log(unidadVar);
        });
        var datos = {idCategoria: idCatVar, nombre: nombreVar, descripcion: descVar, unidad: unidadVar, action: 'newProd'};
        enviarDatos(datos, '#frmResNewPro', this, "El producto \"" + nombreVar + "\"", "ingreso");
    });

    $('#btnSubmitNewCat').click(function(event) {
        event.preventDefault();
        var nombreVarCat = $('#frmNewName').val();
        var descVarCat = $('#frmNewDesc').val();
        var datos = {frmNewName: nombreVarCat, frmNewDesc: descVarCat, action: 'newCat'};
        enviarDatos(datos, '#datos', this, "La categoría \"" + nombreVarCat + "\"", "ingreso");
    });

    $('#btnNewProdNav').click(function() {
        $('#frmNewProd').modal('show');
    });
    $('#btnModProdNav').click(function() {
        $('#frmModProd').modal('show');
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
                enviarDatos(datos, '#msgModCat', btn, "La categoría " + newModName, "modifico");
                $('#frmModDesc').val("");
                $('#frmModName').val("");
                showCats('#sltcatMod');
            } else {
                showMsg('#msgModCat', 'warning', "Seleccione una categoría a ser modificada");
            }
        });

    });




    $('#btnDelCatExes').click(function() {
        var btn = this;
        $("#sltCatDel option:selected").each(function() {
            var idCatSel = $(this).val();
            var value = $(this).text();
            if (idCatSel !== "0") {
                var valores = {idCat: idCatSel, action: 'CatDel'};
                enviarDatos(valores, '#msgDelCat', btn, "La categoría \"" + value + "\"", "elimino");
                showCats("#sltCatDel");
            } else {
                showMsgEmpty('#msgDelCat');
            }
        });
    });

});

function delCatExe() {
    var btn = document.getElementById('btnDelCatExe');
    $("#sltCatDel option:selected").each(function() {
        var idCatSel = $(this).val();
        var value = $(this).text();
        if (idCatSel !== "0") {
            var valores = {idCat: idCatSel, action: 'CatDel'};
            enviarDatos(valores, '#msgDelCat', btn, "La categoría \"" + value + "\"", "elimino");
            showCats("#sltCatDel");
        } else {
            showMsgEmpty('#msgDelCat');
        }
    });

}