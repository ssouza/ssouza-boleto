package br.com.ssouza.boleto;

import java.util.Calendar;

import br.com.ssouza.util.DateUtils;

public class Datas {

	private Calendar documento;
	private Calendar processamento;
	private Calendar vencimento;

	private static final Calendar DATA_MINIMA = DateUtils.getPrimeiraHoraDoDia(7, 10, 1997);
	private static final Calendar DATA_MAXIMA = DateUtils.getPrimeiraHoraDoDia(22, 2, 2025);

	private Datas() {

	}

	public static Datas newDatas () {

		return new Datas().withDocumento(DateUtils.getDataAtual()).withProcessamento(DateUtils.getDataAtual());
	}

	public Datas withDocumento (Calendar documento) {

		if (documento.getTime().before(DATA_MINIMA.getTime())) {
			throw new IllegalArgumentException("O ano do documento deve ser maior do que 1997.");
		}

		if (documento.getTime().after(DATA_MAXIMA.getTime())) {
			throw new IllegalArgumentException("O ano do documento deve ser menor do que 21/02/2025.");
		}

		this.documento = documento;
		return this;
	}

	public Datas withDocumento (int dia, int mes, int ano) {

		return withDocumento(DateUtils.getData(dia, mes, ano));
	}

	public Datas withProcessamento (Calendar processamento) {

		if (processamento.getTime().before(DATA_MINIMA.getTime())) {
			throw new IllegalArgumentException("O ano do processamento deve ser maior do que 1997.");
		}

		if (processamento.getTime().after(DATA_MAXIMA.getTime())) {
			throw new IllegalArgumentException("O ano do processamento deve ser menor do que 21/02/2025.");
		}

		this.processamento = processamento;
		return this;
	}

	public Datas withProcessamento (int dia, int mes, int ano) {

		return withProcessamento(DateUtils.getData(dia, mes, ano));
	}

	public Datas withVencimento (Calendar vencimento) {

		if (vencimento.getTime().before(DATA_MINIMA.getTime())) {
			throw new IllegalArgumentException("O ano do vencimento deve ser maior do que 1997.");
		}

		if (vencimento.getTime().after(DATA_MAXIMA.getTime())) {
			throw new IllegalArgumentException("O ano do vencimento deve ser menor do que 21/02/2025.");
		}

		this.vencimento = vencimento;
		return this;
	}

	public Datas withVencimento (int dia, int mes, int ano) {

		return withVencimento(DateUtils.getData(dia, mes, ano));
	}

	public Calendar getDocumento () {

		return documento;
	}

	public Calendar getProcessamento () {

		return processamento;
	}

	public Calendar getVencimento () {

		return vencimento;
	}

}