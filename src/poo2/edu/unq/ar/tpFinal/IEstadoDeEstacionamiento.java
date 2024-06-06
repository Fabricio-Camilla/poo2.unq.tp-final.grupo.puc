package poo2.edu.unq.ar.tpFinal;

public interface IEstadoDeEstacionamiento {
	
	public void alertaInicioEstacionamiento(AppDeUsuario usuario);
	public void alertaFinEstacionamiento(AppDeUsuario usuario);
	public boolean estaVigente();
}
