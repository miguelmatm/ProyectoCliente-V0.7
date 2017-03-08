package PControlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import PModelo.ClConeccion;
import PModelo.ModelUsuario;
import PModelo.ModeloProyectos;

public class ServletIndex extends HttpServlet{
	
	private	HttpSession session;
	private Connection conex;
	private ModelUsuario mUsuario;

//	http://localhost:8080/Cliente
//	http://ns3034756.ip-91-121-81.eu:8080/ProyectoCliente/Proyectos
//	TODO Comprobar todo su funcionamiento desde 0 sin ningun usuario registrado, necesito comprobar el registro de un usuario nuevo.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {	
	
		session = request.getSession();
			
		try {
			
			getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("entra en post");
		
		String accion = (String) request.getParameter("tipo");
		String email ;
		String axion;
		String asunto;
		String cuerpo;
		String token;
		
		ServletEnvioMail enviarCorreo;
		
		System.out.println("MUsuario " + request.getSession().getAttribute("mUsuario"));
		
		
		if (request.getSession().getAttribute("mUsuario") == null) {
			
			System.out.println("Entra para crear un nuevo mUsuario");
			
			conectarModeloUsuario(request);
		}
		
		
		if (accion != null) {
			switch (accion) {
				case "guardarUsuario":
					JsonObject json = new JsonObject();
					String nick = (String) request.getParameter("nick");
					axion = "registro";
					email = (String) request.getParameter("correo");
				 
					String aleatoria = getCadenaAlfanumAleatoria(10);
					token = getCadenaAlfanumAleatoria(15);
					
					asunto = "Correo de verificación, por favor no responder";
					cuerpo = "Este es un correo de verificacion \n"
							+ "Gracias por registrarse señor/a " + nick + " "
							+ " \n" + "Por favor haga click en el siguiente enlace\n"
							+ "para seguir con la verificacion de sus datos  \n" 
							+ " <a href='http://ns3034756.ip-91-121-81.eu:8080/ProyectoCliente/ProyectoActivarCuenta?usuario="+ email + "&aleatorio="
							+ aleatoria +"'>Enlace</a>";
					
					
					enviarCorreo = new ServletEnvioMail(axion, nick, email, (ModelUsuario) request.getSession().getAttribute("mUsuario"), aleatoria, asunto, cuerpo, token);
					
					boolean guardad = enviarCorreo.servic(request, response);
					
					json.addProperty("resultado", guardad);
					json.addProperty("token", token);
					
					response.getWriter().write(new com.google.gson.Gson().toJson(json));
					 
					break;
					
				case "logearUsuario":					
					String passw = (String) request.getParameter("pass");
					email  = (String) request.getParameter("email");
					JsonObject existe = new JsonObject();			  
					
					if (passw != null && email != null && !passw.isEmpty() && !email.isEmpty()) {
						mUsuario.buscarUsuario(email);
						token = mUsuario.getToken();
						if (mUsuario.isValidado()) {
							if (mUsuario.getPassw() != null) {
								if (mUsuario.cantidad() == 1) {
									if (mUsuario.getPassw().equals(passw)) {
										existe.addProperty("error", "false");
										existe.addProperty("token", token);										
									}else{
										existe.addProperty("error", "true");
										existe.addProperty("motivo", "Email o Contraseña Incorrecta");										
									}
								}
							}		
						}else{
							existe.addProperty("error", "true");
							existe.addProperty("motivo", "Usuario No Validado");
						}
					}else{
						existe.addProperty("error", "true");
						existe.addProperty("motivo", "Rellene Los Campos");
					}
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(new com.google.gson.Gson().toJson(existe));					
					
					break;
					
				case "recuperarPass":
					
					email  = (String) request.getParameter("email");
					boolean resultado = false;
					
					mUsuario.buscarUsuario(email);
					
					if (mUsuario.cantidad() == 1) {
						
						token = getCadenaAlfanumAleatoria(15);
						 if (mUsuario.asignarToken(email, token)){							  
						 
							axion = "recuperarPass";
							asunto = "Recuperrar Contraseña, por favor no responder";
							cuerpo = "Este es un correo de verificacion \n"
									+ "Para recuperar su contraseña " + email + " "
									+ " \n" + "Por favor haga click en el siguiente enlace\n"
									+ " <a href='http://ns3034756.ip-91-121-81.eu:8080/ProyectoCliente/RePassword?email="+ email + "&token="
									+ token +"'>Enlace</a>";

							enviarCorreo = new ServletEnvioMail(axion, null, email, (ModelUsuario) request.getSession().getAttribute("mUsuario"), token, asunto, cuerpo, token);
								 
							 resultado = enviarCorreo.servic(request, response);	
						 }
					}
					response.getWriter().write(new com.google.gson.Gson().toJson(resultado));	
					break;
					
				default:
					break;
			}
			
		}
	}
	

	
	private void conectarModeloUsuario(HttpServletRequest request) {
		
		if (mUsuario == null) {
			
			if (session == null) {
				
				session = request.getSession();

			}
			
			if (session.getAttribute("mUsuario") == null) {
				if (conex == null) {
					
						conex = (Connection) session.getAttribute("conex");
	
				}
				
				mUsuario = new ModelUsuario(conex);
				session.setAttribute("mUsuario", mUsuario);	
				
			}else{
				
				mUsuario = (ModelUsuario) session.getAttribute("mUsuario");
				
			}					
					
		}	
	}
	

	public static String getCadenaAlfanumAleatoria(int longitud) {
		
		String cadenaAleatoria = "";
		long milis = new java.util.GregorianCalendar().getTimeInMillis();
		Random r = new Random(milis);
		int i = 0;
		while (i < longitud) {
			char c = (char) r.nextInt(255);
			if ((c >= '0' && c <= 9) || (c >= 'A' && c <= 'Z')) {
				cadenaAleatoria += c;
				i++;
			}
		}
		return cadenaAleatoria;
	}	
	
		
	
	
}