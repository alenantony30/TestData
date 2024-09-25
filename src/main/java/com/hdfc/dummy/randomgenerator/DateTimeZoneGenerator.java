package com.hdfc.dummy.randomgenerator;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;

public class DateTimeZoneGenerator {
	
	public static String generator() {
		
		  Random random = new Random();

	        // Generate a random year between 2000 and 2022
	        int year = random.nextInt(23) + 2000;

	        // Generate random month, day, hour, minute, second, and millisecond
	        int month = random.nextInt(12) + 1;
	        int day = random.nextInt(28) + 1; // Assuming all months have 28 days for simplicity
	        int hour = random.nextInt(24);
	        int minute = random.nextInt(60);
	        int second = random.nextInt(60);
	        int millisecond = random.nextInt(1000);

	        // Generate random offset in minutes between -720 and 720 (for time zone)
	        int offsetMinutes = random.nextInt(1440) - 720;

	        // Create LocalDateTime object
	        LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute, second, millisecond * 1_000_000);

	        // Create ZoneOffset object
	        ZoneOffset zoneOffset = ZoneOffset.ofTotalSeconds(offsetMinutes * 60);

	        // Format the LocalDateTime using the desired format
	        String formattedDateTime = dateTime.atOffset(zoneOffset).toString();

	        return formattedDateTime;
	    
	}

}
