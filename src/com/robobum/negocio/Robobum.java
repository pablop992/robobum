package com.robobum.negocio;

/**
 * Clase encargada de orquestar toda la dinamica
 * del simulador de detector de amenazas
 * 
 * @author Pablo Pelaez <pablo.pelaez@telintel.net>
 *
 */
public class Robobum {
	
	private CampoMinado campo;
	private RobotDetector robot;
	
	public Robobum(int longitudX, int longitudY){
		this.campo = new CampoMinado(longitudX, longitudY);
	}

}
