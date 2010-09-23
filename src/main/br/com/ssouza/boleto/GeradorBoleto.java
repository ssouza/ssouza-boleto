package br.com.ssouza.boleto;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperRunManager;

import org.apache.commons.lang.Validate;

import br.com.ssouza.util.DateUtils;
import br.com.ssouza.util.ParseUtils;

public class GeradorBoleto {

	private Boleto boleto;

	public GeradorBoleto(Boleto boleto) {

		Validate.notNull(boleto, "Boleto não pode ser nulo.");

		this.boleto = boleto;
	}

	public void toPDF (HttpServletResponse response) {

		try {

			Map<String, Object> map = getParametros();

			URL url = this.getClass().getResource("/br/com/ssouza/boleto/template.jasper");

			Validate.notNull(url, "Template do boleto não foi localizado");

			byte[] bytes = null;

			bytes = JasperRunManager.runReportToPdf(url.openStream(), map);

			response.setContentType("application/pdf");
			response.setHeader("Pragma", "");
			response.setHeader("Cache-Control", "");
			response.setHeader("Expires", "");
			response.setHeader("Content-Disposition", "inline;boleto.pdf");
			response.setContentLength(bytes.length);

			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();

		} catch (Exception e) {
			throw new GeradorBoletoException(e);
		}
	}

	private Map<String, Object> getParametros () throws IOException {

		Cedente cedente = boleto.getCedente();
		Sacado sacado = boleto.getSacado();
		Banco banco = boleto.getBanco();
		Datas datas = boleto.getDatas();

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("CEDENTE_CPF_CNPJ", cedente.getCpfCnpj());
		map.put("CEDENTE", cedente.getNome());

		StringBuilder agenciaCodigo = new StringBuilder();
		agenciaCodigo.append(banco.getAgenciaFormatado(cedente));
		agenciaCodigo.append("/");
		agenciaCodigo.append(banco.getContaCorrenteFormatado(cedente));
		agenciaCodigo.append("-");
		agenciaCodigo.append(cedente.getDvContaCorrente());

		map.put("CEDENTE_AGENCIA_CODIGO", agenciaCodigo.toString());

		map.put("DATA_VENCIMENTO", ParseUtils.dateToString(datas.getVencimento().getTime(),
				DateUtils.PATTERN_DD_MM_YYYY));

		map.put("DATA_PROCESSAMENTO", ParseUtils.dateToString(datas.getProcessamento().getTime(),
				DateUtils.PATTERN_DD_MM_YYYY));

		map.put("DATA_DOCUMENTO", ParseUtils.dateToString(datas.getDocumento().getTime(),
				DateUtils.PATTERN_DD_MM_YYYY));

		map.put("NUMERO_DOCUMENTO", boleto.getNumeroDocumentoFormatado());

		StringBuilder nossoNumero = new StringBuilder();
		nossoNumero.append(banco.getCarteiraFormatado(cedente));
		nossoNumero.append("/");
		nossoNumero.append(banco.getNossoNumeroFormatado(cedente));
		nossoNumero.append("-");
		nossoNumero.append(banco.getDvNossoNumero(cedente));

		map.put("NOSSO_NUMERO", nossoNumero.toString());

		map.put("ESPECIE_DOCUMENTO", boleto.getEspecieDocumento());

		map.put("ACEITE", boleto.isAceite() ? "S" : "N");

		map.put("CARTEIRA", banco.getCarteiraFormatado(cedente));

		map.put("ESPECIE_MOEDA", boleto.getEspecieMoeda());

		// map.put("QUANTIDADE", boleto.getQuantidadeMoeda());
		// map.put("VALOR_MOEDA", null);

		map.put("VALOR_DOCUMENTO", ParseUtils.bigDecimalToString(boleto.getValor()));

		int i = 1;
		for (String instrucao : boleto.getInstrucoes()) {
			map.put("INSTRUCAO_" + i++, instrucao == null ? "" : instrucao);
		}

		map.put("CODIGO_DIGITAVEL", banco.geraCodigoLinhaDigitavel(boleto));
		map.put("CODIGO", banco.geraCodigoDeBarras(boleto));

		map.put("SACADO", sacado.getNome());
		map.put("SACADO_CPF_CNPJ", sacado.getCpfCnpj());
		map.put("SACADO_LOGRADOURO", sacado.getLogradouro());
		map.put("SACADO_BAIRRO", sacado.getBairro());
		map.put("SACADO_CEP", sacado.getCep());
		map.put("SACADO_CIDADE", sacado.getCidade());
		map.put("SACADO_UF", sacado.getUf());

		map.put("LOGO_BANCO", banco.getImagem());
		map.put("CODIGO_BANCO", banco.getCodigoComDv());

		return map;
	}

}