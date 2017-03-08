package PControlador;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import PModelo.ClConeccion;
import PModelo.ModelUsuario;

/**
 * Servlet implementation class ServletServicio
 */
//@WebServlet("Servicio")
public class ServletServicio extends HttpServlet {

	private HttpSession session;
	private Connection conex;
	private ModelUsuario mUsuario;
       

    public ServletServicio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		session = request.getSession();
		
		getServletContext().getRequestDispatcher("/servicios.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		System.err.println("Error en servicios");
	}
}
