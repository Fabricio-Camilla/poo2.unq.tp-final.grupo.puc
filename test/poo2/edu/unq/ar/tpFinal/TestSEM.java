package poo2.edu.unq.ar.tpFinal;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TestSEM {

	private SEM sem;
	private Usuario usuario;

	@BeforeEach

	void setUp() {
		sem = new SEM();
		usuario = mock(Usuario.class);
	}

	@Test
	void unSemRegistraAUnUsuario() {
		sem.registrarAlUsuario(usuario);
		assertTrue(sem.tieneRegistradoAlUsuario(usuario));
	}
}
