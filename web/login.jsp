<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Iniciar Sesi&oacute;n</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/css/bootstrap.min.lumen.css" type="text/css" media="screen">
        <link rel="icon" type="image/ico" href="<%= request.getContextPath()%>/icon.ico">
        <style>
            .btn 
            {
                outline:0;
                border:none;
                border-top:none;
                border-bottom:none;
                border-left:none;
                border-right:none;
                box-shadow:inset 2px -3px rgba(0,0,0,0.15);
            }
            .btn:focus
            {
                outline:0;
                -webkit-outline:0;
                -moz-outline:0;
            }
            html{
                position: fixed;
                top: 0;
                right: 0;
                bottom: 0;
                left: 0;
                background-size: cover;
                background-position: 50% 50%;
                background-image: url('http://cleancanvas.herokuapp.com/img/backgrounds/color-splash.jpg');
                background-repeat:repeat;
            }
            .well {
                max-width: 400px;
                padding: 30px;
                margin: 0 auto;
                margin-top:50px;
            }
            .form-signin .form-signin-heading, .form-signin {
                margin-bottom: 10px;
            }
            .form-signin .form-control {
                position: relative;
                font-size: 16px;
                height: auto;
                padding: 10px;
                -webkit-box-sizing: border-box;
                -moz-box-sizing: border-box;
                box-sizing: border-box;
            }
            .form-signin .form-control:focus {
                z-index: 2;
            }
            .form-signin input[type="email"] {
                margin-bottom: -1px;
                border-bottom-left-radius: 0;
                border-bottom-right-radius: 0;
                border-top-style: solid;
                border-right-style: solid;
                border-bottom-style: none;
                border-left-style: solid;
                border-color: #000;
            }
            .form-signin input[type="password"] {
                margin-bottom: 10px;
                border-top-left-radius: 0;
                border-top-right-radius: 0;
                border-top-style: none;
                border-right-style: solid;
                border-bottom-style: solid;
                border-left-style: solid;
                border-color: rgb(0,0,0);
                border-top:1px solid rgba(0,0,0,0.08);
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="well">
                <form class="form-signin" role="form" action="Login" method="post">
                    <h2 class="form-signin-heading text-center">Por favor inicie sesi&oacute;n</h2>
                    <input type="email" name="email" class="form-control" placeholder="Ej: usuario@dominio.com" required autofocus/>
                    <input type="password" name="pass" class="form-control" placeholder="Contrase&ntilde;a" required />
                    <button class="btn btn-lg btn-success btn-block" type="submit">Iniciar Sesi&oacute;n</button>
                </form>
            </div>
        </div>
    </body>
    <script src="<%= request.getContextPath()%>/js/jquery.min.js"></script>
    <script src="<%= request.getContextPath()%>/js/bootstrap.js"></script>
</html>
