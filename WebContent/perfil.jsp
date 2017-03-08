<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Perfil</title>
    <link rel="stylesheet" type="text/css" href="less/stylo.css">
    <link rel="shortcut icon" href="imagenes/logo.png" type="image/png">
    <link href="./css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<!--    <link href='http://fonts.googleapis.com/css?family=Shadows+Into+Light' rel='stylesheet' type='text/css'> -->
<!-- jmmj13-001-site1.smarterasp.net/lectores/ayudamantener.html -->
    <link href='http://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'/>
  </head>
  <body onload="cargarPerfil()">
        <header id="cabecera"></header>
        <div id="login"></div>
        <div id="modall"></div>
        <img id="imagenfondoPerfil" src="imagenes/fondo.png" alt="imagen de fondo">
        <div id="registroperfil" class="container">
            <div class="row">
                <div class="col-xs-12 toppad" >
                    <div class="panel panel-info">
                        <div class="col-xs-6">
                            <div class="panel-heading">
                                <h3 class="panel-title">INFORMACION PERSONAL</h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-3 col-lg-3 "> <img alt="Foto" src="imagenes/user.png" class="img-circle img-responsive"> 
                                    </div>
                                    <div class=" col-md-9 col-lg-9">
                                        <div class="row">
                                            <span class="col-xs-3">Nombre :</span>
                                            <input  id="txtnombre" class=" txtPerfil col-xs-7" type='text' placeholder="Nombre"/>
                                        </div>
                                        <div class="row">
                                            <span class="col-xs-3">Email :</span>
                                            <input  id="txtemail" class=" txtPerfil col-xs-7" disabled ="disabled" type="email" name="email" placeholder="Correo"/>
                                        </div>
                                        <div class="row">
                                            <span class="col-xs-3">Id Cliente :</span>
                                            <input  id="txtIdCliente" class=" txtPerfil col-xs-7" disabled ="disabled" type="text"  placeholder="Id Cliente"/>
                                        </div>
                                        <div class="row">
                                            <a id="btnactualizar" href="#" class="btn btn-primary">Actualizar</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div> 
                        <div class="col-xs-6">
                            <div class="panel-heading">
                                <h3 class="panel-title">CAMBIAR CONTRASEÑA</h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class=" col-md-12 col-lg-12 "> 
                                        <div class="row">
                                            <span class="col-xs-6">Contraseña :</span>
                                            <input  id="txtpass" class="col-xs-4" type="password"  placeholder="Contraseña"/>
                                        </div>
                                        <div class="row">
                                            <span class="col-xs-6">Nueva Contraseña :</span>
                                            <input  id="txtnewpass" class="col-xs-4" type="password"  placeholder="Nueva Contraseña"/>
                                        </div>
                                        <div class="row">
                                            <span class="col-xs-6">Confirmar Nueva Contraseña :</span>
                                            <input  id="txtrepass" class="col-xs-4" type="password"  placeholder="Repetir Contraseña"/>
                                        </div>
                                        <a href="#" id="btnguardarPass" class="btn btn-primary">Cambiar Contraseña</a>
                                    </div>
                                </div>
                            </div> 
                        </div>  
                    </div>
                </div>
            </div>
        </div> 
    <footer id="pie"></footer>
    <div class="scroll-top-wrapper ">
        <span class="scroll-top-inner">
            <i class="fa fa-2x fa-arrow-circle-up"></i>
        </span>
    </div>
    <script src="./js/jquery-1.11.3.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/md5.js"></script>
    <script type="text/javascript" src="js/js.js" charset="UTF-8"></script>
<!--     <script type="text/javascript" src="js/jsIndex.js" charset="UTF-8"></script> -->
  </body>
</html>