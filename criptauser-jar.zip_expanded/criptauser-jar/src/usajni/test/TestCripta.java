package usajni.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import usajni.Resultados;

public class TestCripta {
	String pass = "mipassword";
	String sal = "sa";
	int n = 1000;
	int numBarras = 10;
	Resultados r;
	long minAntiguo = 26;
	long maxAntiguo = 104;
	long mediaAntigua = 65;
	long desvEstAntigua = 12;

	@Test
	public void testDatos() {
		r = new Resultados(pass,sal,n,numBarras);
		r.calcula();
		
		long porcentaje = 0;
		long porcentajeAcum = 0;
		long media = r.getMedia();
		System.out.println(media);
		porcentaje = Math.abs((media-mediaAntigua))/mediaAntigua;
		porcentajeAcum += porcentaje;
		assertTrue(porcentaje <= 0.1);
		
		long min = r.getMin();
		System.out.println(min);
		porcentaje = Math.abs((min-minAntiguo))/minAntiguo;
		porcentajeAcum += porcentaje;
		assertTrue(porcentaje <= 0.1);
		
		long max = r.getMax();
		System.out.println(max);
		porcentaje = Math.abs((max-maxAntiguo))/maxAntiguo;
		porcentajeAcum += porcentaje;
		assertTrue(porcentaje <= 0.1);
		
		long desvEst = r.getDesvEst();
		System.out.println(desvEst);
		porcentaje = Math.abs((desvEst-desvEstAntigua))/desvEstAntigua;
		porcentajeAcum += porcentaje;
		assertTrue(porcentaje <= 0.1);
		
		assertTrue(porcentajeAcum <= 0.25);
	}

}
