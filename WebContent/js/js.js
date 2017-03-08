var enlace;
var proyectosMostrados;

$(document).ready(function(){


    var pgs = String(window.location.href).split('/');
    enlace = pgs[pgs.length - 1];
    if (!enlace) {
        enlace = "index.html";
    }
    var pg = enlace.split('?')[0];

    /*variables para el menu scrollable*/
    var flag = false;
    var scroll;
    
    
    $.ajax({
        url: 'includes/cabecera.html',
        type: 'GET',
        async: false,
        success: function(respuesta){
            $("#cabecera").html(respuesta);
        }
    });

    $.ajax({
        url: 'includes/pie.html',
        type: 'GET',
        async: false,
        success: function(respuesta){
            $("#pie").html(respuesta);
        }
    });

    $.ajax({
        url: 'includes/login.html',
        type: 'GET',
        async: false,
        success: function(respuesta){
            $("#login").html(respuesta);
        }
    });

    $.ajax({
        url: 'includes/modal.html',
        type: 'GET',
        async: false,
        success: function(respuesta){
            $("#modall").html(respuesta);
        }
    });

    if ("verdad" === getCookie("logeado")) {
        logeado();
    }


    function logeado(){
         var cadena = "";
            cadena += "<li class='col-sm-2'><a href='Cliente' class='btn btn-1'>INICIO</a></li>";
            cadena += "<li class='col-sm-2'><a href='Servicios' class='btn btn-1'>SERVICIOS</a></li>";
            cadena += "<li class='col-sm-2'><a href='Proyectos' class='btn btn-1'>PROYECTOS</a></li>";
            cadena += "<li class='col-sm-2'><a href='Contacto' class='btn btn-1'>CONTACTO</a></li>";
            cadena += "<li class='col-sm-2'><a href='Perfil' class='btn btn-1'>PERFIL</a></li>";
            cadena += "<li class='col-sm-2'><a id='cerrarsesion' href='#' class='btn btn-1'>CERRAR SESION</a></li>";

            $('#ali').html(cadena);
            $('#alid').html(cadena);
    }


    $(document).on('click', '#botonLogin', function(event) {
        logeadoRegistro();
    });
    
    


    function logeadoRegistro(){

        var cEmail = $('#login_username').val();
        var passUsuario =  pasarAmd5('#login_password');

        if (!esVacioValor(cEmail)) {
            if (!esVacioValor(passUsuario)) {

                $.ajax({
                    url:"Cliente",
                    type: "POST",
                    async:true,
                    dataType: "json",
                    data:{tipo: "logearUsuario", pass: passUsuario, email: cEmail},
                    success: function(respuesta){  

                    	if(respuesta['error'] == "false"){
                    		var token = respuesta['token'];
                            var cadena = "";
                            cadena += "<li class='col-sm-2'><a href='Cliente' class='btn btn-1'>INICIO</a></li>";
                            cadena += "<li class='col-sm-2'><a href='Servicios' class='btn btn-1'>SERVICIOS</a></li>";
                            cadena += "<li class='col-sm-2'><a href='Proyectos' class='btn btn-1'>PROYECTOS</a></li>";
                            cadena += "<li class='col-sm-2'><a href='Contacto' class='btn btn-1'>CONTACTO</a></li>";
                            cadena += "<li class='col-sm-2'><a href='Perfil' class='btn btn-1'>PERFIL</a></li>";
                            cadena += "<li class='col-sm-2'><a id='cerrarsesion' href='#' class='btn btn-1'>CERRAR SESION</a></li>";

                            $('#ali').html(cadena);
                            $('#alid').html(cadena);

                            var correoo = $('#login_username').val();

                            setCookie("logeado", "verdad");
//                            setCookie("pass", $('#login_password').val());
                            setCookie("email", correoo);
                            setCookie("token", token);

                            var cadena = "<i class='glyphicon glyphicon-thumbs-up'></i> Bienvenido";
                            $('#modalTitulo').html(cadena);
                            var cadenad = "<p>Bienvenido : "+correoo+"</p>";
                            $('#modalTexto').html(cadenad);
                            $('#success').modal();
                    	} else if (respuesta['motivo'] == "Email o Contraseña Incorrecta") {
	                        var cadena = "<i class='glyphicon glyphicon-thumbs-up'></i> Email o Contraseña Incorrecta";
	                        $('#modalTitulo').html(cadena);
	                        var cadenad = "<p>Email o Contraseña Incorrecta</p>";
	                        $('#modalTexto').html(cadenad);
	                        $('#success').modal();

	                    } else if (respuesta['motivo'] == "Rellene Los Campos") {
	                        var cadena = "<i class='glyphicon glyphicon-thumbs-up'></i> Rellene Los Campos";
	                        $('#modalTitulo').html(cadena);
	                        var cadenad = "<p>Rellene Los Campos</p>";
	                        $('#modalTexto').html(cadenad);
	                        $('#success').modal();       
	                    } else if (respuesta['motivo'] == "Usuario No Validado") {
	                    	var cadena = "<i class='glyphicon glyphicon-thumbs-up'></i> Usuario No Validado";
	                        $('#modalTitulo').html(cadena);
	                        var cadenad = "<p>Confirme el correo que le fue enviado</p>";
	                        $('#modalTexto').html(cadenad);
	                        $('#success').modal();     
						}
                        
                    },
                    error: function(jqXHR, status, error) {
                        alert("Error detectado: " + status + "\nExcepcion: " + error);
                    }
                });                
            }
        }
    }

    $(document).on('click', '#cerrarsesion', function(event) {
        var cadena = "";
            cadena += "<li class='col-sm-2'><a href='Cliente' class='btn btn-1'>INICIO</a></li>";
            cadena += "<li class='col-sm-2'><a href='Servicios' class='btn btn-1'>SERVICIOS</a></li>";
            cadena += "<li class='col-sm-2'><a href='Proyectos' class='btn btn-1'>PROYECTOS</a></li>";
            cadena += "<li class='col-sm-2'><a href='Contacto' class='btn btn-1'>CONTACTO</a></li>";
            cadena += "<li class='col-sm-2' data-toggle='modal' data-target='#login-modal'><a href='#' class='btn btn-1'>LOGIN</a></li>";

            $('#ali').html(cadena);
            $('#alid').html(cadena);

            setCookie("logeado", "false");
    });
    
    
    $(document).on('click', '#btnactualizar', function(event) {
    	var nombre = $('#txtnombre').val();
    	var email = getCookie("email");
    	
    	if (!esVacio($('#txtnombre')) && email != null) {
    		$.ajax({
                url:"Perfil",
                type: "POST",
                async:true,
                dataType: "json",
                data:{tipo: "actualizarUsuario", nombre: nombre, email: email},
                success: function(respuesta){  
                	if (respuesta) {
                        var cadena = "<i class='glyphicon glyphicon-thumbs-up'></i> Actualizado con exito";
                        $('#modalTitulo').html(cadena);
                        var cadenad = "<p>Actualizado el nombre : "+nombre+"</p>";
                        $('#modalTexto').html(cadenad);
                        $('#success').modal();
					}else{
			            var cadena = "<i class='glyphicon glyphicon-thumbs-up'></i> No Se Actualizo";
			            $('#modalTitulo').html(cadena);
			            var cadenad = "<p>Error.</p>";
			            $('#modalTexto').html(cadenad);
			            $('#success').modal();
					}
                	
                    
                },
                error: function(jqXHR, status, error) {
                    alert("Error detectado: " + status + "\nExcepcion: " + error);
                }
            });     
		}    	 
    });
    
    
    $(document).on('click', '#btnContactUs', function(event) {
    	var nombre = $('#name').val();
    	var email = $('#email').val();
    	var asunto = $('#subject').val();
    	var mensaje = $('#message').val();
    	
    	if (!esVacio($('#name'))) {
    		if (!esVacio($('#email'))) {
    			if (!esVacio($('#subject'))) {
    				if (!esVacio($('#message'))) {
    					$.ajax({
    		                url:"Contacto",
    		                type: "POST",
    		                async:true,
    		                dataType: "json",
    		                data:{tipo: "enviarCorreoAlAdmin", nombre: nombre, email: email, asunto : asunto, mensaje : mensaje},
    		                success: function(respuesta){      		                	   	          	
    		                    if (respuesta) {
    		                    	$('#name').val("");
    		                    	$('#email').val("");
    		                    	$('#subject').val("");
    		                    	$('#message').val("");
    		                    	mostrarMensaje("Mensaje Enviado", "El mensaje fue enviado");
								}else{
									mostrarMensaje("Error Mensaje", "El mensaje no pudo ser enviado");
								}
    		                },
    		                error: function(jqXHR, status, error) {
    		                    alert("Error detectado: " + status + "\nExcepcion: " + error);
    		                }
    		            });    
        			}else{
        				mostrarMensaje("Mensaje", "Escribanos una breve descripción");
        			}
    			}else{
    				mostrarMensaje("Asunto" , "Seleccione un asunto");
    			}
			}else{
				mostrarMensaje("Email", "Es importante que nos introdusca un correo para contactar con usted");
			}    		 
		}else{
			mostrarMensaje("Nombre", "Diganos su nombre para poder dirigirnos a usted.");
		}    	 
    });
    
    
    
    
    $(document).on('click', '#btnguardarPass', function(event) {
    	var pass = $('#txtpass').val();
    	var email = getCookie("email");
    	var token = getCookie("token");
    	var newPass = $('#txtnewpass').val();
    	var reNewPass = $('#txtrepass').val();
    		
    		
    	
    	if (!esVacio($('#txtpass')) && !esVacio($('#txtnewpass')) && !esVacio($('#txtrepass'))) {
    		if (email != null && token != null) {
    			if (newPass === reNewPass) {
    				pass = pasarAmd5($('#txtpass'));
    				newPass = pasarAmd5($('#txtnewpass'));
    				
    				$.ajax({
    	                url:"Perfil",
    	                type: "POST",
    	                async:true,
    	                dataType: "json",
    	                data:{tipo: "actualizarPassDsdPerfil", oldPass : pass, newPass: newPass, email: email, token : token},
    	                success: function(respuesta){        	
    						if (respuesta) {
    				            var cadena = "<i class='glyphicon glyphicon-thumbs-up'></i> Guardado Correctamente";
    				            $('#modalTitulo').html(cadena);
    				            var cadenad = "<p>Guardado.</p>";
    				            $('#modalTexto').html(cadenad);
    				            $('#success').modal();
    				            
    				        	$('#txtpass').val("");    			
    				        	$('#txtnewpass').val("");
    				        	$('#txtrepass').val("");
    				            
							}else{
					            var cadena = "<i class='glyphicon glyphicon-thumbs-up'></i> No Se Actualizo";
					            $('#modalTitulo').html(cadena);
					            var cadenad = "<p>Error.</p>";
					            $('#modalTexto').html(cadenad);
					            $('#success').modal();
							}
    	                	
    	                    
    	                },
    	                error: function(jqXHR, status, error) {
    	                    alert("Error detectado: " + status + "\nExcepcion: " + error);
    	                }
    	            });     
				}
			}else{
				alert("hay un error en los datos personales guardados, Intentelo mas tarde");
			}
    	}else{
            var cadena = "<i class='glyphicon glyphicon-thumbs-up'></i> Error";
            $('#modalTitulo').html(cadena);
            var cadenad = "<p>Introduzca todos los campos.</p>";
            $('#modalTexto').html(cadenad);
            $('#success').modal();
    	}   	 
    });
    

    $(window).scroll(function() {
        scroll = $(window).scrollTop();

        if (scroll > 200) {
            if (!flag) {
                flag = true;
                $("#menu").css({"display":"inline"});
            }
        }else{
            if (flag) {
                flag = false;
                 $("#menu").css({"display":"none"});
            }
        }

        for (var int = 0; int < proyectosMostrados; int++) {
            if (scroll > ($("#producto"+int).position().top - ($("#producto"+int).height()))) {
            	$("#producto"+int).css({"right":"0%"});
           }
		}

        if(scroll > 50){
        	$(".columnasTriple").css({"margin-top":"1em","visibility":"visible"});
        }
    });





	$(function(){
	 
	    $(document).on( 'scroll', function(){
	 
	    	if ($(window).scrollTop() > 100) {
				$('.scroll-top-wrapper').addClass('show');
			} else {
				$('.scroll-top-wrapper').removeClass('show');
			}
		});
	 
		$('.scroll-top-wrapper').on('click', scrollToTop);
	});
	 
	function scrollToTop() {
		verticalOffset = typeof(verticalOffset) != 'undefined' ? verticalOffset : 0;
		element = $('body');
		offset = element.offset();
		offsetTop = offset.top;
		$('html, body').animate({scrollTop: offsetTop}, 500, 'linear');
	}

});



   
$(function() {
    
    var $formLogin = $('#login-form');
    var $formLost = $('#lost-form');
    var $formRegister = $('#register-form');
    var $divForms = $('#div-forms');
    var $modalAnimateTime = 300;
    var $msgAnimateTime = 150;
    var $msgShowTime = 2000;

    $("form").submit(function () {
        switch(this.id) {
            case "login-form":
                var $lg_username=$('#login_username').val();
                var $lg_password=$('#login_password').val();
                if ($lg_username == "ERROR") {
                    msgChange($('#div-login-msg'), $('#icon-login-msg'), $('#text-login-msg'), "error", "glyphicon-remove", "Login error");
                } else {
                    msgChange($('#div-login-msg'), $('#icon-login-msg'), $('#text-login-msg'), "success", "glyphicon-ok", "Login OK");
                }
                return false;
                break;
            case "lost-form":
                var $ls_email=$('#lost_email').val();

                $.ajax({
                    url:"Cliente",
                    type: "POST",
                    async:true,
                    data:{tipo: "recuperarPass", email: $ls_email},
                    success: function(respuesta){  
                        if (respuesta == "false") {
                            msgChange($('#div-lost-msg'), $('#icon-lost-msg'), $('#text-lost-msg'), "error", "glyphicon-remove", "Envio Email Erroneo");
                        } else {
                            msgChange($('#div-lost-msg'), $('#icon-lost-msg'), $('#text-lost-msg'), "success", "glyphicon-ok", "Envio Email OK");
//                            close();
                        }
                    },
                    error: function(jqXHR, status, error) {
                        alert("Error detectado: " + status + "\nExcepcion: " + error);
                    }
                }); 
                
                return false;
                break;
                
            case "register-form":
                var $rg_username=$('#register_username').val();
                var $rg_email=$('#register_email').val();
                var $rg_password=$('#register_password').val();
                if ($rg_username == "ERROR") {
                    msgChange($('#div-register-msg'), $('#icon-register-msg'), $('#text-register-msg'), "error", "glyphicon-remove", "Register error");
                } else {
                    msgChange($('#div-register-msg'), $('#icon-register-msg'), $('#text-register-msg'), "success", "glyphicon-ok", "Register OK");
                }
                return false;
                break;
            default:
                return false;
        }
        return false;
    });
    
    $('#login_register_btn').click( function () { modalAnimate($formLogin, $formRegister) });
    $('#register_login_btn').click( function () { modalAnimate($formRegister, $formLogin); });
    $('#login_lost_btn').click( function () { modalAnimate($formLogin, $formLost); });
    $('#lost_login_btn').click( function () { modalAnimate($formLost, $formLogin); });
    $('#lost_register_btn').click( function () { modalAnimate($formLost, $formRegister); });
    $('#register_lost_btn').click( function () { modalAnimate($formRegister, $formLost); });
    
    function modalAnimate ($oldForm, $newForm) {
        var $oldH = $oldForm.height();
        var $newH = $newForm.height();
        $divForms.css("height",$oldH);
        $oldForm.fadeToggle($modalAnimateTime, function(){
            $divForms.animate({height: $newH}, $modalAnimateTime, function(){
                $newForm.fadeToggle($modalAnimateTime);
            });
        });
    }

    $(document).on('click', '#botonRegistro', function(event) {
    event.preventDefault();

    var corre = $('#register_email').val();
    var pass = pasarAmd5('#register_password');
    
    var nick = $('#register_username').val();

    $.ajax({
        url:"Cliente",
        type: "POST",
        async:true,
        dataType: "json",
        data:{tipo: "guardarUsuario", correo: corre, pass: pass, nick: nick},
        success: function(respuesta){       
        	if(respuesta["resultado"]){
        		setCookie("token", respuesta["token"]);
        		mostrarMensaje("Exito al Registrar", "Usuario Registrado");
        	}else{
        		mostrarMensaje("Error al guardar", "Error al registrar el usuario, posiblemente el correo ya exista");
        	}
        },
        error: function(jqXHR, status, error) {
            alert("Error detectado: " + status + "\nExcepcion: " + error);
        }
    });


    modalAnimate($formRegister, $formLogin);
   
});
    
    function msgFade ($msgId, $msgText) {
        $msgId.fadeOut($msgAnimateTime, function() {
            $(this).text($msgText).fadeIn($msgAnimateTime);
        });
    }
    
    function msgChange($divTag, $iconTag, $textTag, $divClass, $iconClass, $msgText) {
        var $msgOld = $divTag.text();
        msgFade($textTag, $msgText);
        $divTag.addClass($divClass);
        $iconTag.removeClass("glyphicon-chevron-right");
        $iconTag.addClass($iconClass + " " + $divClass);
        setTimeout(function() {
            msgFade($textTag, $msgOld);
            $divTag.removeClass($divClass);
            $iconTag.addClass("glyphicon-chevron-right");
            $iconTag.removeClass($iconClass + " " + $divClass);
  		}, $msgShowTime);
    }
});


