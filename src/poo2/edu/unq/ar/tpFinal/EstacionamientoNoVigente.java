package poo2.edu.unq.ar.tpFinal;

import java.time.LocalTime;
import java.util.Optional;

public class EstacionamientoNoVigente implements IEstadoDeEstacionamiento{
	
	public void alertaInicioEstacionamiento(AppDeUsuario usuario) throws Exception {
		usuario.notificarInicioEstacionamiento("Alerta inicio Estacionamiento");
		ZonaDeEstacionamiento zona = usuario.getSEM()
											.getZonasDeEstacionamiento()
											.stream().toList()
											.stream().filter(z -> z.getLocalizacion().equals(usuario.getLocalizacion())).toList().get(0);
		LocalTime horaInicio = LocalTime.now();
		usuario.getSEM().registrarUnNuevoEstacionamientoEnLaZona(new EstacionamientoViaApp(horaInicio, horaInicio.plusHours(1), 20d,usuario.getCelular()), zona);
		usuario.cambiarAEstadoVigente(new EstacionamientoVigente());
	}
	
	public void alertaFinEstacionamiento(AppDeUsuario usuario){
	}
	
	public boolean estaVigente() {
		return false;
	}
}
