package servidor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ServicioPass {

	private RequisitosPass requisitosPass;
	
	public ServicioPass(RequisitosPass requisitosPass) {
		this.requisitosPass = requisitosPass;
	}
	
	public String generaPass() {
		Random random = new Random();
		String contraseña = "";
		
		//Obtener digitos aleatorios
		String digitos = "";
		for(int i = 0; i< requisitosPass.getNumDigitos(); i++) {
			int numeroAleatorio = random.nextInt(10);
			digitos = digitos + numeroAleatorio; 
			//System.out.println("Digito aleatorio: " +digitos);
		}
		
		//Obtener minusculas
		String minusculas = "";
		for(int i = 0; i< requisitosPass.getNumMinusculas(); i++) {
			int numeroAsciAleatorio = random.nextInt(26) + 'a';
			char letra = (char) numeroAsciAleatorio;
			minusculas = minusculas + letra;
			//System.out.println("Minuscula aleatoria: " +minusculas);
		}
		
		//Obtener minusculas
		String mayusculas = "";
		for(int i = 0; i< requisitosPass.getNumMayusculas(); i++) {
			int numeroAsciAleatorio = random.nextInt(26) + 'a';
			char letra = (char) numeroAsciAleatorio;
			mayusculas = mayusculas + Character.toUpperCase(letra);	//Convertimos la minusculas de antes en Mayus
			//System.out.println("Minuscula aleatoria: " +mayusculas);
		}
		
		//Obtener caracteres especiales
		String listaEspeciales = "!@#$%^&*()_-+=.:?";
		String especiales = "";
		for(int i = 0; i< requisitosPass.getNumCaractEspeciales(); i++) {
			int numeroAleatorio = random.nextInt(17);   //Para que escoja uno de los 16 caracteres
			especiales = especiales + listaEspeciales.charAt(numeroAleatorio);
			
			//System.out.println("Caracter aleatorio: " +especiales);
		}
		
		//Mezclamos toda la contraseña
		contraseña = digitos + minusculas + mayusculas + especiales;
        List<Character> caracteres = new ArrayList<>();

        // Agregar todos los caracteres de las cadenas a la lista

         for (char c : contraseña.toCharArray()) {		//Pasamos todo a un array de caracteres
             caracteres.add(c);
           }
         Collections.shuffle(caracteres);				//Mezclamos el array de caracteres
        
         contraseña = "";								//Reseteamos contraseña
         for (char c : caracteres) {
        	 
             contraseña = contraseña + c;				//Rellenamos contraseña con array char 
         }
         //System.out.println(contraseña);
         
		return contraseña;
	}
	
	public int longitudPass() {
		
		
		return requisitosPass.getNumMayusculas() + requisitosPass.getNumMinusculas() + 
				requisitosPass.getNumDigitos() + requisitosPass.getNumCaractEspeciales();
	}
}
