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
    	String linea;
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
		
		//Enviamos las MINUSCULAS al servidor

		
		int isNumber = -1;
		
		
		
		//Comprueba que es numero y luego mientras ese num sea negativo repite. Si no es numero, 

		
			
			while(isNumber < 0 ) {
				linea = consola.readLine();
				if(linea.matches("\\d+")){
					isNumber = Integer.parseInt(linea);
					if(isNumber >= 0) {
						salida.writeBytes(linea+"\n"); //Necesita un /n para que se envie
					}else {
						System.out.println("Solo puedes introducir numeros POSITIVOS. Repite: ");
					}
				}else {
					System.out.println("Solo puedes introducir numeros. Repite: ");
				}
			}

			
		
		//salida.writeBytes(linea+"\n"); //Necesita un /n para que se envie
		
		

		
		//MAYUS
		System.out.println(entradaServidor.readUTF());
		
		
		isNumber = -1;
		while(isNumber < 0 ) {
			linea = consola.readLine();
			if(linea.matches("\\d+")){
				isNumber = Integer.parseInt(linea);
				if(isNumber >= 0) {
					salida.writeBytes(linea+"\n"); //Necesita un /n para que se envie
				}else {
					System.out.println("Solo puedes introducir numeros POSITIVOS. Repite: ");
				}
			}else {
				System.out.println("Solo puedes introducir numeros. Repite: ");
			}
		}
		
		//DIGITOS
		System.out.println(entradaServidor.readUTF());
		
		
		
		isNumber = -1;
		while(isNumber < 0 ) {
			linea = consola.readLine();
			if(linea.matches("\\d+")){
				isNumber = Integer.parseInt(linea);
				if(isNumber >= 0) {
					salida.writeBytes(linea+"\n"); //Necesita un /n para que se envie
				}else {
					System.out.println("Solo puedes introducir numeros POSITIVOS. Repite: ");
				}
			}else {
				System.out.println("Solo puedes introducir numeros. Repite: ");
			}
		}
		
		//ESPECIALES
		System.out.println(entradaServidor.readUTF());
		
		
		
		isNumber = -1;
		while(isNumber < 0 ) {
			linea = consola.readLine();
			if(linea.matches("\\d+")){
				isNumber = Integer.parseInt(linea);
				if(isNumber >= 0) {
					salida.writeBytes(linea+"\n"); //Necesita un /n para que se envie
				}else {
					System.out.println("Solo puedes introducir numeros POSITIVOS. Repite: ");
				}
			}else {
				System.out.println("Solo puedes introducir numeros. Repite: ");
			}
		}
		
		
		//Longitud + generar contraseña
		System.out.println(entradaServidor.readUTF());	//Longitud
		System.out.println(entradaServidor.readUTF());	//generar?
		
		linea = consola.readLine();
		while(!linea.equals("si") && !linea.equals("no")) {
			System.out.println("Valor invalido, solo se acepta si o no");
			linea = consola.readLine();
		}
		salida.writeBytes(linea+"\n");
		
		
		//Recibimos o se denega la contraseña
		System.out.println(entradaServidor.readUTF());
		if(linea.equals("no")){
			System.out.print("Cliente Cerrado");
	        salida.close();
	        entradaServidor.close();
	        socket.close();
		}
		
		//Vuelve el bucle para enviar algo mas. Esto sirve para que no se cierre aun, que puedas escribir algo en el cliente
		System.out.flush();
		linea = consola.readLine();
        ///////////////
        

		
		System.out.print("Cliente Cerrado");
        salida.close();
        entradaServidor.close();
        socket.close();
    }

}
