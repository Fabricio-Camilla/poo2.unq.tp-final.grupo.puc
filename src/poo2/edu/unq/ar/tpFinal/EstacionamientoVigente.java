package poo2.edu.unq.ar.tpFinal;

public class EstacionamientoVigente implements IEstadoDeEstacionamiento{
	
	public void alertaFinEstacionamiento(AppDeUsuario usuario){
		usuario.notificarFinEstacionamiento("Alerta fin Estacionamiento");
		usuario.getSEM().indicarFinEstacionamiento(usuario);
		usuario.cambiarAEstadoNoVigente(new EstacionamientoNoVigente());
	}
	
	public void alertaInicioEstacionamiento(AppDeUsuario usuario){
		
	}
	
	public boolean estaVigente() {
		return true;
	}
	
	
	
}
