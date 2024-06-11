package poo2.edu.unq.ar.tpFinal;

import java.time.LocalTime;

public class EstacionamientoNoVigente implements IEstadoDeEstacionamiento {

	public void alertaInicioEstacionamiento(AppDeUsuario usuario) throws Exception {
		if (usuario.getSEM().calcularSaldoSuficiente(usuario)) {
			ZonaDeEstacionamiento zona = usuario.getSEM().encontrarZonaEstacionamientoEn(usuario.getLocalizacion());
			Estacionamiento estacionamiento = new EstacionamientoViaApp(usuario, LocalTime.now(),
					usuario.getSEM().getHoraFin(), usuario.getPatente());
			usuario.getSEM().registrarUnNuevoEstacionamientoEnLaZona(estacionamiento, zona);
			usuario.cambiarAEstadoVigente();
		} else {
			new Exception("No tiene saldo suficiente"); 
		}
	}

	public void alertaFinEstacionamiento(AppDeUsuario usuario) {
		System.out.println("Ya tenes el estacionamiento finalizado");
	}

	public boolean estaVigente() {
		return false;
	}

	
}