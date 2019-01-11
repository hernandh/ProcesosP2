package usajni.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import usajni.Resultados;

public class TestCripta {
	
	@Test
	public void testDatos() {	
		String pass = "mipassword";
		String sal = "sa";
		int n = 500;
		int nBarras = 10;
		long porcentajeVariacion = 0;
		long porcentajeVariacionTotal = 0;
		long tMinAntiguo = 26;
		long tMaxAntiguo = 104;
		long tMedioAntiguo = 65;
		long desviacionAntigua = 12;
		
		Resultados res = new Resultados(pass, sal, n, nBarras);
		res.calcula();
		
		// a)
		long tMin = res.getMin();
		porcentajeVariacion = Math.abs((tMin - tMinAntiguo)) / tMinAntiguo;
		porcentajeVariacionTotal += porcentajeVariacion;
		
		assertTrue(porcentajeVariacion <= 0.1);
		
		
		// b)
		long tMax = res.getMax();
		porcentajeVariacion = Math.abs((tMax - tMaxAntiguo)) / tMaxAntiguo;
		porcentajeVariacionTotal += porcentajeVariacion;
		
		assertTrue(porcentajeVariacion <= 0.1);	
		
		
		// c)
		long tMedio = res.getMedia();
		porcentajeVariacion = Math.abs((tMedio - tMedioAntiguo)) / tMedioAntiguo;
		porcentajeVariacionTotal += porcentajeVariacion;
		
		assertTrue(porcentajeVariacion <= 0.1);
		
		
		// d)
		long desviacion = res.getDesvEst();
		porcentajeVariacion = Math.abs((desviacion - desviacionAntigua)) / desviacionAntigua;
		porcentajeVariacionTotal += porcentajeVariacion;
		
		assertTrue(porcentajeVariacion <= 0.1);
		
		
		// Comprobamos que el el porcentaje de variación acumulado no supera el 25%
		assertTrue(porcentajeVariacionTotal <= 0.25);
		
		// Mostramos los resultados obtenidos
		System.out.println("Tiempo mejor caso: " + tMin);
		System.out.println("Tiempo peor caso: " + tMax);
		System.out.println("Tiempo promedio: " + tMedio);
		System.out.println("Desviación estándar: " + desviacion);
	}

}
