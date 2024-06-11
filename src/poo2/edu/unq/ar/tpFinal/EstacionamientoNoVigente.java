package poo2.edu.unq.ar.tpFinal;

import java.time.LocalTime;

public class EstacionamientoNoVigente implements IEstadoDeEstacionamiento {

	public void alertaInicioEstacionamiento(AppDeUsuario usuario) throws Exception {
		if (usuario.getSEM().calcularSaldoSuficiente(usuario)) {
			ZonaDeEstacionamiento zona = usuario.getSEM().encontrarZonaEstacionamientoEn(usuario.getLocalizacion());
			Estacionamiento estacionamiento = new EstacionamientoViaApp(usuario, LocalTime.now(),
					usuario.getSEM().getHoraFin(), usuario.getPatente());
			usuario.getSEM().registrarUnNuevoEstacionamientoEnLaZona(estacionamiento, zona);
			usuario.setEstado(new EstacionamientoVigente());
		} else {
			new Exception("No tiene saldo suficiente");
		}
	}

	public void alertaFinEstacionamiento(AppDeUsuario usuario) {
	}

	public boolean estaVigente() {
		return false;
	}

	@Override
	public void vigenciaPara(AppInspector inspector, String patente) {
		inspector.notificarAlSemPorEstacionamientoNoVigente(patente);
	}
}