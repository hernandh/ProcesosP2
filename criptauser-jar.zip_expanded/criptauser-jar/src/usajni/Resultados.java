package usajni;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fundamentos.Grafica;
import jcrypta.Jcripta;

public class Resultados {

	private long min;
	private long max;
	private long media;
	private long desvEst;
	String clave;
	String sal;
	long tiempo_inicial, duracion;
	List<Long> listaValores;
	Jcripta criptaLink;
	int barras[]; 
	private int n;
	private int numBarras;
	
	public Resultados(String pass,String sal,int n,int numBarras){
		criptaLink = new Jcripta();
		clave = pass;
		this.sal = sal;
		this.n = n;
		this.numBarras = numBarras;
		listaValores = new ArrayList<Long>();
	}
	/**
	 * Calcula la media,los valores max y min y la desviacion tipica
	 */
	public void calcula() {
		if(!listaValores.isEmpty()) {
			listaValores.clear();
		}
		for(int i=0;i<n;i++) {
			tiempo_inicial = System.currentTimeMillis();
			criptaLink.crypta(clave,sal);
			duracion = System.currentTimeMillis() - tiempo_inicial;
			//Se añaden a la lista de valores
			listaValores.add(duracion);
		}
		
		min = Collections.min(listaValores);
		max = Collections.max(listaValores);
		media = 0;
		desvEst = 0;
		
		for(int i=0;i<listaValores.size();i++) {
			media += listaValores.get(i);
		}
		media = media / listaValores.size();
		
		for(int i=0;i<listaValores.size();i++) {
			desvEst += Math.pow(listaValores.get(i) - media,2);
		}
		
		desvEst = (long) Math.sqrt(desvEst/listaValores.size());
		
	}
	
	public void imprime() {
		Grafica histograma = new Grafica("Histograma","Tiempo","Repeticiones");
		
		barras = new int[numBarras];
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
		/**
		System.out.println("el tiempo de respuesta de mejor caso: " + min);
		System.out.println("el tiempo de respuesta de peor caso: " + max);
		System.out.println("el tiempo de respuesta promedio: " + media);
		System.out.println("la desviacion estandar: " + desvEst);
		*/
		histograma.pinta();
	}
	
	public long getMedia() {
		return media;
	}
	
	public long getMin() {
		return min;
	}
	
	public long getMax() {
		return max;
	}
	
	public long getDesvEst() {
		return desvEst;
	}
	
}
