package poo2.edu.unq.ar.tpFinal;

import java.time.LocalTime;

public class EstacionamientoVigente implements IEstadoDeEstacionamiento {

	public void alertaFinEstacionamiento(AppDeUsuario usuario) throws Exception {
		usuario.getSEM().finalizarEstacionamiento(usuario.getCelular());
		usuario.cambiarAEstadoNoVigente();
	}

	public void alertaInicioEstacionamiento(AppDeUsuario usuario) {
		System.out.println("Ya tenes un estacionamiento vigente");
	}

	public boolean estaVigente() {
		return true;
	}

	
}