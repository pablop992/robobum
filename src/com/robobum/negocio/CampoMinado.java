package com.robobum.negocio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.robobum.model.Amenaza;
import com.robobum.model.Orientacion;
import com.robobum.model.Posicion;

/**
 * Clase que representa el campo minado a recorrer a traves del robot Robobum
 * 
 * @author Pablo Pelaez <pablo.pelaez@telintel.net>
 *
 */
public class CampoMinado {

	/*
	 * Primer indice: posicion en Y Segundo indice: posicion en X
	 */
	private Amenaza[][] matrizCampo;

	private int amenazasEnCampo;
	private int amenazasDetectadas;

	private int longitudX, longitudY;

	public CampoMinado(int longitudX, int longitudY) {
		this.longitudX = longitudX;
		this.longitudY = longitudY;

		matrizCampo = new Amenaza[longitudY][longitudX];

		cargarAmenazasEnCampo();
	}

	/**
	 * Carga las amenazas detalladas en el archivo de texto, segun sus
	 * posiciones y tipos. Omite aquellas amenazas cuyo distintivo no se
	 * encuentre identificado o sus posiciones excedan los limites del campo.
	 */
	private void cargarAmenazasEnCampo() {

		FileReader strmRdr;
		try {
			strmRdr = new FileReader(new File("amenazas.txt"));
			BufferedReader buffRdr = new BufferedReader(strmRdr);

			String line = null;
			String[] contenidoLinea = null;
			Amenaza nuevaAmenaza = null;
			int posX, posY;

			while ((line = buffRdr.readLine()) != null) {

				/*
				 * Se divide el contenido en un arreglo de longitud 3: - indice
				 * 0: posicion de amenaza en X - indice 1: posicion de amenaza
				 * en Y - indice 2: tipo de amenaza a ubicar
				 */
				contenidoLinea = line.replaceAll("[\\(\\),]", " ").trim().split("\\s");

				posX = Integer.valueOf(contenidoLinea[0]);
				posY = Integer.valueOf(contenidoLinea[1]);

				nuevaAmenaza = Amenaza.obtenerAmenazaPorDistintivo(contenidoLinea[2]);

				if (nuevaAmenaza == null) {
					System.err.println("La amenaza referida por el distintivo \"" + contenidoLinea[2]
							+ "\"no es reconocida por nuestro sistema");
					continue;
				} else if (Integer.valueOf(contenidoLinea[0]) > (longitudX - 1)
						|| Integer.valueOf(contenidoLinea[1]) > (longitudY - 1)) {
					System.err.println(
							"La amenaza \"" + nuevaAmenaza.getTipoAmenaza() + "\" a ubicar en las posiciones (" + posX
									+ ", " + posY + ") " + "excede los limites considerados por nuestro sistema");
					continue;
				}

				matrizCampo[posY][posX] = nuevaAmenaza;
				amenazasEnCampo++;
			}
		} catch (IOException e) {
			// Nada que hacer, continue con la siguiente linea
		}

	}

	/**
	 * Valida que exista una amenaza en la posicion siguiente al robot, teniendo
	 * en cuenta la orientacion del mismo y los limites del campo
	 * 
	 * @param posicionRobot
	 * @return
	 */
	protected void validarAmenazaEncontrada(Posicion posicionRobot) {

		Amenaza amenazaEncontrada = null;
		int posAmenazaX = 0, posAmenazaY = 0;

		switch (posicionRobot.getOrientacion()) {
		case NORTE: {

			if (posicionRobot.getPosicionY() + 1 <= longitudY - 1) {
				posAmenazaX = posicionRobot.getPosicionX();
				posAmenazaY = posicionRobot.getPosicionY() + 1;
			}
		}
		case SUR: {

			if (posicionRobot.getPosicionY() - 1 >= 0) {
				posAmenazaX = posicionRobot.getPosicionX();
				posAmenazaY = posicionRobot.getPosicionY() - 1;
			}
		}
		case ORIENTE: {

			if (posicionRobot.getPosicionX() + 1 <= longitudX + 1) {
				posAmenazaX = posicionRobot.getPosicionX() + 1;
				posAmenazaY = posicionRobot.getPosicionY();
			}
		}
		case OCCIDENTE: {

			if (posicionRobot.getPosicionX() - 1 >= 0) {
				posAmenazaX = posicionRobot.getPosicionX() - 1;
				posAmenazaY = posicionRobot.getPosicionY();
			}
		}
		}
		
		if((amenazaEncontrada = matrizCampo[posAmenazaY][posAmenazaX]) != null){
			
			System.out.println("Se ha desmantelado una amenaza de tipo \"" + amenazaEncontrada.getTipoAmenaza() + "\" "
					+ "en la posicion (" + posAmenazaX + "," + posAmenazaY + ").");
			
			matrizCampo[posAmenazaY][posAmenazaX] = null;
			
			this.amenazasDetectadas++;
		}
		else{
			System.out.println("No se ha encontrado ninguna amenaza en la posicion "
					+ "(" + posAmenazaX + "," + posAmenazaY + ").");
		}

	}

	/**
	 * Valida si en la posicion actual del robot existe una amenaza
	 * 
	 * @param posicionRobot
	 * @return La amenaza encontrada en la posicion actual del robot, null si no
	 *         existe una amenaza en la posicion
	 */
	public void validarColisionConAmenaza(Posicion posicionRobot) {
		
		Amenaza amenazaColision = null;
		
		if((amenazaColision = matrizCampo[posicionRobot.getPosicionY()]
										 [posicionRobot.getPosicionX()]) != null){
			System.err.println("Has detectado una nueva amenaza de tipo \""+ amenazaColision.getTipoAmenaza() 
			+ "\" en la posicion (" + posicionRobot.getPosicionX() + "," 
				+ posicionRobot.getPosicionY() + "). Ten mas cuidado la proxima vez!");
		}

	}

	public int getLongitudX() {
		return longitudX;
	}

	public int getLongitudY() {
		return longitudY;
	}

	public int getAmenazasEnCampo() {
		return amenazasEnCampo;
	}

	public int getAmenazasDetectadas() {
		return amenazasDetectadas;
	}
	
	
	

}
