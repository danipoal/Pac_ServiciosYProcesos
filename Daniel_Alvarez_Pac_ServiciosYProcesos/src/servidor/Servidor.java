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
			
			//MINUS
			salida.writeUTF("Cuantas minusculas quieres en la contraseña?");
			
			int numMinusculas = Integer.parseInt(entrada.readLine());
			//System.out.println("Numero de minusculas que tendrá la contraseña: " + numMinusculas);
			
			//MAYUS
			salida.writeUTF("Cuantas mayusculas quieres en la contraseña?");
			int numMayusculas = Integer.parseInt(entrada.readLine());
			//System.out.println("Numero de mayusculas que tendrá la contraseña: " + numMayusculas);
			
			//DIGITOS
			salida.writeUTF("Cuantos digitos quieres en la contraseña?");
			int numDigitos = Integer.parseInt(entrada.readLine());
			//System.out.println("Numero de digitos que tendrá la contraseña: " + numDigitos);
			
			//ESPECIALES
			salida.writeUTF("Cuantos caracteres especiales quieres en la contraseña?");
			int numCaractEspeciales = Integer.parseInt(entrada.readLine());
			//System.out.println("Numero de caracteres especiales que tendrá la contraseña: " + numCaractEspeciales);
			
			

            
			//Generamos los requisitos y los printeamos en el Servidor
			RequisitosPass requisitosPass = new RequisitosPass(numMayusculas, numMinusculas, numDigitos, numCaractEspeciales);
			System.out.println(requisitosPass.toString());
			
			this.servicioPass = new ServicioPass(requisitosPass);			
			
			
			//Enviamos la longitud de la contraseña al cliente
			System.out.println("Longitud: " + servicioPass.longitudPass());
			
			
			salida.writeUTF("Longitud total de la contraseña que se va a generar: " + servicioPass.longitudPass());
			salida.writeUTF("¿Quieres generar la contraseña ahora? [si/no]");
			
			if(entrada.readLine().equals("si")) {
				System.out.println("Generando contraseña...");
				String contraseña = servicioPass.generaPass();
				System.out.println("Contraseña enviada al cliente");
				salida.writeUTF("La contraseña generada es: " + contraseña);
			}else {
				System.out.println("Denegada la generación de contraseña.");
				salida.writeUTF("Hasta la proxima!");
				socket.close();
			}
			
			while (linea != null){	//Esto ira antes del close para que no se cierre
				
 				}
            socket.close();
        }
    }

    public void finalizarServer() throws IOException {
        serverSocket.close();
    }

}
