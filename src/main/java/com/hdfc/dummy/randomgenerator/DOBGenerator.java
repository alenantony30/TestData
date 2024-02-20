package com.hdfc.dummy.randomgenerator;

import java.time.LocalDate;
import java.util.Random;

public class DOBGenerator {
	
	
	//private String DOB;
	
	public static String generator() {

		Random random = new Random();

		//StringBuilder DOB = new StringBuilder();
		
		int days= random.nextInt(365*30)+random.nextInt(365*20);
		LocalDate currentDate= LocalDate.now();
		LocalDate randomDate=currentDate.minusDays(days);
		
		int day=randomDate.getDayOfMonth();
		int month=randomDate.getMonthValue();
		int year=randomDate.getYear();
		
		String formattedMonth = String.format("%02d", month);
        String formattedDay = String.format("%02d", day);
        
        StringBuilder randomDateString= new StringBuilder();
        
        randomDateString.append(formattedDay);
        randomDateString.append(formattedMonth);
        randomDateString.append(year);
        



		

		return randomDateString.toString();
	}

}
