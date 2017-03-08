<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ES">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Principal</title>
		<link rel="stylesheet" type="text/css" href="less/stylo.css">
		<link rel="shortcut icon" href="imagenes/logo.png" type="image/png">
		<link href="./css/bootstrap.css" rel="stylesheet">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<!-- 		<link href='http://fonts.googleapis.com/css?family=Shadows+Into+Light' rel='stylesheet' type='text/css'> -->
<!-- jmmj13-001-site1.smarterasp.net/lectores/ayudamantener.html -->
		<link href='http://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'/>
	</head>
	<body>
		<header id="cabecera"></header>
		<div id="login"></div>
		<div id="modall"></div>	
		<div id="carousel-id" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#carousel-id" data-slide-to="0" class=""></li>
				<li data-target="#carousel-id" data-slide-to="1" class=""></li>
				<li data-target="#carousel-id" data-slide-to="2" class="active"></li>
			</ol>
			<div class="carousel-inner">
				<div class="item">
					<img alt="First slide" src="imagenes/portada03.jpg">
				</div>
				<div class="item">
					<img alt="Second slide" src="imagenes/portada04.jpg">
				</div>
				<div class="item active">
					<img alt="Third slide" src="imagenes/portada07.jpg">
				</div>
			</div>
			<a class="left carousel-control" href="#carousel-id" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
			<a class="right carousel-control" href="#carousel-id" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
		</div>
	
		<p id="tituloIntroducion"> Importante empresa expertos en la creación de paginas web. <br><span>Comprometidos con nuestros clientes</span></p>

		<div class="container-fluid">
			<div class="col-md-3 col-sm-3 col-sm-offset-1 columnasTriple">
				<article class="boxIcon dinaAnim-invisible dinaAnim-animated dinaAnim-fadeInUp dinaAnim-visible" data-dinadelay="10" data-dinaanim="fadeInUp" style="animation-delay: 10ms; opacity: 1;">
					<a href="#">
						<div class="imgBorder">
							<img class="img-circle img-responsive" alt="" src="imagenes/diseno-programacion-web.jpg">
						</div>
						<h3 style="color:#7FDAE2;">	DESARROLLO<br>A MEDIDA WEB Y APP
						</h3>
						<p>Déjanos ayudarte a crecer con el desarrollo de una solución a medida adaptada a tus necesidades. Conoce las ventajas de los entornos multidispositivo.</p>
					</a>
				</article>
			</div>
			<div class="col-md-3 col-sm-3 col-sm-offset-1 columnasTriple">
				<article class="boxIcon dinaAnim-invisible dinaAnim-animated dinaAnim-fadeInUp dinaAnim-visible" data-dinadelay="10" data-dinaanim="fadeInUp" style="animation-delay: 10ms; opacity: 1;">
					<a href="#">
						<div class="imgBorder">
							<img class="img-circle img-responsive" alt="" src="imagenes/marketing-seo.jpg">
						</div>
						<h3 style="color:#7FDAE2;">	MARKETING<br>DIGITAL
						</h3>
						<p>Ingenio, innovación y experiencia unidos en el uso de todos los canales disponibles. Contamos con más de 30 casos de éxito en estrategia de marketing digital</p>
					</a>
				</article>
			</div>
			<div class="col-md-3 col-sm-3 col-sm-offset-1 columnasTriple">
				<article class="boxIcon dinaAnim-invisible dinaAnim-animated dinaAnim-fadeInUp dinaAnim-visible" data-dinadelay="10" data-dinaanim="fadeInUp" style="animation-delay: 10ms; opacity: 1;">
					<a href="#">
						<div class="imgBorder">
							<img class="img-circle img-responsive" alt="" src="imagenes/desarrollo-app.jpg">
						</div>
						<h3 style="color:#7FDAE2;">	IDENTIDAD<br>DIGITAL Y DISEÑO
						</h3>
						<p>Confía en una de las agencias digitales con mas años de experiencia en el sector para cumplir tus objetivos. Muchos de los logos que recuerdas, nacieron aquí</p>
					</a>
				</article>
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