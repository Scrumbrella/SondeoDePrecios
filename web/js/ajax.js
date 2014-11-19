
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
            cosa = "";
            mensaje = "Ocurrio un error al procesar la petición";

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

function gestNewValProd(estado) {
    if (estado === true) {
        $('#sltCatModProd').val(0);
        $('#sltUniModProd').val(1);
        $('#frmModNameProd').val(null);
        $('#frmModDescProd').val(null);

    }
    $('#sltCatModProd').prop('disabled', estado);
    $('#sltUniModProd').prop('disabled', estado);
    $('#frmModNameProd').prop('disabled', estado);
    $('#frmModDescProd').prop('disabled', estado);

}

(function($) {
    $.fn.extend({
        limiter: function(limit, elem) {
            $(this).on("change focus keyup", function() {
                setCount(this, elem);
            });
            function setCount(src, elem) {
                var chars = src.value.length;
                if (chars > limit) {
                    src.value = src.value.substr(0, limit);
                    chars = limit;
                }
                elem.html(limit - chars);
            }
            setCount($(this)[0], elem);
        }
    });
})(jQuery);

$(document).ready(function() {

    $('#btnSubmitNewProd').click(function(event) {
        event.preventDefault();
        var idCatVar = $('#sltCatNewProd').val();
        var nombreVar = $('#frmNewNameProd').val();
        var descVar = $('#frmNewDescProd').val();
        var unidadVar = $("#sltUniNewProd").val();
        var datos = {idCategoria: idCatVar, nombre: nombreVar, descripcion: descVar, unidad: unidadVar, action: 'newProd'};
        enviarDatos(datos, '#frmResNewPro', this, "El producto \"" + nombreVar + "\"", "ingreso");
    });


    $('#btnSubmitModProd').click(function() {
        var idCat = $('#sltCatModProd').val();
        if (idCat !== "0") {
            var idProd = $('#sltProdSrchModProd').val();
            var uni = $('#sltUniModProd').val();
            var nombre = $('#frmModNameProd').val();
            var desc = $('#frmModDescProd').val();
            var datos = {action: 'modProd', idCat: idCat, unidad: uni, nombre: nombre, desc: desc, idProd: idProd};
            enviarDatos(datos, '#frmResModProd', this, "El producto \"" + nombre + "\"", "modifico");
        } else
            showMsgEmpty('#frmResModProd');

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
        $('#frmModDesc').val(null);
        $('#frmModName').val(null);
        showCats('#sltcatMod');
    });

    $('#frmModProd').on('show.bs.modal', function() {
        showCats('#sltCatSrchModProd');
        showCats('#sltCatModProd');
        $('#sltProdSrchModProd').html('<option value="0" disable>Selecione una categor&iacute;a</option>');
        $('#sltProdSrchModProd').prop("disabled", true);
        gestNewValProd(true);
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
            $("#frmNewName").limiter(100, $("#countNewNameCat"));
            $("#frmNewDesc").limiter(200, $("#countNewDescCat"));
        });
    });

    $('#sltProdSrchModProd').change(function() {
        var idProdSel = $(this).val();
        if (idProdSel !== "0") {
            gestNewValProd(false);
            $.post('home', {action: 'getDataProd', idProd: idProdSel}, function(res) {
                var datos = res.split("|");
                $('#sltCatModProd').val(datos[0]);
                $('#sltUniModProd').val(datos[1]);
                $('#frmModNameProd').val(datos[2]);
                $('#frmModDescProd').val(datos[3]);
            });
        } else {
            gestNewValProd(true);
        }

    });

    $('#sltCatSrchModProd').change(function() {
        var idCatSel = $(this).val();
        $('#sltProdSrchModProd').html('<option value="0" disable>.::Seleccione una categor&iacute;a::.</option>');
        $('#sltProdSrchModProd').prop("disabled", true);
        gestNewValProd(true);
        if (idCatSel !== "0")
            $.post('home', {action: 'getCatProd', idCat: idCatSel}, function(res) {
                var datos = res.split("|");
                var i, linea, options;
                if (datos.length > 1) {
                    options = '<option value="0" disable>Seleccionar...</option>';
                    $('#sltProdSrchModProd').prop("disabled", false);
                    for (i = 0; i < datos.length - 1; i++) {
                        linea = datos[i].split("*");
                        options += '<option value="' + linea[0] + '">' + linea[1] + '</option>';
                    }

                } else {
                    options = '<option value="0" disable>No hay productos</option>';
                    $('#sltProdSrchModProd').prop("disabled", true);
                    gestNewValProd(true);
                }
                $('#sltProdSrchModProd').html(options);
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

    $("#frmModNameProd").limiter(100, $("#countNameModProd"));

    $("#frmModName").limiter(100, $("#countModNameCat"));
    $("#frmModDesc").limiter(200, $("#countModDescCat"));
    $("#frmModNameProd").limiter(100, $("#countNameModProd"));


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

$(function() {
    $('#btn').click(function() {
        $('#slt').val("val3");
    });
});