$(document).on('click', '#btnactualizar', function(event) {
    event.preventDefault();

    var textoNombre = $('#txtnombre').val();
    var textoEmail = $('#txtemail').val();
    var textoTelefono = $('#txttelefono').val();

    if (!esVacio($('#txtnombre'))) {
        if (!esVacio($('#txtemail'))) {
            if (!esVacio($('#txttelefono'))) {
                setCookie("nombre", textoNombre);
                setCookie("email", textoEmail);
                setCookie("telefono", textoTelefono);
            }
        }else{
            var cadena = "<i class='glyphicon glyphicon-thumbs-up'></i> Correo vacio";
            $('#modalTitulo').html(cadena);
            var cadenad = "<p>Introduzca una contraseña</p>";
            $('#modalTexto').html(cadenad);
            $('#success').modal();
        }
    }
    /* meter los tres valores en cookies*/
});



$(document).on('click', '#btnCambiarPass', function(event) {
    event.preventDefault();

    var newPass = $('#txtnewpass').val();
    var reNewPass = $('#txtrepass').val();
    
    var parametros = enlace.split('?')[1];
    
    var GET = parametros.split('&');
    var get = {};
    
    var email;
    var token;

    // recorremos todo el array de valores
    for(var i = 0, l = GET.length; i < l; i++){
        var tmp = GET[i].split('=');
        get[tmp[0]] = unescape(decodeURI(tmp[1]));
    }
    
    for(var clave in get){
        if (clave == "email") {
        	email = get[clave];
		}else if( clave == "token"){
			token = get[clave];
		}        
    }

    if (newPass == reNewPass && email != null && token != null && !esVacioValor(newPass) && !esVacioValor(reNewPass)) {
		newPass = pasarAmd5('#txtnewpass');
		 	$.ajax({
		        url:"RePassword",
		        type: "POST",
		        async:true,
		        dataType: "json",
		        data:{tipo: "nuevaPass", newPass: newPass, email : email, token : token},
		        success: function(respuesta){                 
		            if (respuesta) {
						window.location="http://ns3034756.ip-91-121-81.eu:8080/ProyectoCliente/Cliente";
					}else{
						alert("Error no se actualizo");
					}
		        },
		        error: function(jqXHR, status, error) {
		            alert("Error detectado: " + status + "\nExcepcion: " + error);
		        }
		    });
		
	}else{
		alert("El valor de las contraseñas deben coincidir y no ser vacios");
	}
    /* meter los tres valores en cookies*/
});


