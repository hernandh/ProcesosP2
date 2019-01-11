/**
 * 
 */
package usajni;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fundamentos.Grafica;

/**
 * @author julio
 *
 */

import jcrypta.Jcripta;

public class Codifica {

	/**
	 * 
	 */
	public Codifica() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String clave;
		String sal;
		String resultado;
		long tiempo_inicial, duracion;
		final int n = 500;
		final int numBarras = 20; //Puede variar dependiendo de lo bonito de la grafica
		
		//Crypta criptaLink = new Crypta();     //<-- para usar las dll (de 32 bits)
		//System.out.println(System.getProperty("java.library.path"));
		
		Jcripta criptaLink = new Jcripta();     //<-- para usar la clase java equivalente
		
		//Jcriptu criptaLink = new Jcriptu();     //<-- para hacer el primer ensayo
		//Jcripto criptaLink = new Jcripto();     //<-- para hacer el segundo ensayo
			
		clave = new String("mipassword");
		sal   = new String("sa"); 
		//Se crea la grafica
		Grafica histograma = new Grafica("Histograma","Tiempo","Repeticiones");
		List<Long> listaValores = new ArrayList<Long>();
		
		for(int i=0;i<n;i++) {
			tiempo_inicial = System.currentTimeMillis();
			criptaLink.crypta(clave,sal);
			duracion = System.currentTimeMillis() - tiempo_inicial;
			//Se añaden a la lista de valores
			listaValores.add(duracion);
		}
		long min = Collections.min(listaValores);
		long max = Collections.max(listaValores);
		long media = 0;
		long desvEst = 0;
		
		for(int i=0;i<listaValores.size();i++) {
			media += listaValores.get(i);
		}
		media = media / listaValores.size();
		
		for(int i=0;i<listaValores.size();i++) {
			desvEst += Math.pow(listaValores.get(i) - media,2);
		}
		
		desvEst = (long) Math.sqrt(desvEst/listaValores.size());
		
		int barras[] = new int[numBarras];
		for(int i=0;i<barras.length;i++) {
			barras[i]=0;
		}
		
		for(int i=0;i<listaValores.size();i++) {
			//Formulita
			int numBarraAfectada = Math.round(((listaValores.get(i)-min) * numBarras) / (max-min));
			if(numBarraAfectada <numBarras) {
			barras[numBarraAfectada]++;
			}
		}
		
		for(int i=0;i<numBarras;i++) {
			histograma.inserta(i,barras[i]);
		}
		System.out.println("el tiempo de respuesta de mejor caso: " + min);
		System.out.println("el tiempo de respuesta de peor caso: " + max);
		System.out.println("el tiempo de respuesta promedio: " + media);
		System.out.println("la desviacion estandar: " + desvEst);
		histograma.pinta();

		
	}
	
	// Quitar si no se usa la dll  
	//static{
	//	  System.loadLibrary("libcrypta");
	//}
	  
}
