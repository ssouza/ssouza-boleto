package br.com.ssouza.util;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void data () {

		Calendar calendar = DateUtils.getData(1, 6, 2010);

		assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH));
		assertEquals(5, calendar.get(Calendar.MONTH));
		assertEquals(2010, calendar.get(Calendar.YEAR));
	}

	@Test
	public void primeiraHoraDoDia () {

		Calendar calendar = DateUtils.getPrimeiraHoraDoDia(1, 6, 2010);

		assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH));
		assertEquals(5, calendar.get(Calendar.MONTH));
		assertEquals(2010, calendar.get(Calendar.YEAR));

		assertEquals(0, calendar.get(Calendar.HOUR));
		assertEquals(0, calendar.get(Calendar.MINUTE));
		assertEquals(0, calendar.get(Calendar.SECOND));
		assertEquals(0, calendar.get(Calendar.MILLISECOND));
	}

	@Test
	public void ultimaHoraDoDia () {

		Calendar calendar = DateUtils.getUltimaHoraDoDia(1, 6, 2010);

		assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH));
		assertEquals(5, calendar.get(Calendar.MONTH));
		assertEquals(2010, calendar.get(Calendar.YEAR));

		assertEquals(23, calendar.get(Calendar.HOUR_OF_DAY));
		assertEquals(59, calendar.get(Calendar.MINUTE));
		assertEquals(59, calendar.get(Calendar.SECOND));
		assertEquals(999, calendar.get(Calendar.MILLISECOND));
	}

}