package poo2.edu.unq.ar.tpFinal;

import java.time.LocalTime;

public class EstacionamientoViaApp extends Estacionamiento {


    private String patente;

    public EstacionamientoViaApp(LocalTime horaInicio, LocalTime horaFin, String patente) {
        super(horaInicio, horaFin);
        this.patente = patente;
    }

    public String getPatenteDeUsuario() {
        return this.patente; 
    }
}