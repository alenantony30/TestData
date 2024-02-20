package com.hdfc.dummy.randomgenerator;

import java.time.LocalDate;
import java.util.Random;

public class DateIdGenerator {
	
	  public static String generator() {

	        LocalDate currentDate = LocalDate.now();

	        LocalDate startDate = currentDate.plusYears(1); 
	        LocalDate endDate = currentDate.plusYears(2);
 
	        Random random = new Random();
	        long randomDays = startDate.toEpochDay() + random.nextInt((int) (endDate.toEpochDay() - startDate.toEpochDay()));
	        LocalDate randomDate = LocalDate.ofEpochDay(randomDays);
 
	        String formattedDate = randomDate.toString().replace("-", "");

	        return formattedDate;
	    }

}
