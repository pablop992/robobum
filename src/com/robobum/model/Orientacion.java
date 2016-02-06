package com.robobum.model;

public enum Orientacion {
	
	NORTE("N"),
	SUR("S"),
	ORIENTE("E"),
	OCCIDENTE("W");
	
	String abreviacion;
	
	private Orientacion(String abreviacion){
		this.abreviacion = abreviacion;
	}
	
	public static Orientacion obtenerOrientacionPorAbreviacion(String abreviacion){
		for(Orientacion ort : Orientacion.values()){
			if(ort.getAbreviacion().equals(abreviacion))
				return ort;
		}
		return null;
	}

	public String getAbreviacion() {
		return abreviacion;
	}
	
	
}
