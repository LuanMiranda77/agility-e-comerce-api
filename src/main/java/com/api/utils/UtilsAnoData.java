package com.api.utils;

import java.util.Calendar;
import java.util.Date;

public class UtilsAnoData {
	
	public static Date subtrair( Date data, int ano) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.YEAR, -ano);
		return calendar.getTime();
	}
	
	public static Date adicionar( Date data, int ano) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.YEAR, ano);
		return calendar.getTime();
	}

}
