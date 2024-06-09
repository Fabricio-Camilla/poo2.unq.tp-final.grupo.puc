package poo2.edu.unq.ar.tpFinal;

public class ModoAutomatico implements ModoDeUso{

	@Override
	public void finDeEstacionamiento(AppDeUsuario appDeUsuario) {
		appDeUsuario.getEstado().alertaFinEstacionamiento(appDeUsuario);
		appDeUsuario.notificarInicioEstacionamiento("Se realizo fin de estacionamiento automaticamente");
	}

	@Override
	public void inicioDeEstacionamiento(AppDeUsuario appDeUsuario) throws Exception {
		appDeUsuario.getEstado().alertaInicioEstacionamiento(appDeUsuario);	
		appDeUsuario.notificarInicioEstacionamiento("Se realizo inicio de estacionamiento automaticamente");
	}

}
