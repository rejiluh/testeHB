package br.com.hbsis.test;

import static org.junit.Assert.assertNotEquals;

import java.io.IOException;

import br.com.hbsis.controller.CidadeController;
import br.com.hbsis.model.Retorno;
import junit.framework.TestCase;

public class CidadeTest extends TestCase {
	public void testeCidadeNaoExistenteAPI() throws IOException {
		Retorno retorno;
		CidadeController cc = new CidadeController();
		retorno = cc.gravarCidade("ABCDEF");
		assertEquals(2, retorno.getCodigo());
	}
	
	public void testeCidadeExistenteAPI() throws IOException {
		Retorno retorno;
		CidadeController cc = new CidadeController();
		retorno = cc.gravarCidade("Blumenau");
		assertNotEquals(2, retorno.getCodigo());
	}
}
