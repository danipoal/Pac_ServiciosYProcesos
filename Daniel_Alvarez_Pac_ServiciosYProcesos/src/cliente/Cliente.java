package cliente;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {

    private final String HOST = "localhost";
    private final int PUERTO = 4321;
    private Socket socket;

    public Cliente() throws IOException {
        socket = new Socket(HOST, PUERTO);
    }

    public void interactua() throws IOException {
    	String linea, mayusculasLinea;
    	//Iniciamos la entrada de datos
        DataInputStream entradaServidor = new DataInputStream(socket.getInputStream());
        System.out.println(entradaServidor.readUTF()); //Mostramos el mensaje por pantalla
        
        //Enviamos el nuestra respuesta al nombre
        BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
        System.out.flush();
        
		linea = consola.readLine();
		salida.writeBytes(linea+"\n");
        
		System.out.println(entradaServidor.readUTF()); //Leemos lo que dice el servidor que nos va a pedir unos parametros
		
		System.out.flush();
		System.out.println(entradaServidor.readUTF()); //Leemos que nos pide las minusculas de la contra
		//mayusculasLinea = entradaServidor.readUTF();
		
		
		//Vuelve el bucle para enviar algo mas. Esto sirve para que no se cierre aun, que puedas escribir algo en el cliente
		System.out.flush();
		linea = consola.readLine();
        ///////////////
        

		
		System.out.print("Servidor Cerrado");
        salida.close();
        entradaServidor.close();
        socket.close();
    }

}
