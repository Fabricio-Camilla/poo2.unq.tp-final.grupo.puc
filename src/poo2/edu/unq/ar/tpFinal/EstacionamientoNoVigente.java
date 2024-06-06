package poo2.edu.unq.ar.tpFinal;

public class EstacionamientoNoVigente implements IEstadoDeEstacionamiento{
	
	public void alertaInicioEstacionamiento(AppDeUsuario usuario) {
		usuario.notificarInicioEstacionamiento("Alerta inicio Estacionamiento");
	}
	
	public void alertaFinEstacionamiento(AppDeUsuario usuario){
	}
	
	public boolean estaVigente() {
		return false;
	}
}
