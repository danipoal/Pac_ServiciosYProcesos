package tcpServer;
import java.io.*;
import java.net.*;

public class ServidorTCP {

	public static void main(String[] args) {
		String linea, mayusculasLinea;
		try{
			int port =1236;
			ServerSocket bienvenida = new ServerSocket(port);
			while (true){
				System.out.println("Esperando clientes que se conecten al puerto "+port);
				//Aquí dejamos al servidor a la espera de clientes
				Socket conecta = bienvenida.accept(); 

				System.out.println("Conectado a "+conecta.getInetAddress()+":"+conecta.getPort());
				
				//Empezamos el intercambio de mensajes
				//El servidor recibirá lo que el cliente vaya escribiendo por la consola
				
				BufferedReader entrada = new BufferedReader(
						new InputStreamReader(conecta.getInputStream()));
				DataOutputStream salida = new DataOutputStream(conecta.getOutputStream());
				
				//Defino qué es lo que está escribiendo el cliente por consola
				linea = entrada.readLine();
				while (linea != null){
					//Escribe el servidor el texto que ha recibido por consola
					System.out.println("Recibido: "+linea);
					//Reescribo la línea en mayusculas para diferenciarla de la que se escribe por pantalla
					//Manda el mensaje al cliente de que ha recibido la línea (en mayúsculas)
					mayusculasLinea = linea.toUpperCase()+"\n";
					salida.writeBytes(mayusculasLinea);
					linea = entrada.readLine();
					}
				}
			}catch(Exception e){e.printStackTrace();}
	}
}
