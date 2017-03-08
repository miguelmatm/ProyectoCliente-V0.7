package PControlador;

import java.io.IOException;
import java.sql.Connection;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import PModelo.ClConeccion;
import PModelo.ModelUsuario;

/**
 * Servlet implementation class ServletRePassword
 */
@WebServlet("/ServletRePassword")
public class ServletRePassword extends HttpServlet {
	
	private	HttpSession session;
	private Connection conex;
	private ModelUsuario mUsuario;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/repassword.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = (String) request.getParameter("tipo");		
		
		conectarModeloUsuario(request);
		
		if (accion != null) {
			switch (accion) {
				case "nuevaPass":				
					
					nuevaPass(request, response);
					
					break;				
				
				default:
					break;
			}
		}	
	}

	private void nuevaPass(HttpServletRequest request, HttpServletResponse response){
	
		try {
		
			String newPass = request.getParameter("newPass");
			String email = request.getParameter("email");
			String token = request.getParameter("token");
			
			boolean guardado = false;
								
			mUsuario.buscarUsuario(email);
			if (mUsuario.cantidad() == 1) {
				String tokenBD = mUsuario.getToken();
				
				if (token.equals(tokenBD)) {
					String tokenDeSegurid = getCadenaAlfanumAleatoria(15);
					guardado = mUsuario.actualizarPass(newPass, email, tokenDeSegurid);
				}
			}			
		
			response.getWriter().write(new com.google.gson.Gson().toJson(guardado));
			
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
