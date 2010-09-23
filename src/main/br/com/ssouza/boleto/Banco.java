package br.com.ssouza.boleto;

import java.net.URL;

public interface Banco {

	String getCodigo ();

	String getCodigoComDv ();

	URL getImagem ();

	String geraCodigoDeBarras (Boleto boleto);

	String geraCodigoLinhaDigitavel (Boleto boleto);

	String getAgenciaFormatado (Cedente cedente);

	String getContaCorrenteFormatado (Cedente cedente);

	String getCarteiraFormatado (Cedente cedente);

	String getNossoNumeroFormatado (Cedente cedente);

	int getDvNossoNumero (Cedente cedente);

}