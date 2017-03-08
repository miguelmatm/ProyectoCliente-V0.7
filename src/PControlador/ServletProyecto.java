package PControlador;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import PModelo.ClConeccion;
import PModelo.ModelUsuario;
import PModelo.ModeloProyectos;

/**
 * Servlet implementation class ServletProyecto
 */
@WebServlet("/ServletProyecto")
public class ServletProyecto extends HttpServlet {
	
	private HttpSession session;
	private Connection conex;
	private ModelUsuario mUsuario;
	
	private ModeloProyectos mProyectos;

	
    public ServletProyecto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		session = request.getSession();				
		
		getServletContext().getRequestDispatcher("/proyectos.jsp").forward(request, response);	
		
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = (String) request.getParameter("tipo");		
			
		
		if (accion != null) {
			switch (accion) {
				case "cargarProyectos":
					
					cargarProyectos(request, response);
					
					break;
				
				default:
					break;
			}
		}		
	}
	
	
	
	private void cargarProyectos(HttpServletRequest request, HttpServletResponse response){
		
		try {
			
			conectarModeloProyecto(request);
			
			mProyectos.cargarProyectos();
			
			int i = mProyectos.cantidad();
			
			JsonObject json = new JsonObject();
			JsonArray jsonArray = new JsonArray();			
			
			if (i > 0) {
	
				JsonObject jsonElemento = new JsonObject();
				String foto = mProyectos.getUrlFoto();	
				
				jsonElemento.addProperty("idProyecto", mProyectos.getIdProyecto());
				jsonElemento.addProperty("idCliente", mProyectos.getIdCliente());
				jsonElemento.addProperty("nombre", mProyectos.getNombre());
				jsonElemento.addProperty("foto", foto);
				
				jsonArray.add(jsonElemento);				
				
				for (int x = 0; x < i-1 ; x++) {
					
					jsonElemento = new JsonObject();
					
					if(mProyectos.obtenerSiguienteid()){
	
						foto = mProyectos.getUrlFoto();								
						
						jsonElemento.addProperty("idProyecto", mProyectos.getIdProyecto());
						jsonElemento.addProperty("idCliente", mProyectos.getIdCliente());
						jsonElemento.addProperty("nombre", mProyectos.getNombre());
						jsonElemento.addProperty("foto", foto);
						
					}
	
					jsonArray.add(jsonElemento);
					
				}	
				System.out.println("Aqui va la bomba "+jsonArray);
				json.add("proyectos", jsonArray);
			}
			response.setCharacterEncoding("UTF-8");
		
			response.getWriter().write(new com.google.gson.Gson().toJson(json));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	private void conectarModeloProyecto(HttpServletRequest request){
		
		if (mProyectos == null) {
			
			if (session == null) {
				
				session = request.getSession();
				
			}
			
			if (session.getAttribute("mProyectos") == null) {
				
				if (conex == null) {
					
						conex = (Connection) session.getAttribute("conex");
					
				}
				
				mProyectos = new ModeloProyectos(conex);
				session.setAttribute("mProyectos", mProyectos);	
				
			}else{
				
				mProyectos = (ModeloProyectos) session.getAttribute("mProyectos");
				
			}					
		}		
	}
}
