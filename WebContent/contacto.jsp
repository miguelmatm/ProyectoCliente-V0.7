<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ES">
	<head>
		<meta charset="utf-8">
		<link href="./css/bootstrap.css" rel="stylesheet">
		<title>Contacto</title>
		<link rel="stylesheet" type="text/css" href="less/stylo.css">
		<link rel="shortcut icon" href="imagenes/logo.png" type="image/png">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"> 
	</head>
	<body>
		<header id="cabecera"></header>
		<div id="login"></div>
		<div id="modall"></div>	
		<object id="mapa" type="text/html" data="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1797.826934976806!2d-6.242058808492073!3d36.59355477206148!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd0dd021112903a5%3A0x940a13869d8c0eb7!2sAv.+Poeta+Rafael+Alberti%2C+11500+El+Puerto+de+Sta+Mar%C3%ADa%2C+C%C3%A1diz!5e0!3m2!1ses!2ses!4v1457036133016"> </object> 
		<div class="jumbotron jumbotron-sm">
		    <div class="container">
		        <div class="row">
		            <div class="col-sm-12 col-lg-12">
		                <h1 id="contactanosCentrar" class="h1"> Contacte con nosotros</h1>
		            </div>
		        </div>
		    </div>
		</div>
		<div class="container">
		    <div class="row">
		        <div class="col-md-8">
		            <div class="well well-sm">
		                <form>
		                <div class="row">
		                    <div class="col-md-6">
		                        <div class="form-group">
		                            <label for="name">
		                                nombre</label>
		                            <input type="text" class="form-control" id="name" placeholder="Introducir Nombre" required="required" />
		                        </div>
		                    </div>
		                    <div class="col-md-6">
		                    	<div class="form-group">
		                            <label for="email">
		                                Email</label>
		                            <div class="input-group">
		                                <span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span>
		                                </span>
		                                <input type="email" class="form-control" id="email" placeholder="Introducir Correo" required="required" />
		                            </div>
		                        </div>
		                    </div>
		                </div>
		                <div class="row">
		                	<div class="col-md-12">
								<div class="form-group">
		                            <label for="subject">
		                                Asunto</label>
		                            <select id="subject" name="subject" class="form-control" required="required">
		                                <option value="" selected="">Elige Una Opcion:</option>
		                                <option value="Servicio de Marketing">Servicio de Marketing</option>
		                                <option value="Asistencia Tecnica">Asistencia Tecnica</option>
		                                <option value="Mantenimiento">Mantenimiento</option>
		                            </select>
		                        </div>
		                	</div>
		                </div>
		               	<div class="row">
		                	<div class="col-md-12">
		                		<div class="form-group">
		                            <label for="name">
		                                Mensaje</label>
		                            <textarea name="message" id="message" class="form-control" rows="9" cols="25" required="required"
		                                placeholder="Mensaje"></textarea>
		                        <div class="col-md-12">
		                        	<button type="submit" class="btn btn-primary pull-right" id="btnContactUs">
		                            Enviar Mensaje</button>
		                    	</div>
		                        </div>
		                	</div>
		                </div>
		                </form>
		            </div>
		        </div>
		        <div class="col-md-4">
		            <form>
		            <fieldset>
		            	<legend><span class="glyphicon glyphicon-globe"></span> Nuestra Oficina</legend>
		            </fieldset>
		            <address>
		                <strong>Twitter, Inc.</strong><br>
		                C/Cruz Roja Española<br>
		                Cadiz, CA 11009<br>
		                <abbr title="Phone">
		                    P:</abbr>
		                (000) 900-456-790
		            </address>
		            <address>
		                <strong>Nuestro Correo </strong><br>
		                <a href="mailto:#">miguelmatm3@gmail.com</a>
		            </address>
		            </form>
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
	</body>
</html>