package com.robobum.negocio;

import com.robobum.model.ComandoRobotDetector;
import com.robobum.model.Orientacion;
import com.robobum.model.Posicion;

public class RobotDetector {
	
	private Posicion posicionActual;
	
	/*
	 * Referencia a la clase principal, para validaciones sobre los valores
	 * del campo minado
	 */
	private Robobum robobumRef;

	public Posicion getPosicionActual() {
		return posicionActual;
	}

	public void setPosicionActual(Posicion posicionActual) {
		this.posicionActual = posicionActual;
	}
	
	/**
	 * Cambia la direccion hacia la cual apunta el robot, de acuerdo a una
	 * direccion de desplazamiento
	 * 
	 * @param direccionDesplazamiento Direccion hacia la cual desea girarse el
	 * robot (Izquierda o Derecha)
	 */
	public void cambiarDireccion(ComandoRobotDetector direccionDesplazamiento){
		switch(posicionActual.getOrientacion()){
			case NORTE:
				if(direccionDesplazamiento.equals(ComandoRobotDetector.DERECHA)){
					posicionActual.setOrientacion(Orientacion.ORIENTE);
					break;
				}else if(direccionDesplazamiento.equals(ComandoRobotDetector.IZQUIERDA)){
					posicionActual.setOrientacion(Orientacion.OCCIDENTE);
					break;
				}
			case SUR:
				if(direccionDesplazamiento.equals(ComandoRobotDetector.DERECHA)){
					posicionActual.setOrientacion(Orientacion.OCCIDENTE);
					break;
				}else if(direccionDesplazamiento.equals(ComandoRobotDetector.IZQUIERDA)){
					posicionActual.setOrientacion(Orientacion.ORIENTE);
					break;
				}
			case ORIENTE:
				if(direccionDesplazamiento.equals(ComandoRobotDetector.DERECHA)){
					posicionActual.setOrientacion(Orientacion.SUR);
					break;
				}else if(direccionDesplazamiento.equals(ComandoRobotDetector.IZQUIERDA)){
					posicionActual.setOrientacion(Orientacion.NORTE);
					break;
				}
			case OCCIDENTE:
				if(direccionDesplazamiento.equals(ComandoRobotDetector.DERECHA)){
					posicionActual.setOrientacion(Orientacion.NORTE);
					break;
				}else if(direccionDesplazamiento.equals(ComandoRobotDetector.IZQUIERDA)){
					posicionActual.setOrientacion(Orientacion.SUR);
					break;
				}
		}
	}
	
	/**
	 * Realiza un avance de una unidad en el tablero, teniendo en cuenta
	 * la orientacion actual del robot y los limites del campo
	 * 
	 * @return true si el desplazamiento a realizar se encuentra dentro de
	 * los limites del campo, false en caso contrario
	 */
	public boolean realizarAvance(){
		
		switch(posicionActual.getOrientacion()){
		case NORTE:
			if((posicionActual.getPosicionY() + 1) 
					<= (robobumRef.getCampo().getLongitudY() - 1) ){
				posicionActual.setPosicionY(posicionActual.getPosicionY() + 1);
				return true;
			}
			break;
		case SUR:
			if((posicionActual.getPosicionY() - 1) >= 0 ){
				posicionActual.setPosicionY(posicionActual.getPosicionY() - 1);
				return true;
			}
			break;
				
		case ORIENTE:
			if((posicionActual.getPosicionX() + 1) 
					<= (robobumRef.getCampo().getLongitudX() - 1) ){
				posicionActual.setPosicionX(posicionActual.getPosicionX() + 1);
				return true;
			}
			break;
		case OCCIDENTE:
			if((posicionActual.getPosicionX() - 1) >= 0 ){
				posicionActual.setPosicionX(posicionActual.getPosicionX() - 1);
				return true;
			}
			break;
		
		}
		
		return false;
	}

}
