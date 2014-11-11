<div class="navbar navbar-inverse">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="<%= request.getContextPath()%>/home"><span class="glyphicon glyphicon-home" ></span>&nbsp;Scrumbrella</a>
    </div>
    <div class="navbar-collapse collapse navbar-responsive-collapse">
        <ul class="nav navbar-nav">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Categor&iacute;as <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="#frmNewCat" data-toggle="modal">Agregar</a></li>
                    <li><a id="btnModCatNav" href="#">Modificar</a></li>
                    <li class="divider"></li>
                    <li class="dropdown-header text-warning">Precauci&oacute;n!</li>
                    <li><a id="btnDelCat" href="#" >Eliminar</a></li>
                </ul>
            </li>
        </ul>
        <ul class="nav navbar-nav">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Productos <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a id="btnNewProdNav" href="#">Agregar</a></li>
                    <li><a id="btnModProdNav" href="#">Modificar</a></li>
                    <li class="divider"></li>
                    <li class="dropdown-header text-warning">Precauci&oacute;n!</li>
                    <li><a id="btnDelProdNav" href="#" >Eliminar</a></li>
                </ul>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <span class="glyphicon glyphicon-user"></span> 
                    <strong>${requestScope['usuario']}</strong>
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li>
                        <div class="navbar-login">
                            <div class="row">
                                <div class="col-lg-4">
                                    <p class="text-center">
                                        <span class="glyphicon glyphicon-user icon-size"></span>
                                    </p>
                                </div>
                                <div class="col-lg-8">
                                    <p class="text-left"><strong>${requestScope['usuario']}</strong></p>
                                    <p class="text-left small">${requestScope['email']}</p>
                                    <p class="text-left">
                                        <a href="#" class="btn btn-primary btn-block btn-sm">Actualizar Datos</a>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <div class="navbar-login navbar-login-session">
                            <div class="row">
                                <div class="col-lg-12">
                                    <p>
                                        <a href="#" class="btn btn-danger btn-block">Cerrar Sesion</a>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</div>
