package br.com.ssouza.util;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Test;

public class ParseUtilsTest {

	@Test
	public void dateToString () {

		Calendar calendar = DateUtils.getData(10, 6, 2010);

		String data = ParseUtils.dateToString(calendar.getTime(), DateUtils.PATTERN_DD_MM_YYYY);

		assertEquals("10", data.substring(0, 2));
		assertEquals("06", data.substring(3, 5));
		assertEquals("2010", data.substring(6));
	}

	@Test
	public void bigDecimalToString () {

		String valor = ParseUtils.bigDecimalToString(new BigDecimal(1234.56));

		assertEquals("1.234", valor.split(",")[0]);
		assertEquals("56", valor.split(",")[1]);
	}

}