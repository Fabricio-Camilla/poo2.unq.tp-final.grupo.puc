package poo2.edu.unq.ar.tpFinal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class AppInspector {

	private SEM sem;
	private ZonaDeEstacionamiento estacionamientoAsignado;
	private Set<Infraccion> infraccionesEmitidas;

	public AppInspector(ZonaDeEstacionamiento zonaDeEstacionamiento, SEM sem) {
		this.sem = sem;
		this.estacionamientoAsignado = zonaDeEstacionamiento;
		this.infraccionesEmitidas = new HashSet<Infraccion>();
	}

	public boolean perteneceAlSem(SEM sem) {
		return this.getSEM().equals(sem);
	}

	public boolean estaAsignadoALaZonaDeEstacionamiento(ZonaDeEstacionamiento zonaDeEstacionamiento) {
		return this.getEstacionamientoAsignado().equals(zonaDeEstacionamiento);
	}

	public boolean estaVigenteElEstacionamientoConPatente(String patente) throws Exception {
		return this.getSEM().estaVigenteElEstacionamientoConPatente(patente);
	}

	public void notificarAlSemPorEstacionamientoNoVigente(String patente) throws Exception {
		if (this.estaVigenteElEstacionamientoConPatente(patente)) {
			Infraccion infraccion = new Infraccion(patente, LocalDate.now(), LocalTime.now(), this,
					this.getEstacionamientoAsignado());
			this.infraccionesRegistradas().add(infraccion);
			this.getSEM().registrarInfraccion(infraccion);
		}
	}

	private SEM getSEM() {
		return this.sem;
	}

	public int cantidadDeInfraccionesEmitidas() {
		return this.infraccionesRegistradas().size();
	}

	public Set<Infraccion> infraccionesRegistradas() {
		return this.infraccionesEmitidas;
	}

	public ZonaDeEstacionamiento getEstacionamientoAsignado() {
		return this.estacionamientoAsignado;
	}

}
