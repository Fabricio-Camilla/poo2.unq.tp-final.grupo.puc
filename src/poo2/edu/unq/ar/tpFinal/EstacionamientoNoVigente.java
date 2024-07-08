package poo2.edu.unq.ar.tpFinal;

import java.time.LocalDateTime;

public class EstacionamientoNoVigente implements IEstadoDeEstacionamiento {
	//solo se utilzaria estando en el modo atuomatico
	public void alertaInicioEstacionamiento(AppDeUsuario usuario) throws Exception {
		if (usuario.cumpleConSaldoParaPagar()) {
			ZonaDeEstacionamiento zona = usuario.pedirZonaDeEstacionamientoValida();
			Estacionamiento estacionamiento = new EstacionamientoViaApp(usuario, LocalDateTime.now(), //mover a que lo haga el sem y no aca
					usuario.horaDeCierreDelSistema(), usuario.getPatente());
			usuario.iniciarEstacionamiento(estacionamiento, zona);//encapsular
			usuario.cambiarAEstadoVigente();
		} else {
			new Exception("No tiene saldo suficiente");
		}
	}
	
	public void alertaFinEstacionamiento(AppDeUsuario usuario) throws Exception {
		
	}

	
}