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

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String clave;
		String sal;
		long tiempo_inicial, duracion;
		final int n = 1000;
		final int nBarras = 10; //Puede variar dependiendo de lo bonito de la grafica		

		Jcripta criptaLink = new Jcripta();     //<-- para usar la clase java equivalente

		clave = new String("mipassword");
		sal   = new String("sa"); 

		// Definimos el histograma
		Grafica histograma = new Grafica("Histograma","Tiempo","Repeticiones");
		List<Long> tiempos = new ArrayList<Long>();

		for(int i=0; i < n; i++) {
			tiempo_inicial = System.currentTimeMillis();
			criptaLink.crypta(clave, sal);
			duracion = System.currentTimeMillis() - tiempo_inicial;

			tiempos.add(duracion);
		}
		
		long tMin = Collections.min(tiempos);
		long tMax = Collections.max(tiempos);		

		long auxTotal = 0;
		for(int i=0; i < tiempos.size(); i++) {
			auxTotal += tiempos.get(i);
		}
		
		long tMedio = auxTotal / tiempos.size();

		
		long desviacion = 0;
		for(int i=0; i < tiempos.size(); i++) {
			desviacion += Math.pow(tiempos.get(i) - tMedio, 2);
		}

		desviacion = (long) Math.sqrt(desviacion / tiempos.size());

		int barras[] = new int[nBarras];
		for(int i=0; i < barras.length; i++) {
			barras[i]=0;
		}

		for(int i=0; i < tiempos.size(); i++) {
			//Formulita
			int numBarraAfectada = Math.round(((tiempos.get(i) - tMin) * nBarras) / (tMax - tMin));
			if(numBarraAfectada < nBarras) {
				barras[numBarraAfectada]++;
			}
		}

		for(int i=0; i < nBarras; i++) {
			histograma.inserta(i, barras[i]);
		}	

		histograma.pinta();	

		// Mostramos los resultados por pantalla
		System.out.println("Tiempo mejor caso: " + tMin);
		System.out.println("Tiempo peor caso: " + tMax);
		System.out.println("Tiempo promedio: " + tMedio);
		System.out.println("Desviación estándar: " + desviacion);
	}  
}
