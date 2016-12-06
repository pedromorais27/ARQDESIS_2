package br.com.usjt.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import br.com.usjt.model.Dispenser;
import br.com.usjt.model.Movimento;
import br.com.usjt.model.Saque;

public class SaqueTest {
	Saque saque;
	Dispenser dispenser;

	@Before
	public void setUp() throws Exception {
		saque = new Saque(new Movimento());
		dispenser = new Dispenser();
		saque.setConta(206107);
		saque.setAgencia(6234);
	}

	@Test
	public void fazerSaque() {
		assertEquals("testa se o saldo da conta retornado é 20000.0", true, saque.fazerSaque(200));
	}
}
