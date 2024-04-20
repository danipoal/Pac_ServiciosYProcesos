package tcpClient;

import java.io.*;
import java.net.*;

public class ClienteTCP {
	
	public static void main(String[] args) {
		
		String linea, mayusculasLinea, host;
		int port;
		host="localhost";
		port = 1236;
		
		try{
			Socket cliente = new Socket(host, port);
			
			//Comienza el intercambio de mensajes
			BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
			
			DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
			BufferedReader entrada = new BufferedReader(
					new InputStreamReader(cliente.getInputStream()));
			
			System.out.print("Envia tu mensaje:");
			
			System.out.flush();
			
			linea = consola.readLine();
			
			//Si el cliente escribe . la conexión finaliza
			//Mientras no lo haga, se va produciendo un intercambio de mensajes
			while (!linea.equals(".")){
				
				salida.writeBytes(linea+"\n");
				mayusculasLinea = entrada.readLine();
				System.out.println("Recibido: "+mayusculasLinea);
				System.out.print("Envia tu mensaje:");
				System.out.flush();
				linea = consola.readLine();
			
			}
		
			cliente.close();
		
		}catch(Exception e){e.printStackTrace();}
	
	}
}