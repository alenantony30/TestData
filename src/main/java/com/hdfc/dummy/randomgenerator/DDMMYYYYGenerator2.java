package com.hdfc.dummy.randomgenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class DDMMYYYYGenerator2 {

    public static String generator() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Set the lower and upper bounds for the random date within the last 2 years
        LocalDate lowerBound = currentDate.minusYears(2);

        // Generate a random date within the specified range
        LocalDate randomDate = generateRandomDateInRange(lowerBound, currentDate);

        // Format the date as DDMMYYYY
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        return randomDate.format(formatter);
    }

    private static LocalDate generateRandomDateInRange(LocalDate lowerBound, LocalDate upperBound) {
        Random random = new Random();
        int minDay = (int) lowerBound.toEpochDay();
        int maxDay = (int) upperBound.toEpochDay();
        int randomDay = minDay + random.nextInt(maxDay - minDay + 1);
        return LocalDate.ofEpochDay(randomDay);
    }
	
}
