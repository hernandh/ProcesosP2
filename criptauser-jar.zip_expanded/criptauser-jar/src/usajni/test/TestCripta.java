package usajni.test;

import static org.junit.Assert.*;
import org.junit.Test;

import usajni.Resultados;

public class TestCripta {
	
	@Test
	public void testDatos() {	
		String pass = "mipassword";
		String sal = "sa";
		int n = 500;
		int numBarras = 20;
		long porcentajeVariacion = 0;
		long porcentajeVariacionTotal = 0;
		long minAntiguo = 27;
		long maxAntiguo = 80;
		long mediaAntigua = 51;
		long desvEstAntigua = 9;
		
		Resultados res = new Resultados(pass,sal,n,numBarras);
		res.calcula();
		
		// a)
		long min = res.getMin();
		System.out.println("Tiempo mejor caso: " + min);
		porcentajeVariacion = Math.abs((min-minAntiguo)) / minAntiguo;
		porcentajeVariacionTotal += porcentajeVariacion;
		assertTrue(porcentajeVariacion <= 0.1);
		
		// b)
		long max = res.getMax();
		System.out.println("Tiempo peor caso: " + max);
		porcentajeVariacion = Math.abs((max-maxAntiguo)) / maxAntiguo;
		porcentajeVariacionTotal += porcentajeVariacion;
		assertTrue(porcentajeVariacion <= 0.1);	
		
		// c)
		long media = res.getMedia();
		System.out.println("Tiempo promedio: " + media);
		porcentajeVariacion = Math.abs((media-mediaAntigua)) / mediaAntigua;
		porcentajeVariacionTotal += porcentajeVariacion;
		assertTrue(porcentajeVariacion <= 0.1);
		
		// d)
		long desvEst = res.getDesvEst();
		System.out.println("Desviación estándar: " + desvEst);
		porcentajeVariacion = Math.abs((desvEst-desvEstAntigua)) / desvEstAntigua;
		porcentajeVariacionTotal += porcentajeVariacion;
		assertTrue(porcentajeVariacion <= 0.1);
		
		// Comprobamos que el el porcentaje de variación acumulado no supera el 25%
		assertTrue(porcentajeVariacionTotal <= 0.25);
	}

}
