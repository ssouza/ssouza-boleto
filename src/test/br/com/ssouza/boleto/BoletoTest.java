package br.com.ssouza.boleto;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Test;

import br.com.ssouza.util.DataHelper;

public class BoletoTest {

	@Test
	public void deveTerAlgunsValoresPadrao() {

		Boleto boleto = Boleto.newBoleto();

		assertEquals("R$", boleto.getEspecieMoeda());
		assertEquals(9, boleto.getCodEspecieMoeda());
		assertEquals(false, boleto.isAceite());
		assertEquals("DV", boleto.getEspecieDocumento());
	}

	@Test
	public void regraFatorVencimentoComPrimeiraHoraDoDia() {

		Calendar vencimento = DataHelper.getPrimeiraHoraDoDia(3, 7, 2000);

		Datas datas = Datas.newDatas().withVencimento(vencimento);

		Boleto boleto = Boleto.newBoleto().withDatas(datas);

		assertEquals("1000", boleto.getFatorVencimento());
	}

	@Test
	public void regraFatorVencimentoComUltimaHoraDoDia() {

		Calendar vencimento = DataHelper.getUltimaHoraDoDia(3, 7, 2000);

		Datas datas = Datas.newDatas().withVencimento(vencimento);

		Boleto boleto = Boleto.newBoleto().withDatas(datas);

		assertEquals("1000", boleto.getFatorVencimento());
	}

	@Test
	public void regraFatorVencimentoDataMaxima() {

		Calendar vencimento = DataHelper.getUltimaHoraDoDia(21, 2, 2025);

		Datas datas = Datas.newDatas().withVencimento(vencimento);

		Boleto boleto = Boleto.newBoleto().withDatas(datas);

		assertEquals("9999", boleto.getFatorVencimento());
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoPermitirFatorVencimentoComDataMuitoAntiga() {

		Datas.newDatas().withDocumento(1, 1, 1996).withProcessamento(1, 1, 1996).withVencimento(1, 2, 1996);
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoPermitirFatorVencimentoComDataMaiorQueOPermitido() {

		Datas.newDatas().withDocumento(1, 1, 2025).withProcessamento(1, 1, 2025).withVencimento(23, 2, 2025);
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoPermitirValorZero() {

		Boleto boleto = Boleto.newBoleto().withValor(BigDecimal.ZERO);

		boleto.getValorFormatado();
	}

	@Test
	public void valorFormatadoComDezDigitos() {

		Boleto boleto = Boleto.newBoleto().withValor(new BigDecimal(12.34));

		assertEquals(10, boleto.getValorFormatado().length());
		assertEquals("0000001234", boleto.getValorFormatado());
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoPermitirMaisDeCincoInstrucoes() {

		Boleto.newBoleto().withInstrucoes("1", "2", "3", "4", "5", "6");
	}

	@Test
	public void permitirCincoInstrucoes() {

		Boleto boleto = Boleto.newBoleto().withInstrucoes("1", "2", "3", "4", "5");

		assertEquals(5, boleto.getInstrucoes().length);
	}

	@Test
	public void permitirNenhumInstrucao() {

		Boleto boleto = Boleto.newBoleto().withInstrucoes();

		assertEquals(0, boleto.getInstrucoes().length);
	}

}