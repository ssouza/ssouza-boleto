package br.com.ssouza.util;

import java.util.Calendar;
import java.util.Locale;

public class DateUtils {

	public static final String PATTERN_DD_MM_YYYY = "dd/MM/yyyy";

	public static Calendar getDataAtual () {

		return Calendar.getInstance(new Locale("pt", "BR"));
	}

	public static Calendar getData (int dia, int mes, int ano) {

		Calendar calendar = getDataAtual();

		calendar.set(Calendar.DAY_OF_MONTH, dia);
		calendar.set(Calendar.MONTH, mes - 1);
		calendar.set(Calendar.YEAR, ano);

		return calendar;
	}

	public static Calendar getPrimeiraHoraDoDia (int dia, int mes, int ano) {

		Calendar calendar = getData(dia, mes, ano);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar;
	}

	public static Calendar getUltimaHoraDoDia (int dia, int mes, int ano) {

		Calendar calendar = getData(dia, mes, ano);

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);

		return calendar;
	}
}