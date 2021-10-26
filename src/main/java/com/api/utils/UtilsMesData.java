package com.api.utils;

import java.util.Calendar;
import java.util.Date;

public class UtilsMesData {
	
	public static Date subtrair( Date data, int mes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.MONTH, -mes);
		return calendar.getTime();
	}
	
	public static Date adicionar( Date data, int mes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.MONTH, mes);
		return calendar.getTime();
	}

}
