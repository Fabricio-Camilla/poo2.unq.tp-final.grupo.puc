package poo2.edu.unq.ar.tpFinal;

public class AppInspector {

	private SEM sem;

	public AppInspector(SEM sem) {
		this.sem = sem;
	}

	public boolean perteneceAlSem(SEM sem) {
		return this.sem.equals(sem);
	}

}
