package poo2.edu.unq.ar.tpFinal;

public class ModoManual implements ModoDeUso{
	//ni en fin ni inicio delegar en el estado de la app y generar el estacionamiento o finalizarlo
	@Override
	public void finDeEstacionamiento(AppDeUsuario appDeUsuario) throws Exception{
		appDeUsuario.getEstado().alertaFinEstacionamiento(appDeUsuario);		
	}

	@Override
	public void inicioDeEstacionamiento(AppDeUsuario appDeUsuario) throws Exception {
		appDeUsuario.getEstado().alertaInicioEstacionamiento(appDeUsuario);
	}
	
}
