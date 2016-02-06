package com.robobum.model;

public enum ComandoRobotDetector {
	
	IZQUIERDA("I"),
	DERECHA("D"),
	ADELANTE("A");
	
	String abreviacion;
	
	private ComandoRobotDetector(String abreviacion){
		this.abreviacion = abreviacion;
	}
	
	public static ComandoRobotDetector obtenerComandoPorAbreviacion(String abreviacion){
		for(ComandoRobotDetector comando : ComandoRobotDetector.values()){
			if(comando.getAbreviacion().equals(abreviacion))
				return comando;
		}
		
		return null;
	}

	public String getAbreviacion() {
		return abreviacion;
	}
	
	
}
