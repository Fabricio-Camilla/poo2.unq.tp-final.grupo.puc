package poo2.edu.unq.ar.tpFinal;

public interface IEstadoDeEstacionamiento {

	public void alertaInicioEstacionamiento(AppDeUsuario usuario) throws Exception;

	public void alertaFinEstacionamiento(AppDeUsuario usuario) throws Exception;

	public boolean estaVigente();

	public void vigenciaPara(AppInspector inspector, String patente);
}
