package PModelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelUsuario {


	
	private Connection conec;
	private ResultSet rs;
	private PreparedStatement ps;
	private String consulta;
	
	private String correo;
	private String passw;
	private String nick;
	private String aleatoria;
	private String token;
	private boolean validado;
	private int idCliente;
	


	public ModelUsuario(Connection c) {

		conec = c;
		rs = null;
		ps = null;

	}
	
	public boolean guardarUsuario(String nick2, String correo2, String passw2, String aleatoria, boolean validado, String token) {
		boolean registro;

		consulta = "INSERT INTO proyecto.cliente(nombre, correo, pass, aleatoria, validado, token) VALUES (?, ?, ?, ?, ?, ?);";

		try {

			ps = conec.prepareStatement(consulta);

			ps.setString(1, nick2);
			ps.setString(2, correo2);
			ps.setString(3, passw2);
			ps.setString(4, aleatoria);
			ps.setBoolean(5, validado);
			ps.setString(6, token);
			
			ps.executeUpdate();
		
			registro = true;
		

		} catch (SQLException e) {
			registro = false;
			e.printStackTrace();
			// si falla por duplicate no quiero q salte el mensaje de error
		}

		return registro;
		
	}

	public void buscarUsuario(String email) {
		
		consulta = "SELECT * FROM proyecto.cliente where correo = ?   ";

		try {

			ps = conec.prepareStatement(consulta, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ps.setString(1, email);
			
			rs = ps.executeQuery();		
			
			if (rs.next()) {
				nick = rs.getString("nombre");
				correo = rs.getString("correo");
				passw = rs.getString("pass");
				aleatoria = rs.getString("aleatoria");
				validado = rs.getBoolean("validado");
				token = rs.getString("token");
				idCliente = rs.getInt("id_client");
			}


		} catch (SQLException e) {
			e.printStackTrace();			
		}
		
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
	
//	Comprueba si existe el correo y si existe el correo devuelve el codigo aleatorio
	public synchronized String codigovalidacion(String correo) {
		
		String existecodigo = "";
		consulta = "SELECT aleatoria FROM proyecto.cliente where correo = ?";
		
		try {

			ps = conec.prepareStatement(consulta);

			ps.setString(1, correo);
			rs = ps.executeQuery();

			if (rs.next()) {
				existecodigo = rs.getString("aleatoria");
			}

		} catch (SQLException e) {
			System.out.println("Error al ejectar la consulta. " + e);

		}
		
		return existecodigo;
	}
	
//	Una ves comprobado que las claves aleatorias son iguales se pone el validado a true
	public synchronized boolean activarUsuario( String correo, boolean validado) {

		
		boolean retorno;
		

		consulta = "UPDATE proyecto.cliente SET validado=? WHERE correo =?;";
		

		try {
			
			ps = conec.prepareStatement(consulta);
	
			ps.setBoolean(1, validado);
			ps.setString(2, correo);

			ps.executeUpdate();	

			retorno = true;

		} catch (SQLException e) {
			e.printStackTrace();
			retorno = false;
		}

		return retorno;
	}
	
	public synchronized boolean asignarToken(String email, String token) {
		
		consulta = "UPDATE proyecto.cliente SET token=? WHERE correo =?;";		
		boolean tokenAsignado = false;

		try {
			
			ps = conec.prepareStatement(consulta);
	

			ps.setString(1, token);
			ps.setString(2, email);

			ps.executeUpdate();			

			tokenAsignado = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tokenAsignado;
	}
	
	public boolean actualizarPass(String newPass, String email, String newToken) {
		consulta = "UPDATE proyecto.cliente SET pass= ?, token= ? WHERE correo =?;";		
		boolean passActualizada = false;

		try {
			
			ps = conec.prepareStatement(consulta);
	

			ps.setString(1, newPass);
			ps.setString(2, newToken);
			ps.setString(3, email);

			ps.executeUpdate();			

			passActualizada = true;
		} catch (SQLException e) {
			e.printStackTrace();
			return passActualizada;
		}
		
		return passActualizada;
	}
	
	public boolean actualizarNombre(String nombre, String email) {
		consulta = "UPDATE proyecto.cliente SET nombre = ? WHERE correo =?;";		
		boolean passActualizada = false;

		try {
			
			ps = conec.prepareStatement(consulta);
	

			ps.setString(1, nombre);
			ps.setString(2, email);

			ps.executeUpdate();			

			passActualizada = true;
		} catch (SQLException e) {
			e.printStackTrace();
			return passActualizada;
		}
		
		return passActualizada;
	}
	
	public boolean buscarEmail(String email){
		consulta = "SELECT * FROM proyecto.cliente where correo =?;";	
		
		boolean existe = false;

		try {
			
			ps = conec.prepareStatement(consulta, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	
			ps.setString(1, email);

			rs = ps.executeQuery();				

			existe = true;
		} catch (SQLException e) {
			e.printStackTrace();
			return existe;
		}
		
		return existe;
	}

	public String getPassw() {
		return passw;
	}

	public void setPassw(String passw) {
		this.passw = passw;
	}

	public String getCorreo() {
		return correo;
	}

	public String getNick() {
		return nick;
	}

	public String getAleatoria() {
		return aleatoria;
	}
	
	public String getToken() {
		return token;
	}

	public boolean isValidado() {
		return validado;
	}

	public int getIdCliente() {
		return idCliente;
	}

}
