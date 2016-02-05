package com.robobum.model;

/**
 * Clase que representa una posicion en el campo, y determina
 * si existe una amenaza en ella
 * 
 * @author Pablo Pelaez
 *
 */
public class Posicion {
	
	private int posicionX, posicionY;
	private Orientacion orientacion;
	
	public int getPosicionX() {
		return posicionX;
	}

	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	public int getPosicionY() {
		return posicionY;
	}

	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	public Orientacion getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(Orientacion orientacion) {
		this.orientacion = orientacion;
	}

}
