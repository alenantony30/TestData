package com.hdfc.dummy.randomgenerator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class YYYYMMDDThhmmssGenerator {
	
	public static String generator() {
		
		LocalDateTime currentDateTime = LocalDateTime.now();

	       
        Random random = new Random();
        int year = currentDateTime.getYear() - random.nextInt(3); 
        int month = random.nextInt(12) + 1; 
        int day = random.nextInt(28) + 1; 
        int hour = random.nextInt(24); 
        int minute = random.nextInt(60); 
        int second = random.nextInt(60); 
        
        String formattedMonth = String.format("%02d", month);
        String formattedDay = String.format("%02d", day);
        String formattedHour = String.format("%02d", hour);
        String formattedMinute = String.format("%02d", minute);
        String formattedSecond = String.format("%02d", second);
        
        StringBuilder randomDate= new StringBuilder();
        
        StringBuilder randomDate2= new StringBuilder();
        
        randomDate.append(year);
        randomDate.append(formattedMonth);
        randomDate.append(formattedDay);
        randomDate.append("T");
        randomDate.append(formattedHour);
        randomDate.append(formattedMinute);
        randomDate.append(formattedSecond);
        
        randomDate2.append(year);
        randomDate2.append("-");
        randomDate2.append(formattedMonth);
        randomDate2.append("-");
        randomDate2.append(formattedDay);
        randomDate2.append(" ");
        randomDate2.append(formattedHour);
        randomDate2.append(":");
        randomDate2.append(formattedMinute);
        randomDate2.append(":");
        randomDate2.append(formattedSecond);
        
        
        return randomDate.toString()+randomDate2.toString();
		
		
		
		
		//return  null;
	}

}