function getCookie(nombre) {
    var name = nombre + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) == 0) return c.substring(name.length,c.length);
    }
    return "";
}


 function setCookie(nombre, valor) {
    document.cookie = nombre +"="+ valor +";";
}


function esVacio(e){
    if (e.val() == ""  || e.val() == " " || e.val() == null) {
        return true;
    }else{
        return false;
    }
}

function esVacioValor(e){
    if (e == ""  || e == " " || e == null) {
        return true;
    }else{
        return false;
    }
}

function pasarAmd5(e){    
    return md5($(e).val());
}

function cargarPerfil(){
	var correo = getCookie("email");
	var token = getCookie("token");
	
    $.ajax({
        url:"Perfil",
        type: "POST",
        async:true,
        dataType: "json",
        data:{tipo: "perfilUsuario", email: correo, token: token},
        success: function(respuesta){  
 
        	var nombre = respuesta['nombre'];
        	var idCliente = respuesta['idCliente'];
        	
        	$('#txtnombre').val(nombre);
        	$('#txtemail').val(correo);
        	$('#txtIdCliente').val(idCliente);
        },
        error: function(jqXHR, status, error) {
            alert("Error detectado: " + status + "\nExcepcion: " + error);
        }
    }); 
}

function cargarProyectos(){
	
	$.ajax({
        url:"Proyectos",
        type: "POST",
        async:true,
        dataType: "json",
        data:{tipo: "cargarProyectos"},
        success: function(respuesta){ 
        	
        	var cadena= "";  
        	
        	proyectosMostrados = respuesta["proyectos"].length;
        	
        	for (var int = 0; int < respuesta["proyectos"].length; int++) {
				var array_element = respuesta["proyectos"][int];

	            cadena += "<div id='producto"+int+"' class='productoss row'>";
	            cadena +=    "<div class='col-sm-5 col-xs-12'>";
	            cadena +=        "<img class='img-responsive' alt='Error al mostrar  la Imagen' src="+array_element["foto"]+">";
	            cadena +=    "</div>";
	            cadena +=    "<div class='col-sm-5 col-xs-12'>";
	            cadena +=       "<span>Nº de identificacion del Cliente : </span></br>";
	            cadena +=       "<p>"+array_element["idCliente"]+"</p></br>";
	            cadena +=       "<span>Nº de identificacion del proyecto : </span></br>";
	            cadena +=       "<p>"+array_element["idProyecto"]+"</p></br>";
	            cadena +=       "<span>Nombre : </span></br>";
	            cadena +=       "<p>"+array_element["nombre"]+"</p></br>";
	            cadena +=    "</div>";
	            cadena += "</div>";
			}
        	
	        $('#productos').html(cadena);
        },
        error: function(jqXHR, status, error) {
            alert("Error detectado: " + status + "\nExcepcion: " + error);
        }
    }); 
	
	

	
    
}

function mostrarMensaje(titulo, cuerpo){
    var cadena = "<i class='glyphicon glyphicon-thumbs-up'></i>"+titulo;
    $('#modalTitulo').html(cadena);
    var cadenad = "<p>"+cuerpo+"</p>";
    $('#modalTexto').html(cadenad);
    $('#success').modal();
}


