package poo2.edu.unq.ar.tpFinal;

import java.time.LocalDateTime;

public class EstacionamientoNoVigente implements IEstadoDeEstacionamiento {
	//solo se utilzaria estando en el modo atuomatico
	public void alertaInicioEstacionamiento(AppDeUsuario usuario) throws Exception {
		if (usuario.cumpleConSaldoParaPagar()) {
			usuario.iniciarEstacionamiento();//encapsular
			usuario.cambiarAEstadoVigente();
		} else {
			new Exception("No tiene saldo suficiente");
		}
	}
	
	public void alertaFinEstacionamiento(AppDeUsuario usuario) throws Exception {
		
	}

	
}