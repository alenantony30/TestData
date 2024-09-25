package com.hdfc.dummy.randomgenerator;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TimeStampInMillisecondsGenrator {

	public static String generator() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		LocalDateTime lowerBound = currentDateTime.minusYears(2);

		Instant lowerBoundInstant = lowerBound.toInstant(ZoneOffset.UTC);
		Instant currentInstant = currentDateTime.toInstant(ZoneOffset.UTC);

		Long randomTimestamp = generateRandomTimestampInRange(lowerBoundInstant.toEpochMilli(),
				currentInstant.toEpochMilli());

		return randomTimestamp.toString();
	}

	private static long generateRandomTimestampInRange(long lowerBound, long upperBound) {
		return lowerBound + (long) (Math.random() * (upperBound - lowerBound + 1));
	}
	
    public static long roundToTenDigits(long number) {
        long divisor = (long) Math.pow(10, Math.max(0, String.valueOf(number).length() - 10));
        return Math.round((double) number / divisor) * divisor;
    }

}
