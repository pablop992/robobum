package com.robobum.negocio;

import java.util.Scanner;

import com.robobum.model.Amenaza;
import com.robobum.model.ComandoRobotDetector;
import com.robobum.model.Orientacion;
import com.robobum.model.Posicion;

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
		this.robot = new RobotDetector();
	}

	public CampoMinado getCampo() {
		return campo;
	}

	public RobotDetector getRobot() {
		return robot;
	}
	
	public void capturarDatosInicialesRobot(){
		
		while(campo.getAmenazasDetectadas() < campo.getAmenazasEnCampo()){
			Scanner sc = new Scanner(System.in);
	        System.out.println("Por favor ingrese la posicion y orientacion inicial del robot:");
	        String posicion = sc.nextLine();
	        System.out.println("Por favor ingrese las acciones a realizar por el robot");
	        String acciones = sc.nextLine();
	        
	        if(!crearNuevaPosicionRobot(posicion))
	        	continue;
	        ejecutarAccionesEnRobot(acciones);
		}
		
		System.out.println("Felicitaciones, has encontrado todas las amenazas en el campo!!");
		finalizarBusqueda();
		
		
	}

	private boolean crearNuevaPosicionRobot(String posicion) {
		String[] posicionSplit = posicion.trim().split("\\s");
		Posicion nuevaPosRobot = new Posicion();
		
		int posRobotX = Integer.valueOf(posicionSplit[0]);
		
		if(posRobotX > (campo.getLongitudX() - 1) && posRobotX < 0){
			System.err.println("Lo sentimos, pero la posicion del robot en el eje X supera los limites");
			return false;
		}
		
		nuevaPosRobot.setPosicionX(posRobotX);
		
		int posRobotY = Integer.valueOf(posicionSplit[1]);
		
		if(posRobotY > (campo.getLongitudY()) && posRobotX < 0){
			System.err.println("Lo sentimos, pero la posicion del robot en el eje Y supera los limites");
			return false;
		}
		
		nuevaPosRobot.setPosicionY(posRobotY);
		
		Orientacion orientacionRobot = Orientacion.obtenerOrientacionPorAbreviacion(posicionSplit[2]);
		
		if(orientacionRobot == null){
			System.err.println("Lo sentimos, pero la orientacion dada para el robot no es valida");
			return false;
		}
		
		nuevaPosRobot.setOrientacion(orientacionRobot);
		robot.setPosicionActual(nuevaPosRobot);
		
		return true;
	}
	
	private void ejecutarAccionesEnRobot(String acciones) {
		
		String [] accionesSplit = acciones.trim().split("|");
		ComandoRobotDetector nuevoComando = null;
		
		
		for (int i = 0; i < accionesSplit.length; i++) {
			nuevoComando = ComandoRobotDetector.obtenerComandoPorAbreviacion(accionesSplit[i]);
			
			if(nuevoComando == null){
				System.err.println("El comando detallado por la letra \"" 
						+ accionesSplit[i] + "\" no es valido, se omite su ejecución");
			}
			
			switch (nuevoComando) {
			case IZQUIERDA:
			case DERECHA:
				robot.cambiarDireccion(nuevoComando);
				break;
			case ADELANTE:
				if(!robot.realizarAvance())
					System.err.println("No fue posible realizar el avance del robot, "
							+ "ya que este excede los limites del campo"); 
				
			}
			
			campo.validarColisionConAmenaza(robot.getPosicionActual());
		}
		
		System.out.println("Posicion despues del desplazamiento: " + robot.getPosicionActual().getPosicionX() + " " 
				+ robot.getPosicionActual().getPosicionY() + " " + robot.getPosicionActual().getOrientacion().getAbreviacion());
		
		campo.validarAmenazaEncontrada(robot.getPosicionActual());
		
	}
	
	/**
	 * Finaliza la apliacion, con un pequeño retraso previo para que el
	 * usuario pueda leer los mensajes generados previamnente al cierre
	 */
	private void finalizarBusqueda(){
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// Improbable que esta excepcion suceda
		}
		System.exit(0);
	}
	
	
	
	
}
