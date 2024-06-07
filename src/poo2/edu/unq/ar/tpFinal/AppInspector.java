package poo2.edu.unq.ar.tpFinal;

public class AppInspector {

	private SEM sem;
	private ZonaDeEstacionamiento estacionamientoAsignado;

	public AppInspector(ZonaDeEstacionamiento zonaDeEstacionamiento, SEM sem) {
		this.sem = sem;
		this.estacionamientoAsignado = zonaDeEstacionamiento;
	}

	public boolean perteneceAlSem(SEM sem) {
		return this.sem.equals(sem);
	}

	public boolean estaAsignadoALaZonaDeEstacionamiento(ZonaDeEstacionamiento zonaDeEstacionamiento) {
		return this.estacionamientoAsignado.equals(zonaDeEstacionamiento);
	}

}
