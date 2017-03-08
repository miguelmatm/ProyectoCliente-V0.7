
package PControlador;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import PModelo.ModelUsuario;

public class ServletEnvioMail extends HttpServlet {

	private String aleatoria = "";
	private String ip = "ns3034756.ip-91-121-81.eu";
	private String asunto;
	private String cuerpo;
	private String nick;
	private String correo;
	private String token;
	
	private String accion;
	
	private HttpSession sesionBD;   
	private ModelUsuario mu;
	
	
	
	public ServletEnvioMail(String accion, String nick, String email, ModelUsuario mUsuario , String aleatoria, String asunto, String cuerpo, String token) {
		
		mu = mUsuario;
		this.accion = accion;
		this.nick = nick;
		correo = email;
		this.aleatoria = aleatoria; 
		this.asunto = asunto;
		this.cuerpo = cuerpo;
		this.token = token;
	}


	
	public boolean servic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		
		boolean esAgregado = false;
		
		try {
			// Propiedades de la conexión
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", "aspmx.l.google.com");
			props.setProperty("mail.smtp.starttls.enable", "false");
			props.setProperty("mail.smtp.port", "25");
			props.setProperty("mail.smtp.user", "miguelmatm3@gmail.com");
			props.setProperty("mail.smtp.auth", "true");
				
			
			
			// Preparamos la sesion pasandole props ... propediades de la
			// coneccion y la autentificacion del correo del que manda los
			// mensajes
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("miguelmatm3@gmail.com", "escotfacebook4");
				}
			});
			
			

			// Construimos el mensaje
			MimeMessage message = new MimeMessage(session);
			// la persona k manda el mensaje, Si el correo no es correcto el
			// mensaje suele entrar en span
			message.setFrom(new InternetAddress("miguelmatm3@gmail.com"));
			// Correo del que va a recibir el mensaje "la persona k tiene k
			// verificar"
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));

			
			String result = "";
			if (accion.equals("registro")) {
				String passw = (String) request.getParameter("pass");
				boolean validado = false;
				
			
				
				if(mu.buscarEmail(correo)){
					if (mu.cantidad() == 0) {
						esAgregado = mu.guardarUsuario(nick, correo, passw, aleatoria, validado, token);
						result = "ok";
						
						// Titulo del mensaje que queremos mandar
						message.setSubject(asunto);
						
						message.setText(cuerpo, "ISO-8859-1", "html");						

						// Lo enviamos a traves de smtp
						Transport t = session.getTransport("smtp");
						t.send(message); // Manda el mensaje
						
						// Cierre el flujo.
						t.close();
					}else{
						result = "El correo ya existe";
					}
				}else{
					result = "no se realizo bien la busqueda";
				}
				
			}else{
				esAgregado = true;
				// Titulo del mensaje que queremos mandar
				message.setSubject(asunto);
				
				message.setText(cuerpo, "ISO-8859-1", "html");						

				// Lo enviamos a traves de smtp
				Transport t = session.getTransport("smtp");
				t.send(message); // Manda el mensaje
				
				// Cierre el flujo.
				t.close();
			}

			System.out.println(result);
			
		
		
			

		} catch (Exception e) {
			e.printStackTrace();

		}
		
		
		return esAgregado;

	}

}