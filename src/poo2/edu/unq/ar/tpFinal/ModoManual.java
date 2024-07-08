package poo2.edu.unq.ar.tpFinal;

public class ModoManual implements ModoDeUso{
	//ni en fin ni inicio delegar en el estado de la app y generar el estacionamiento o finalizarlo
	@Override
	public void finDeEstacionamiento(AppDeUsuario appDeUsuario) throws Exception{
		appDeUsuario.notificarFinEstacionamiento("Se realizo fin de estacionamiento automaticamente");
	}

	@Override
	public void inicioDeEstacionamiento(AppDeUsuario appDeUsuario) throws Exception {
		appDeUsuario.notificarInicioEstacionamiento("Se realizo inicio de estacionamiento automaticamente");
		
	}
	
}
