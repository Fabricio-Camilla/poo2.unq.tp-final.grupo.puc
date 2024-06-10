package poo2.edu.unq.ar.tpFinal;

import java.time.LocalTime;

public class EstacionamientoCompraPuntual extends Estacionamiento {

    private String patente;
    private int cantidadDeHoras;

    public EstacionamientoCompraPuntual(LocalTime horaInicio, String patente,
            int cantidadDeHoras) {
        super(horaInicio, null);
        this.patente = patente;
        this.cantidadDeHoras = cantidadDeHoras;
    }

    @Override
    protected String getPatenteDeUsuario() {
        return this.patente;
    }
}