 package poo2.edu.unq.ar.tpFinal;

public class ModoAutomatico implements ModoDeUso{

	@Override
	public void finDeEstacionamiento(AppDeUsuario appDeUsuario) throws Exception{
		appDeUsuario.realizarAlertaFinEstacionamiento(); //encapsular
		appDeUsuario.notificarFinEstacionamiento("Se realizo fin de estacionamiento automaticamente");
	}

	@Override
	public void inicioDeEstacionamiento(AppDeUsuario appDeUsuario) throws Exception {
		appDeUsuario.realizarAlertaInicioEstacionamiento();
		appDeUsuario.notificarInicioEstacionamiento("Se realizo inicio de estacionamiento automaticamente");
	}

}
