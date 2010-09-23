package br.com.ssouza.boleto;

public class Cedente {

	private String nome;
	private String cpfCnpj;

	private int agencia;
	private char dvAgencia;
	private long contaCorrente;
	private int carteira;
	private long numConvenio;
	private long nossoNumero;
	private char dvContaCorrente;
	private int codOperacao;
	private int codFornecidoPelaAgencia;

	private Cedente() {

	}

	/**
	 * Cria novo cedente
	 * 
	 * @return
	 */
	public static Cedente newCedente () {

		return new Cedente();
	}

	/**
	 * Devolve o número da agencia sem o digito
	 * 
	 */
	public int getAgencia () {

		return this.agencia;
	}

	/**
	 * Associa uma agencia, SEM o dígito verificador, ao cedente
	 * 
	 * @param agencia
	 */
	public Cedente withAgencia (int agencia) {

		this.agencia = agencia;
		return this;
	}

	/**
	 * Devolve o número da conta corrente sem o digito
	 * 
	 */
	public long getContaCorrente () {

		return this.contaCorrente;
	}

	/**
	 * Associa uma conta corrente, SEM o dígito verificador, ao cedente
	 * 
	 * @param contaCorrente
	 */
	public Cedente withContaCorrente (long contaCorrente) {

		this.contaCorrente = contaCorrente;
		return this;
	}

	/**
	 * Devolve a carteira<br/>
	 * Valor informado pelo banco para identificação do tipo de boleto
	 * 
	 */
	public int getCarteira () {

		return this.carteira;
	}

	/**
	 * Associa uma carteira ao cedente<br/>
	 * Valor informado pelo banco para identificação do tipo de boleto
	 * 
	 * @param carteira
	 */
	public Cedente withCarteira (int carteira) {

		this.carteira = carteira;
		return this;
	}

	/**
	 * Devolve o número do convênio<br/>
	 * Valor que identifica um cedente junto ao seu banco para associar seus boletos<br/>
	 * Valor informado pelo banco
	 * 
	 */
	public long getNumConvenio () {

		return this.numConvenio;
	}

	/**
	 * Associa um número de convênio ao cedente<br/>
	 * Valor que identifica um cedente junto ao seu banco para associar seus boletos<br/>
	 * Valor informado pelo banco
	 * 
	 * @param numConvenio
	 */
	public Cedente withNumConvenio (long numConvenio) {

		this.numConvenio = numConvenio;
		return this;
	}

	/**
	 * Devolve o nosso número<br/>
	 * Valor que o cedente escolhe para manter controle sobre seus boletos. Esse valor serve para o cedente identificar
	 * quais boletos foram pagos ou não. Recomenda-se o uso de números sequenciais, na geração de diversos boletos, para
	 * facilitar a identificação dos boletos pagos
	 * 
	 */
	public long getNossoNumero () {

		return this.nossoNumero;
	}

	/**
	 * Associa o nosso número ao cedente<br/>
	 * Valor que o cedente escolhe para manter controle sobre seus boletos. Esse valor serve para o cedente identificar
	 * quais boletos foram pagos ou não. Recomenda-se o uso de números sequenciais, na geração de diversos boletos, para
	 * facilitar a identificação dos boletos pagos
	 * 
	 * @param nossoNumero
	 */
	public Cedente withNossoNumero (long nossoNumero) {

		this.nossoNumero = nossoNumero;
		return this;
	}

	public String getCpfCnpj () {

		return cpfCnpj;
	}

	public Cedente withCpfCnpj (String cpfCnpj) {

		this.cpfCnpj = cpfCnpj;
		return this;
	}

	/**
	 * Devolve o cedente. (nome do cedente)
	 * 
	 */
	public String getNome () {

		return this.nome;
	}

	/**
	 * Associa um cedente (nome) ao cedente
	 * 
	 * @param cedente
	 */
	public Cedente withNome (String nome) {

		this.nome = nome;
		return this;
	}

	/**
	 * Devolve o digito verificador (DV) da conta corrente
	 * 
	 * @return
	 */
	public char getDvContaCorrente () {

		return this.dvContaCorrente;
	}

	/**
	 * Associa um digito verificador (DV) da conta corrente ao cedente
	 * 
	 * @param dv
	 * @return
	 */
	public Cedente withDvContaCorrente (char dv) {

		this.dvContaCorrente = dv;
		return this;
	}

	/**
	 * Devolve o digito verificador (DV) da agencia
	 * 
	 * @return
	 */
	public char getDvAgencia () {

		return this.dvAgencia;
	}

	/**
	 * Associa um digito verificador (DV) da agencia ao cedente
	 * 
	 * @param dv
	 * @return
	 */
	public Cedente withDvAgencia (char dv) {

		this.dvAgencia = dv;
		return this;
	}

	/**
	 * Devolve o código de operação do cedente.
	 * 
	 * @return
	 */
	public int getCodOperacao () {

		return this.codOperacao;
	}

	/**
	 * Associa um código de operação ao cedente.
	 * 
	 * @param codOperacao
	 * @return
	 */
	public Cedente withCodOperacao (int codOperacao) {

		this.codOperacao = codOperacao;
		return this;
	}

	/**
	 * Devolve o código fornecido pela agência do cedente.
	 * 
	 * @return
	 */
	public int getCodFornecidoPelaAgencia () {

		return this.codFornecidoPelaAgencia;
	}

	/**
	 * Associa um código fornecido pela agência ao cedente.
	 * 
	 * @param codFornecidoPelaAgencia
	 * @return
	 */
	public Cedente withCodFornecidoPelaAgencia (int codFornecidoPelaAgencia) {

		this.codFornecidoPelaAgencia = codFornecidoPelaAgencia;
		return this;
	}

}