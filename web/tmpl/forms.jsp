<!-- Agregar Categoria -->
<div class="modal fade" id="frmNewCat" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header success">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title text-info" id="myModalLabel">Agregar Categoría</h4>
            </div>
            <div class="modal-body">
                <div class="well well-lg">
                    <div id="datos"></div>
                    <form role="form" id="fNC">
                        <div class="form-group">
                            <label for="frmNewName">Nombre</label>
                            <input type="text" id="frmNewName" name="frmNewName" class="form-control" required size="100">
                        </div>
                        <div class="form-group float-label-control">
                            <label for="frmNewDesc">Descripción</label>
                            <textarea class="form-control" id="frmNewDesc" name="frmNewDesc" rows="2" cols="55" required></textarea>
                        </div>
                    </form>

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                <button type="button" class="btn btn-success ladda-button" data-style="expand-right"  id="btnSubmitNewCat">Crear Categoría</button>
            </div>
        </div>
    </div>
</div>

<!-- Agregar Producto -->
<div class="modal fade" id="frmNewProd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header success">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title text-info" id="myModalLabel">Agregar Producto</h4>
            </div>
            <div class="modal-body">
                <div class="well well-lg">
                    <div id="frmResNewPro"></div>
                    <form role="form">
                        <div class="form-group">
                            <label for="sltCatNewProd">Seleccione una categoría:</label>
                            <select id="sltCatNewProd" class="form-control" >
                                <option disable="true" value="0">....Cargando....</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="sltCatNewProd">Unidad Medida:</label>
                            <select id="sltUniNewProd" class="form-control" >
                                <option disable="true" value="0">....Cargando....</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="frmNewNameProd">Nombre</label>
                            <input type="text" id="frmNewNameProd" name="frmNewName" class="form-control" required size="100">
                        </div>
                        <div class="form-group">
                            <label for="frmNewDescProd">Descripción</label>
                            <textarea class="form-control" id="frmNewDescProd" name="frmNewDesc" rows="2" cols="55" required></textarea>
                        </div>
                    </form>

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                <button type="button" class="btn btn-success ladda-button" data-style="expand-right" id="btnSubmitNewProd"><span class="ladda-label">Crear Producto</span></button>
            </div>
        </div>
    </div>
</div>


<!-- Eliminar Categoria-->
<div class="modal fade" id="frmDelCat" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header success">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title text-danger" id="myModalLabel">Eliminar Categoría</h4>
            </div>
            <div class="modal-body">
                <div class="well well-lg">
                    <div id="msgDelCat"></div>
                    <h2 class="text-warning">Esta acción no se puede deshacer!</h2>
                    <p class="text-danger">Nota: Se eliminará toda la información asociada a esta categoría.</p>
                    <form role="form">
                        <div class="form-group">
                            <span class="help-block">Seleccione la categoría a ser eliminada.</span>
                            <select id="sltCatDel" name="sltCatDel">
                                <option disable="true" value="0">....Cargando...</option>
                            </select>
                        </div>
                    </form>
                    <div class="panel panel-primary ">
                        <div class="panel-heading">Descripción de categoría:</div>
                        <div class="panel-body" id="desCatDel">
                            Seleccione una categoría.
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">

                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                <button type="button" class="btn btn-danger ladda-button" data-style="expand-right"  id="btnDelCatExe">Eliminar Categoría</button>
            </div>
        </div>
    </div>
</div>

<!-- Modificar Categoria-->
<div class="modal fade" id="frmModCat" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header success">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title text-info" id="myModalLabel">Modificar Categoría</h4>
            </div>
            <div class="modal-body">
                <div class="well well-lg">
                    <div id="msgModCat"></div>
                    <form role="form" action="<%= request.getContextPath()%>/home" method="post" id="fNC">
                        <div class="form-group">
                            <span class="help-block">Seleccione la categoría a modificar.</span>
                            <select id="sltcatMod" name="sltCatMod"><option value="0">...Cargando...</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="frmModName">Nombre</label>
                            <input type="text" id="frmModName" name="frmNewName" class="form-control" required size="100">
                        </div>
                        <div class="form-group float-label-control">
                            <label for="frmModDesc">Descripción</label>
                            <textarea class="form-control" id="frmModDesc" name="frmNewDesc" rows="3" cols="55" required></textarea>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                <button type="button" class="btn btn-warning ladda-button" data-style="expand-right"  id="btnFrmModCatExe">Modificar Categoría</button>
            </div>
        </div>
    </div>
</div>
