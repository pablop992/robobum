package com.robobum.model;

public enum Amenaza {
	
	BOMBA_ANTIPERSONA("Bomba Antipersona", "*"),
	MARCIANO_REBELDE("Marciano Rebelde","%"),
	JUSTIN_BIEBER("Justin Bieber", "$");
	
	String tipoAmenaza;
	String distintivo;
	
	private Amenaza(String tipoAmenaza, String distintivo){
		this.distintivo=distintivo;
		this.tipoAmenaza=tipoAmenaza;
	}
	
	
	public static Amenaza obtenerAmenazaPorDistintivo(String distintivoAEvaluar){
		
		for(Amenaza amenz : Amenaza.values()){
			if(amenz.getDistintivo().equals(distintivoAEvaluar))
				return amenz;
		}
		
		return null;
	}

	public String getTipoAmenaza() {
		return tipoAmenaza;
	}

	public String getDistintivo() {
		return distintivo;
	}
	
	

}
