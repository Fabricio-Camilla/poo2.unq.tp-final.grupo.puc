package poo2.edu.unq.ar.tpFinal;

import java.time.LocalTime;

public class PuntoDeVenta {
	
	private SEM sistema;
	private ZonaDeEstacionamiento zona;
	
	public PuntoDeVenta(SEM sistema, ZonaDeEstacionamiento zona) {
		this.sistema = sistema;
		this.zona = zona;
	}

	public void cargarCredito(Double montoACargar, String celular) {
		this.sistema.cargarCredito(montoACargar, celular);
	}

	public EstacionamientoCompraPuntual registarEstacionamiento(String patente, int cantidadDeHoras) throws Exception {
		EstacionamientoCompraPuntual estacionamientoARegistrar = new EstacionamientoCompraPuntual(LocalTime.now(), patente, cantidadDeHoras);
		this.getSem().registrarUnNuevoEstacionamientoEnLaZona(estacionamientoARegistrar, this.getZona());
		return estacionamientoARegistrar;
	}

	private ZonaDeEstacionamiento getZona() {
		return this.zona;
	}

	private SEM getSem() {
		return this.sistema;
	}
}
