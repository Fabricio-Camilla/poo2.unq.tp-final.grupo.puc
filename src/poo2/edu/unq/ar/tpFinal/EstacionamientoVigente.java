package poo2.edu.unq.ar.tpFinal;

public class EstacionamientoVigente implements IEstadoDeEstacionamiento {
	//solamente se utilizaria estando el modo atumatico
	public void alertaFinEstacionamiento(SEM sem, String celular) throws Exception {
		sem.finalizarEstacionamiento(celular); 
		//usuario.cambiarAEstadoNoVigente();
	}

	public void alertaInicioEstacionamiento(AppDeUsuario usuario) {
		System.out.println("Ya tenes un estacionamiento vigente");
	}

	public boolean estaVigente() { //no estaria en ningun estado
		return true;
	}

	
}