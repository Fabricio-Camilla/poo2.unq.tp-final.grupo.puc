package poo2.edu.unq.ar.tpFinal;

import java.time.LocalTime;

public class EstacionamientoNoVigente implements IEstadoDeEstacionamiento {
	//solo se utilzaria estando en el modo atuomatico
	public void alertaInicioEstacionamiento(AppDeUsuario usuario) throws Exception {
		if (usuario.getSEM().calcularSaldoSuficiente(usuario)) {
			ZonaDeEstacionamiento zona = usuario.getSEM().encontrarZonaEstacionamientoEn(usuario.getLocalizacion()); //buscar zona o no?
			Estacionamiento estacionamiento = new EstacionamientoViaApp(usuario, LocalTime.now(),
					usuario.getSEM().getHoraFin(), usuario.getPatente());
			usuario.getSEM().registrarUnNuevoEstacionamientoEnLaZona(estacionamiento, zona); //encapsular
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