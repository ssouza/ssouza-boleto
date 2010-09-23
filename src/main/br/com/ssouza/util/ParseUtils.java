package br.com.ssouza.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ParseUtils {

	private static final Locale LOCALE = new Locale("pt", "BR");

	public static String dateToString (Date data, String padrao) {

		if (data != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(padrao, LOCALE);
			return simpleDateFormat.format(data);
		} else {
			return "";
		}
	}

	public static String bigDecimalToString (BigDecimal valor) {

		DecimalFormat f = (DecimalFormat) DecimalFormat.getInstance(LOCALE);
		f.applyPattern("#,##0.00;-#,##0.00");

		return f.format(valor);

	}

}
