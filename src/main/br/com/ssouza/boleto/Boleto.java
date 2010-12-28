package br.com.ssouza.boleto;

import java.math.BigDecimal;
import java.util.Calendar;

import org.apache.commons.lang.Validate;

import br.com.ssouza.util.DataHelper;

public class Boleto {

	private BigDecimal valor;

	private BigDecimal quantidadeMoeda;
	private BigDecimal valorMoeda;
	private String especieMoeda;
	private int codEspecieMoeda;

	private boolean aceite;
	private int numeroDocumento;
	private String especieDocumento;

	private Datas datas;
	private Sacado sacado;
	private Cedente cedente;
	private Banco banco;

	private String[] instrucoes = new String[5];

	private static final Calendar DATA_BASE_FATOR = DataHelper.getPrimeiraHoraDoDia(7, 10, 1997);

	private Boleto() {

	}

	public static Boleto newBoleto() {

		return new Boleto().withCodEspecieMoeda(9).withEspecieMoeda("R$").withAceite(false).withEspecieDocumento("DV");
	}

	public Boleto withAceite(boolean aceite) {

		this.aceite = aceite;
		return this;
	}

	public Boleto withEspecieDocumento(String especieDocumento) {

		this.especieDocumento = especieDocumento;
		return this;
	}

	public Boleto withQuantidadeMoeda(BigDecimal quantidadeMoeda) {

		this.quantidadeMoeda = quantidadeMoeda;
		return this;
	}

	public Boleto withValorMoeda(BigDecimal valorMoeda) {

		this.valorMoeda = valorMoeda;
		return this;
	}

	public Boleto withEspecieMoeda(String especieMoeda) {

		this.especieMoeda = especieMoeda;
		return this;
	}

	public Boleto withCodEspecieMoeda(int codEspecieMoeda) {

		this.codEspecieMoeda = codEspecieMoeda;
		return this;
	}

	public Boleto withValor(BigDecimal valor) {

		this.valor = valor;
		return this;
	}

	public Boleto withDatas(Datas datas) {

		this.datas = datas;
		return this;
	}

	public Boleto withSacado(Sacado sacado) {

		this.sacado = sacado;
		return this;
	}

	public Boleto withCedente(Cedente cedente) {

		this.cedente = cedente;
		return this;
	}

	public Boleto withBanco(Banco banco) {

		this.banco = banco;
		return this;
	}

	public Boleto withNumeroDocumento(int numeroDocumento) {

		this.numeroDocumento = numeroDocumento;
		return this;
	}

	public Boleto withInstrucoes(String... instrucoes) {

		Validate.isTrue(instrucoes.length <= 5, "São permitidas no máximo 5 instruções.");
		this.instrucoes = instrucoes;
		return this;
	}

	public boolean isAceite() {

		return aceite;
	}

	public String getEspecieDocumento() {

		return especieDocumento;
	}

	public BigDecimal getQuantidadeMoeda() {

		return quantidadeMoeda;
	}

	public BigDecimal getValorMoeda() {

		return valorMoeda;
	}

	public String getEspecieMoeda() {

		return especieMoeda;
	}

	public int getCodEspecieMoeda() {

		return codEspecieMoeda;
	}

	public BigDecimal getValor() {

		return valor;
	}

	public String getValorFormatado() {

		Validate.isTrue(BigDecimal.ZERO.compareTo(valor) == -1, "Não foi definido valor para Boleto.");
		return String.format("%011.2f", this.valor).replaceAll("[^0-9]", "");
	}

	public Datas getDatas() {

		return datas;
	}

	public Sacado getSacado() {

		return sacado;
	}

	public Cedente getCedente() {

		return cedente;
	}

	public Banco getBanco() {

		return banco;
	}

	public int getNumeroDocumento() {

		return numeroDocumento;
	}

	public String getNumeroDocumentoFormatado() {

		return String.format("%010d", numeroDocumento);
	}

	public String[] getInstrucoes() {

		Validate.isTrue(instrucoes.length <= 5, "São permitidas no máximo 5 instruções.");
		return instrucoes;
	}

	public String getFatorVencimento() {

		int day = this.datas.getVencimento().get(Calendar.DAY_OF_MONTH);
		int month = this.datas.getVencimento().get(Calendar.MONTH);
		int year = this.datas.getVencimento().get(Calendar.YEAR);

		Calendar dataVencimento = DataHelper.getPrimeiraHoraDoDia(day, month + 1, year);

		long diferencasEmMiliSegundos = dataVencimento.getTimeInMillis() - DATA_BASE_FATOR.getTimeInMillis();
		long diferencasEmDias = diferencasEmMiliSegundos / (1000 * 60 * 60 * 24);

		Validate.isTrue(diferencasEmDias <= 9999, "Data fora do formato aceito!");

		return String.valueOf((int) diferencasEmDias);
	}

}