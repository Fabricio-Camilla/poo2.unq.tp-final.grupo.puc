package poo2.edu.unq.ar.tpFinal;

import java.time.LocalTime;

public class EstacionamientoVigente implements IEstadoDeEstacionamiento {

	public void alertaFinEstacionamiento(AppDeUsuario usuario) throws Exception {
		usuario.getSEM().finalizarEstacionamiento(usuario.getCelular());
		usuario.setEstado(new EstacionamientoNoVigente());
	}

	public void alertaInicioEstacionamiento(AppDeUsuario usuario) {

	}

	public boolean estaVigente() {
		return true;
	}

	public void vigenciaPara(AppInspector inspector, String patente) {
	}
}