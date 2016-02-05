package com.robobum.model;

public enum DireccionDesplazamiento {
	
	IZQUIERDA("I"),
	DERECHA("D");
	
	String abreviacion;
	
	private DireccionDesplazamiento(String abreviacion){
		this.abreviacion = abreviacion;
	}
}
