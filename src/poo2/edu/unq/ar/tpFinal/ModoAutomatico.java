package poo2.edu.unq.ar.tpFinal;

public class ModoAutomatico implements ModoDeUso{

	@Override
	public void finDeEstacionamiento(AppDeUsuario appDeUsuario) throws Exception{
		appDeUsuario.getEstado().alertaFinEstacionamiento(appDeUsuario); //encapsular
		appDeUsuario.notificarFinEstacionamiento("Se realizo fin de estacionamiento automaticamente");
	}

	@Override
	public void inicioDeEstacionamiento(AppDeUsuario appDeUsuario) throws Exception {
		appDeUsuario.getEstado().alertaInicioEstacionamiento(appDeUsuario);	 //encapsular
		appDeUsuario.notificarInicioEstacionamiento("Se realizo inicio de estacionamiento automaticamente");
	}

}
