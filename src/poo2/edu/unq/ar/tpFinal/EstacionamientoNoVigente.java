package poo2.edu.unq.ar.tpFinal;

import java.time.LocalTime;

public class EstacionamientoNoVigente implements IEstadoDeEstacionamiento {
	//solo se utilzaria estando en el modo atuomatico
	public void alertaInicioEstacionamiento(AppDeUsuario usuario) throws Exception {
		if (usuario.cumpleConSaldoParaPagar()) {
			ZonaDeEstacionamiento zona = usuario.getSEM().encontrarZonaEstacionamientoEn(usuario.getLocalizacion()); //delgar en el gps
			Estacionamiento estacionamiento = new EstacionamientoViaApp(usuario, LocalTime.now(),
					usuario.getSEM().getHoraFin(), usuario.getPatente());
			usuario.iniciarEstacionamiento(estacionamiento, zona);//encapsular
			usuario.cambiarAEstadoVigente();
		} else {
			new Exception("No tiene saldo suficiente"); 
		}
	}
//Igps preguntarle una zona valida que esta en mi localizacion

	public boolean estaVigente() {
		return false;
	}
	@Override
	public void alertaFinEstacionamiento(SEM sem, String celular) throws Exception {
		
	}

	
}