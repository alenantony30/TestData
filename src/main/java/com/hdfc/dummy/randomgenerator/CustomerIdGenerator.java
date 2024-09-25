package com.hdfc.dummy.randomgenerator;

import java.util.Random;

public class CustomerIdGenerator {
	
	public static String generator() {

		Random random = new Random();
		StringBuilder customerId = new StringBuilder();

		for (int i = 0; i < 9; i++) {

			customerId.append(random.nextInt(10));
		}

		return customerId.toString();

	}

}
