package com.ninza.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class JavaUtility {

	public int getRandomNumber() {

		Random random = new Random();
		int randomNumber = random.nextInt(5000);
		return randomNumber;

	}
	
	public String getRandomString(int length) {

	    String randomString = UUID.randomUUID().toString().replace("-", "").replaceAll("[0-9]", "");
	    return randomString.substring(0, length);
	    
	}

	public String getCurrentDate() { 
	      
	     Date date=new Date(); 
	     SimpleDateFormat sim=new SimpleDateFormat("dd-MM-yyyy"); 
	     String currentDate = sim.format(date); 
	     return currentDate; 
	      
	}

	public String getRequiredDate(int days) {

		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
		sim.format(date);
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String dateRequired = sim.format(cal.getTime());
		return dateRequired;

	}
}
