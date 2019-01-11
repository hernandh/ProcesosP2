package usajni;

import java.util.List;
import fundamentos.Grafica;
import jcrypta.Jcripta;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @authors Daniel Peñil,Hector Hernandez y Carlos Fernandez
 */
public class CodificaV2 {
	private long tMin;
	private long tMax;
	private long tMedio;
	private long desviacion;
	String clave;
	String sal;
	long tiempo_inicial, duracion; 
	private int n;
	private int nBarras;
	List<Long> tiempos;
	Jcripta criptaLink;
	int bars[];
	
	public CodificaV2(String clave,String sal,int n,int nBarras){
		criptaLink = new Jcripta();
		this.clave = clave;
		this.sal = sal;
		this.n = n;
		this.nBarras = nBarras;
		tiempos = new ArrayList<Long>();
	}
	
	/**
	 * Metodo que calcula el tiempo de respuesta de mejor caso, el de peor caso, el tiempo promedio y la
	 * desviacion estándar.
	 */
	public void obtenResultados() {
		if(!tiempos.isEmpty()) {
			tiempos.clear();
		}
		for(int i=0;i<n;i++) {
			tiempo_inicial = System.currentTimeMillis();
			criptaLink.crypta(clave,sal);
			duracion = System.currentTimeMillis() - tiempo_inicial;
			//Se añaden a la lista de valores
			tiempos.add(duracion);
		}
		tMin = Collections.min(tiempos);
		tMax = Collections.max(tiempos);
		tMedio = 0;
		desviacion = 0;
		for(int i=0;i<tiempos.size();i++) {
			tMedio += tiempos.get(i);
		}
		tMedio = tMedio / tiempos.size();
		for(int i=0;i<tiempos.size();i++) {
			desviacion += Math.pow(tiempos.get(i) - tMedio,2);
		}
		desviacion =(long) Math.sqrt(desviacion/tiempos.size());	
	}
	
	/**
	 * Metodo que pinta el histograma
	 */
	public void dibujaHistograma() {
		Grafica histograma = new Grafica("Histograma","Tiempo","Repeticiones");	
		bars = new int[nBarras];
		for(int i = 0; i < bars.length; i++) {
			bars[i]=0;
		}	
		for(int i = 0; i < tiempos.size(); i++) {
			int nBarraAct = Math.round(((tiempos.get(i)-tMin) * nBarras) / (tMax-tMin));
			if(nBarraAct <nBarras) {
			bars[nBarraAct]++;
			}
		}
		for(int i = 0; i < nBarras; i++) {
			histograma.inserta(i,bars[i]);
		}
		histograma.pinta();
	}
	
	/**
	 * observadores
	 */
	public long getMedia() {
		return tMedio;
	}
	public long getMin() {
		return tMin;
	}
	public long getMax() {
		return tMax;
	}
	public long getDesvEst() {
		return desviacion;
	}
	
}
