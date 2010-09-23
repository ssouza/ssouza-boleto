package br.com.ssouza.boleto.banco;

import java.net.URL;

import org.apache.commons.lang.Validate;

import br.com.ssouza.boleto.Banco;
import br.com.ssouza.boleto.Boleto;
import br.com.ssouza.boleto.Cedente;

public class Itau implements Banco {

	private static final String NUMERO_ITAU = "341";

	private static final String NUMERO_ITAU_COM_DV = "341-7";

	private final DigitoVerificador dv = new DigitoVerificador();

	@Override
	public String getCodigo () {

		return NUMERO_ITAU;
	}

	@Override
	public String getCodigoComDv () {

		return NUMERO_ITAU_COM_DV;
	}

	@Override
	public String geraCodigoDeBarras (Boleto boleto) {

		Cedente cedente = boleto.getCedente();

		StringBuilder codigoDeBarras = new StringBuilder();

		codigoDeBarras.append(getCodigo());
		codigoDeBarras.append(String.valueOf(boleto.getCodEspecieMoeda()));

		codigoDeBarras.append(boleto.getFatorVencimento());
		codigoDeBarras.append(boleto.getValorFormatado());

		codigoDeBarras.append(getCarteiraFormatado(cedente));
		codigoDeBarras.append(getNossoNumeroFormatado(cedente));

		codigoDeBarras.append(getAgenciaFormatado(cedente));
		codigoDeBarras.append(getContaCorrenteFormatado(cedente));

		codigoDeBarras.append("000");

		codigoDeBarras.insert(38, this.dv.geraDVMod10(codigoDeBarras.substring(30, 38)));

		codigoDeBarras.insert(29, this.dv.geraDVMod10(codigoDeBarras.substring(30, 38).concat(
				codigoDeBarras.substring(18, 29))));

		codigoDeBarras.insert(4, this.dv.geraDVMod11(codigoDeBarras.toString()));

		String result = codigoDeBarras.toString();

		Validate.isTrue(result.length() == 44,
				"Erro na geração do código de barras. Número de digitos diferente de 44. Verifique todos os dados.");

		return result;
	}

	@Override
	public String geraCodigoLinhaDigitavel (Boleto boleto) {

		return new LinhaDigitavel().geraLinhaDigitavelPara(boleto);
	}

	@Override
	public String getCarteiraFormatado (Cedente cedente) {

		return String.format("%03d", cedente.getCarteira());
	}

	@Override
	public String getContaCorrenteFormatado (Cedente cedente) {

		return String.format("%05d", cedente.getContaCorrente());
	}

	@Override
	public URL getImagem () {

		return getClass().getResource(String.format("/br/com/ssouza/boleto/banco/imagem/%s.gif", getCodigo()));
	}

	@Override
	public String getNossoNumeroFormatado (Cedente cedente) {

		return String.format("%08d", cedente.getNossoNumero());
	}

	@Override
	public int getDvNossoNumero (Cedente cedente) {

		int dac = 0;

		String campo = String.valueOf(getAgenciaFormatado(cedente)) + cedente.getContaCorrente()
				+ String.valueOf(cedente.getCarteira()) + getNossoNumeroFormatado(cedente);

		int multiplicador = 1;
		int multiplicacao = 0;
		int soma_campo = 0;

		for (int i = 0; i < campo.length(); i++) {
			multiplicacao = Integer.parseInt(campo.substring(i, 1 + i)) * multiplicador;

			if (multiplicacao >= 10) {
				multiplicacao = Integer.parseInt(String.valueOf(multiplicacao).substring(0, 1))
						+ Integer.parseInt(String.valueOf(multiplicacao).substring(1));
			}
			soma_campo = soma_campo + multiplicacao;

			if (multiplicador == 2)
				multiplicador = 1;
			else
				multiplicador = 2;

		}

		dac = 10 - (soma_campo % 10);

		if (dac == 10)
			dac = 0;

		return dac;
	}

	@Override
	public String getAgenciaFormatado (Cedente cedente) {

		return String.format("%04d", cedente.getAgencia());
	}

}