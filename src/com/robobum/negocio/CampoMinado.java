package com.robobum.negocio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.robobum.model.Amenaza;
import com.robobum.model.Orientacion;

/**
 * Clase que representa el campo minado a recorrer a traves
 * del robot Robobum
 * @author Pablo Pelaez <pablo.pelaez@telintel.net>
 *
 */
public class CampoMinado {
	
	
	/*
	 * Primer indice: posicion en Y
	 * Segundo indice: posicion en X
	 */
	private Amenaza[][] matrizCampo;
	
	private int longitudX, longitudY;
	
	private boolean amenazasCargadas = false;
	
	public CampoMinado(int longitudX, int longitudY){
		this.longitudX = longitudX;
		this.longitudY = longitudY;
		
		matrizCampo = new Amenaza[longitudY][longitudX];
		
		cargarAmenazasEnCampo();
	}
	
	private void cargarAmenazasEnCampo(){
		
		InputStreamReader strmRdr= new InputStreamReader(getClass().getResourceAsStream("amenazas.txt"));
		BufferedReader buffRdr = new BufferedReader(strmRdr);
		
		String line = null;
		String[] contenidoLinea = null;
		Amenaza nuevaAmenaza = null;
		int posX, posY;
		
		try {
			while((line = buffRdr.readLine()) != null){
				
				/*
				 * Se divide el contenido en un arreglo de longitud 3:
				 *  - indice 0: posicion de amenaza en X
				 *  - indice 1: posicion de amenaza en Y
				 *  - indice 2: tipo de amenaza a ubicar
				 */
				contenidoLinea = line.split("(,)") ;
				
				posX = Integer.valueOf(contenidoLinea[0]);
				posY = Integer.valueOf(contenidoLinea[1]);
				
				nuevaAmenaza = Amenaza.obtenerAmenazaPorDistintivo(contenidoLinea[2]);
				
				if (nuevaAmenaza == null){
					System.err.println("La amenaza referida por el distintivo \"" 
							+ contenidoLinea[2] + "\"no es reconocida por nuestro sistema");
					continue;
				}
				else if(Integer.valueOf(contenidoLinea[0]) > (longitudX - 1) ||
					Integer.valueOf(contenidoLinea[1]) > (longitudY - 1) ){
					System.err.println("La amenaza \"" + nuevaAmenaza.getTipoAmenaza() + 
							"\" a ubicar en las posiciones (" + posX + ", " + posY + ") "
									+ "excede los limites considerados por nuestro sistema");
					continue;
				}
				
				matrizCampo[posY][posX] = nuevaAmenaza;
			}
		} catch (IOException e) {
			//Nada que hacer, continue con la siguiente linea
		}
		
	}
	
	private boolean validarAmenazaEncontrada(int posX, int posY, Orientacion orientacionRobot){
		
		sw
		
	}

	
	

}
