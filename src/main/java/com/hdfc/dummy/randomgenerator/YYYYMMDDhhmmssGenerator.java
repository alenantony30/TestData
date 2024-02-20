package com.hdfc.dummy.randomgenerator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class YYYYMMDDhhmmssGenerator {
	
    public static String generator() {
      
        LocalDateTime currentDateTime = LocalDateTime.now();

       
        Random random = new Random();
        int year = currentDateTime.getYear() - random.nextInt(3); 
        int month = random.nextInt(12) + 1; 
        int day = random.nextInt(28) + 1; 
        int hour = random.nextInt(24); 
        int minute = random.nextInt(60); 
        int second = random.nextInt(60); 
        LocalDateTime randomDateTime = LocalDateTime.of(year, month, day, hour, minute, second);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return randomDateTime.format(formatter);
    }

}
