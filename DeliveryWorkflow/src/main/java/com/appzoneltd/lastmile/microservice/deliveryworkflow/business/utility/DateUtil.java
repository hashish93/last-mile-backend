package com.appzoneltd.lastmile.microservice.deliveryworkflow.business.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static String getWeekDay(Date date) {
		// Date Format
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.applyPattern("EEEE");
		String MyDate = dateFormat.format(date);
		return MyDate.toUpperCase();
	}

	public static String getWeekDayAfterCountDates(Date currentDate, Integer numberOfDaysAfter) {
		// Date Format
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		c.add(Calendar.DATE, numberOfDaysAfter);
		// Convert to Date
		Date currentDatePlus = c.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.applyPattern("EEEE");
		String MyDate = dateFormat.format(currentDatePlus);
		// return result
		return MyDate.toUpperCase();
	}

	public static Date getDateAfterCountDates(Date currentDate, Integer numberOfDaysAfter) {
		// Date Format
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		c.add(Calendar.DATE, numberOfDaysAfter);

		return c.getTime();
	}

	public static String getStringFromDate(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String stringDate = sdf.format(date);

		return stringDate;

	}

	public static Date addDaysToDate(Date date, Integer days) {
		// Getting CurrentDate
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		// return result
		return cal.getTime();
	}

	public static Date removeDaysToDate(Date date, Integer days) {
		// Getting CurrentDate
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -(days));
		// return result
		return cal.getTime();
	}
	
	

}
