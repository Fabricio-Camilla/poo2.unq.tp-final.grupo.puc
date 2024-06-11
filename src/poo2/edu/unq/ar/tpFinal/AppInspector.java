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
		return this.sem.equals(sem);
	}

	public boolean estaAsignadoALaZonaDeEstacionamiento(ZonaDeEstacionamiento zonaDeEstacionamiento) {
		return this.estacionamientoAsignado.equals(zonaDeEstacionamiento);
	}

	public boolean estaVigenteElEstacionamientoConPatente(String patente) throws Exception {
		return this.sem.estaVigenteElEstacionamientoConPatente(patente);
	}

	public void recorrerZonaDeEstacionamiento() {
		this.estacionamientoAsignado.revisarVigenciaDeEstacionamientos();
	}

	public void notificarAlSemPorEstacionamientoNoVigente(String patente) {
		Infraccion infraccion = new Infraccion(patente, LocalDate.now(), LocalTime.now(), this,
				this.estacionamientoAsignado);
		this.sem.registrarInfraccion(infraccion);
		this.infraccionesRegistradas().add(infraccion);
	}

	public int cantidadDeInfraccionesEmitidas() {
		return this.infraccionesRegistradas().size();
	}

	public Set<Infraccion> infraccionesRegistradas() {
		return this.infraccionesEmitidas;
	}

}
