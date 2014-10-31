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
                        <input type="hidden" value="newCat" name="action">
                    </form>

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                <button type="button" class="btn btn-success" form="fNC" id="btnSubmitNewCat">Crear Categoría</button>
            </div>
        </div>
    </div>
</div>



<!-- Eliminar -->
<div class="modal fade" id="frmDelCat" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header success">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title text-danger" id="myModalLabel">Eliminar Categoría</h4>
            </div>
            <div class="modal-body">
                <div class="well well-lg">
                    <div id="msgErrorDelCat"></div>
                    <h2 class="text-warning">Esta acción no se puede deshacer!</h2>
                    <form role="form" id="fDC" method="post" action="<%= request.getContextPath()%>/home">
                        <div class="form-group">
                            <span class="help-block">Seleccione la categoría a ser eliminada.</span>
                            <select id="sltcat" name="sltCatDel">
                                <option disable="true" value="0">....Cargando...</option>
                            </select>
                        </div>
                    </form>
                    <div class="panel panel-info">
                        <div class="panel-heading">Descripción de categoría:</div>
                        <div class="panel-body" id="desCatDel">
                            Seleccione una categoría.
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                <button type="button" class="btn btn-danger" id="btnDelCatExe">Eliminar Categoría</button>
            </div>
        </div>
    </div>
</div>

<!-- Modificar -->
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
                <button type="button" class="btn btn-warning" id="btnFrmModCatExe">Modificar Categoría</button>
            </div>
        </div>
    </div>
</div>
