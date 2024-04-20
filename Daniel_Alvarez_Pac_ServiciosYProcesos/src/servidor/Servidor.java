package servidor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private final int PUERTO = 4321; //no va a cambiar
    private ServerSocket serverSocket;
    private Socket socket;
    private ServicioPass servicioPass;
    
    //Definimos el constructor
    public Servidor() throws IOException {
        serverSocket = new ServerSocket(PUERTO); //Definimos la conexion
        socket = new Socket(); //Iniciamos el cliente
    }
    //DataOutPut para enviar al cliente, DataInput para lo que entra del cliente, porque estamos en el server
    
    //Funcion para iniciar la conexion
    public void start() throws IOException {
        
    	//Vamos a aceptar los datos que llegaran del cliente
    	while (true) {
            System.out.println("Esperando la conexion del cliente al puerto" + PUERTO);
            socket = serverSocket.accept(); //guardamos la peticion que llegue al servidor en socket
            
            
            // El servidor se queda a la espera de recibir peticiones
            
            //Al recibir la peticion, iniciamos la conexion
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            
            //Enviamos mensaje al cliente
            salida.writeUTF("Hola, soy un SERVIDOR. ¿Como te llamas?");
            
			BufferedReader entrada = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
          
            String linea;
            
            linea = entrada.readLine();
            //Escribe el servidor el texto que ha recibido por consola
			System.out.println("Nombre del cliente:  "+linea + ".");
				
			//Manda el mensaje al cliente de que ha recibido la línea (en mayúsculas)
			//salida.writeBytes(linea);
			//linea = entrada.readLine();
				
			salida.writeUTF("Voy a pedirte unos requisitos para la contraseña que voy a generar.");
			salida.writeUTF("Cuantas minusculas quieres en la contraseña?");
			
			int numMinusculas = Integer.parseInt(entrada.readLine());
			System.out.print(numMinusculas);
			
			
			
			
			
			while (linea != null){	//Esto ira antes del close para que no se cierre
				

				}
            
			
			RequisitosPass requisitosPass = new RequisitosPass(0, 0, 0, 0);
			this.servicioPass = new ServicioPass(requisitosPass);
            socket.close();
        }
    }

    public void finalizarServer() throws IOException {
        serverSocket.close();
    }

}
