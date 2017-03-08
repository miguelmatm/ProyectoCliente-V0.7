package PControlador;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import PModelo.ClConeccion;
import PModelo.ModelUsuario;

/**
 * Servlet implementation class ServlertPerfil
 */


public class ServletPerfil extends HttpServlet {

	private	HttpSession session;
	private Connection conex;
	private ModelUsuario mUsuario;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPerfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/perfil.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = (String) request.getParameter("tipo");		
			
		conectarModeloUsuario(request);
		
		if (accion != null) {
			switch (accion) {
				case "perfilUsuario":				
					
					perfilUsuario(request, response);
					
					break;
					
				case "actualizarUsuario":
					
					actualizarUsuario(request, response);
					
					break;
					
				case "actualizarPassDsdPerfil":
					
					actualizarPassDsdPerfil(request, response);
					
					break;
				
				default:
					break;
			}
		}	
	}
	
	
	private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			String email = request.getParameter("email");
			String nombre = request.getParameter("nombre");
			boolean result = false;
			
			
			if (mUsuario.actualizarNombre(nombre, email)) {
				result = true;
			}
						
			response.getWriter().write(new com.google.gson.Gson().toJson(result));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	
	private void actualizarPassDsdPerfil(HttpServletRequest request, HttpServletResponse response){
		
		try {
	
			String newPassPerfil = request.getParameter("newPass");
			String email = request.getParameter("email");
			String token = request.getParameter("token");
			String pass = request.getParameter("oldPass");
			
			boolean guardado = false;
			
			mUsuario.buscarUsuario(email);
			if (mUsuario.cantidad() == 1) {
				String tokenBD = mUsuario.getToken();
				
				if (token.equals(tokenBD)) {
					if (mUsuario.getPassw().equals(pass)) {
						guardado = mUsuario.actualizarPass(newPassPerfil, email, tokenBD);
					}							
				}
			}		
		
			response.getWriter().write(new com.google.gson.Gson().toJson(guardado));
		
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	

	private void perfilUsuario(HttpServletRequest request, HttpServletResponse response){
	
		String email = request.getParameter("email");
		String token = request.getParameter("token");
		JsonObject datosDelUsuario = new JsonObject();	
		
		try {
			
			mUsuario.buscarUsuario(email);
			
			if (mUsuario.cantidad() == 1) {
				if (mUsuario.getToken().equals(token)) {
					String nombre = mUsuario.getNick();
					
					//TODO Ojo a esto ultima modificacion que no se como reacciona
					String idCliente = String.valueOf(mUsuario.getIdCliente());
					
					
					datosDelUsuario.addProperty("nombre", nombre);
					datosDelUsuario.addProperty("idCliente", idCliente);
	
				}
			}
			
			
			response.setCharacterEncoding("UTF-8");
		
			response.getWriter().write(new com.google.gson.Gson().toJson(datosDelUsuario));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	

}
