package poo2.edu.unq.ar.tpFinal;

public class EstacionamientoVigente implements IEstadoDeEstacionamiento {
	//solamente se utilizaria estando el modo atumatico
	public void alertaFinEstacionamiento(AppDeUsuario usuario) throws Exception {
		usuario.getSEM().finalizarEstacionamiento(usuario.getCelular()); //encapsular
		usuario.cambiarAEstadoNoVigente();
	}

	public void alertaInicioEstacionamiento(AppDeUsuario usuario) {
		System.out.println("Ya tenes un estacionamiento vigente");
	}

	public boolean estaVigente() { //no estaria en ningun estado
		return true;
	}

	
}