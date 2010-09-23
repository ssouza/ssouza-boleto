package br.com.ssouza.boleto;

public class GeradorBoletoException extends RuntimeException {

	private static final long serialVersionUID = -6018283985044286716L;

	public GeradorBoletoException(Exception e) {

		super(e);
	}

}