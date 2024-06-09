package poo2.edu.unq.ar.tpFinal;

public class ModoManual implements ModoDeUso{

	@Override
	public void finDeEstacionamiento(AppDeUsuario appDeUsuario) throws Exception{
		appDeUsuario.getEstado().alertaFinEstacionamiento(appDeUsuario);		
	}

	@Override
	public void inicioDeEstacionamiento(AppDeUsuario appDeUsuario) throws Exception {
		appDeUsuario.getEstado().alertaInicioEstacionamiento(appDeUsuario);
	}
	
}
