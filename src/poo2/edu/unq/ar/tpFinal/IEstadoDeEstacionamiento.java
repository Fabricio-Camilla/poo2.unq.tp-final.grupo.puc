package poo2.edu.unq.ar.tpFinal;

public interface IEstadoDeEstacionamiento {

	public void alertaInicioEstacionamiento(AppDeUsuario usuario) throws Exception;

	public void alertaFinEstacionamiento(SEM sem, String celular) throws Exception;

	public boolean estaVigente();

}
