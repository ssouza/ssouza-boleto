package br.com.ssouza.boleto;

public class Sacado {

	private String nome;
	private String cpfCnpj;
	private String logradouro;
	private String bairro;
	private String cep;
	private String cidade;
	private String uf;

	private Sacado() {

	}

	public static Sacado newSacado () {

		return new Sacado();
	}

	public Sacado withNome (String nome) {

		this.nome = nome;
		return this;
	}

	public Sacado withCpfCnpj (String cpfCnpj) {

		this.cpfCnpj = cpfCnpj;
		return this;
	}

	public Sacado withLogradouro (String logradouro) {

		this.logradouro = logradouro;
		return this;
	}

	public Sacado withBairro (String bairro) {

		this.bairro = bairro;
		return this;
	}

	public Sacado withCep (String cep) {

		this.cep = cep;
		return this;
	}

	public Sacado withCidade (String cidade) {

		this.cidade = cidade;
		return this;
	}

	public Sacado withUf (String uf) {

		this.uf = uf;
		return this;
	}

	public String getNome () {

		return nome;
	}

	public String getCpfCnpj () {

		return cpfCnpj;
	}

	public String getLogradouro () {

		return logradouro;
	}

	public String getBairro () {

		return bairro;
	}

	public String getCep () {

		return cep;
	}

	public String getCidade () {

		return cidade;
	}

	public String getUf () {

		return uf;
	}

}