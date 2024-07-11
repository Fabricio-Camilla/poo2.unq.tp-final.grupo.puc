package poo2.edu.unq.ar.tpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestModoManual {

	private ModoDeUso modo;
	private AppDeUsuario usuario;
	private EstacionamientoNoVigente noVigente;
	private EstacionamientoVigente vigente;
	
	@BeforeEach
	void setUp()  {
		modo = new ModoManual();
		usuario = mock(AppDeUsuario.class);
		noVigente = mock(EstacionamientoNoVigente.class);
		vigente = mock(EstacionamientoVigente.class);
	}

	@Test
	void testModoMandaNotificacionDeInicioEstacionamiento() throws Exception {
		when(usuario.getEstado()).thenReturn(noVigente);
		
		modo.inicioDeEstacionamiento(usuario);
		
		verify(usuario, atLeastOnce()).notificarInicioEstacionamiento("Se realizo inicio de estacionamiento automaticamente");
	}
	
	@Test
	void testModoMandaNotificacionDeFinEstacionamiento() throws Exception {
		when(usuario.getEstado()).thenReturn(vigente);
		
		modo.finDeEstacionamiento(usuario);
		
		verify(usuario, atLeastOnce()).notificarFinEstacionamiento("Se realizo fin de estacionamiento automaticamente");
	}

}
