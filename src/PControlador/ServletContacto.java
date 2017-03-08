package PControlador;

import java.io.IOException;
import java.sql.Connection;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import PModelo.ClConeccion;
import PModelo.ModelUsuario;

/**
 * Servlet implementation class ServletContacto
 */
@WebServlet("/ServletContacto")
public class ServletContacto extends HttpServlet {

//	private HttpSession session;
	private Connection conex;
	private ModelUsuario mUsuario;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletContacto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		getServletContext().getRequestDispatcher("/contacto.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = (String) request.getParameter("tipo");		
			
		
		if (accion != null) {
			switch (accion) {
				case "enviarCorreoAlAdmin":
					
					enviarCorreoAlAdmin(request, response);
					
					break;
				
				default:
					break;
			}
		}			
	}
	
	private void enviarCorreoAlAdmin(HttpServletRequest request, HttpServletResponse response){
		
		String axion;
		String nombre = request.getParameter("nombre");
		String emailCliente = request.getParameter("email");
		String email = "miguelmatm3@gmail.com";
		String asunto = request.getParameter("asunto");
		String descripcion = "Correo : " +emailCliente + " " + request.getParameter("mensaje");
		boolean enviado = false;
		
		ServletEnvioMail enviarCorreo;

		try {
			
			if (!nombre.isEmpty() && !email.isEmpty() && !asunto.isEmpty() && !descripcion.isEmpty()) {
	
				axion = "notificarAlAdministrador";
				enviarCorreo = new ServletEnvioMail(axion, nombre, email, null, null, asunto, descripcion, null);
				
				enviado = enviarCorreo.servic(request, response);

			}
		
			response.getWriter().write(new com.google.gson.Gson().toJson(enviado));
		
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
