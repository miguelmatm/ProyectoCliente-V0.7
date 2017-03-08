package PModelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModeloProyectos {
	
	private Connection conec;
	private ResultSet rs;
	private PreparedStatement ps;
	private String consulta;
	
	private int idProyecto;
	private String nombre;
	private int idCliente;
	private byte [] foto;
	private String urlFoto;
	
	
	public ModeloProyectos(Connection c) {
		conec = c;
		rs = null;
		ps = null;
	}
	
	public boolean cargarProyectos(){
		
		consulta = "SELECT * FROM proyecto.proyecto";
		
		boolean resultado = false;
	
		try {

			ps = conec.prepareStatement(consulta, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			rs = ps.executeQuery();		
			
			if (rs.next()) {
				nombre = rs.getString("nombre");	
				idProyecto = rs.getInt("id_proyecto");
				idCliente = rs.getInt("id_cliente");
				foto = rs.getBytes("foto");		
				urlFoto = rs.getString("urlfoto");
			}
			
			resultado = true;
			
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		
		return resultado;
	}
	
	
	public boolean  obtenerSiguienteid(){
		
		boolean resultado = false;
		
	
		try {		
			
			if (rs.next()) {
				nombre = rs.getString("nombre");	
				idProyecto = rs.getInt("id_proyecto");
				idCliente = rs.getInt("id_cliente");
				foto = rs.getBytes("foto");	
				urlFoto = rs.getString("urlfoto");
				resultado = true;
			}
			
	
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		
		return resultado;
	}
	
	
	public int cantidad (){
		
		int cuantos = 0;
		
		try {
			
			rs.last();
			cuantos = rs.getRow();
			rs.first();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cuantos;
	}

	public int getIdProyecto() {
		return idProyecto;
	}

	public String getNombre() {
		return nombre;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public byte[] getFoto() {
		return foto;
	}
	
	public String getUrlFoto(){
		return urlFoto;
	}
	
}
