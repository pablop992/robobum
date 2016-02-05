package com.robobum.negocio;

import com.robobum.model.DireccionDesplazamiento;
import com.robobum.model.Posicion;

public class RobotDetector {
	
	private Posicion posicionActual;

	public Posicion getPosicionActual() {
		return posicionActual;
	}

	public void setPosicionActual(Posicion posicionActual) {
		this.posicionActual = posicionActual;
	}
	
	public void cambiarDireccion(DireccionDesplazamiento direccionDesplazamiento){
		switch(posicionActual.getOrientacion()){
			case NORTE:
				if()
		}
		
	}

}
