package PControlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import PModelo.ClConeccion;
import PModelo.ModelUsuario;
 

public class ServletActivacionCuenta extends HttpServlet {
 	
 	private ClConeccion initBD;    
 	private	ModelUsuario mu;
 	
    public ServletActivacionCuenta() {
        super();		
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
    	mu = new ModelUsuario((Connection)request.getSession().getAttribute("conex"));
    	PrintWriter out = response.getWriter();
    	
    	try {
		  
		    String usucorreo = request.getParameter("usuario");
		    String ale = request.getParameter("aleatorio");   
		    response.setContentType("text/html;charset=UTF-8");
		    	out.println("");
		    	out.println("");
		    	out.println("");
		    if (ale != null && usucorreo != null && !ale.isEmpty() && !usucorreo.isEmpty()) {
		    	 if(ale.equals(mu.codigovalidacion(usucorreo))){ 
		        	boolean activacion=true;
		        	mu.activarUsuario(usucorreo, activacion);
		        	
		        	getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		      	
		        }else{
			        out.println("<h3>ERROR!</h3>");
			        out.println("<b>No existe usuario</b>");
			        out.println("");
			        out.println("");		        
		        }
			}else{
		        out.println("<h3>ERROR!</h3>");
		        out.println("<b>Parametros Erroneos </b>");
		        out.println("");
		        out.println("");	
			}
		   
	        
		} catch (Exception e) {	 
			System.out.println("error"+e);	   
		}finally{
			out.close();
		}   
    }
      
}
