package poo2.edu.unq.ar.tpFinal;

import java.time.LocalTime;

public class EstacionamientoCompraPuntual extends Estacionamiento {

    private String patente;
    private int cantidadDeHoras;

    public EstacionamientoCompraPuntual(LocalTime horaInicio, LocalTime horaFin, String patente,
            int cantidadDeHoras) {
        super(horaInicio, horaFin);
        this.patente = patente;
        this.cantidadDeHoras = cantidadDeHoras;
    }

    @Override
    protected String getPatenteDeUsuario() {
        return this.patente;
    }
}