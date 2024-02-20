package com.hdfc.dummy.randomgenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class DDMMYYYYGenerator {

	public static String generator() {

		{
			LocalDate currentDate = LocalDate.now();
			Random random = new Random();
			int randomDays = random.nextInt(3 * 365) + 1;
			LocalDate pastDate = currentDate.minusDays(randomDays);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String formattedDate = pastDate.format(formatter);

			return formattedDate;
		}

	}

}
