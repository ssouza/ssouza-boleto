package br.com.ssouza.boleto.banco;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import br.com.ssouza.boleto.Boleto;
import br.com.ssouza.boleto.Cedente;
import br.com.ssouza.boleto.Datas;
import br.com.ssouza.boleto.Sacado;

public class ItauTest {

	private Boleto boleto;
	private Itau banco;

	@Before
	public void setUp() {

		Datas datas = Datas.newDatas().withDocumento(25, 6, 2010).withProcessamento(25, 6, 2010).withVencimento(2, 7,
				2010);

		Cedente cedente = Cedente.newCedente().withNome("Fulano").withAgencia(987).withContaCorrente(77472)
				.withDvContaCorrente('9').withCarteira(175).withNossoNumero(1);

		Sacado sacado = Sacado.newSacado().withNome("Beltrano").withCpfCnpj("000.111.222-33");

		this.banco = new Itau();

		this.boleto = Boleto.newBoleto().withDatas(datas).withCedente(cedente).withSacado(sacado).withValor(
				new BigDecimal(15.50)).withNumeroDocumento(1).withBanco(banco);
	}

	@Test
	public void codigoBanco() {

		assertEquals("341", banco.getCodigo());

		assertEquals("341-7", banco.getCodigoComDv());
	}

	@Test
	public void agenciaFormatadoComQuatroDigitos() {

		String agencia = banco.getAgenciaFormatado(boleto.getCedente());

		assertEquals(4, agencia.length());

		assertEquals("0987", agencia);
	}

	@Test
	public void contaCorrenteFormatadoComCincoDigitos() {

		String conta = banco.getContaCorrenteFormatado(boleto.getCedente());

		assertEquals(5, conta.length());

		assertEquals("77472", conta);
	}

	@Test
	public void carteiraFormatadoComTresDigitos() {

		String carteira = banco.getCarteiraFormatado(boleto.getCedente());

		assertEquals(3, carteira.length());

		assertEquals("175", carteira);
	}

	@Test
	public void nossoNumeroFormatadoComOitoDigitos() {

		String numero = banco.getNossoNumeroFormatado(boleto.getCedente());

		assertEquals(8, numero.length());

		assertEquals("00000001", numero);
	}

	@Test
	public void digitoVerificadorNossoNumero() {

		int dv = banco.getDvNossoNumero(boleto.getCedente());

		assertEquals(3, dv);
	}

	@Test
	public void tentativaDeObterImagem() {

		assertNotNull(this.banco.getImagem());
	}

	@Test
	public void linhaDigitavel() {

		String codigo = banco.geraCodigoLinhaDigitavel(this.boleto);

		assertEquals("34191.75009 00000.130989 77747.260006 5 46510000001550", codigo);
	}

	@Test
	public void codigoDeBarras() {

		String codigo = banco.geraCodigoDeBarras(this.boleto);

		assertEquals("34195465100000015501750000000130987774726000", codigo);
	}

}