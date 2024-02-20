package com.hdfc.dummy.randomgenerator;

import java.util.Random;

public class AccountNumberGenerator {

	public static String generator() {

		Random random = new Random();
		StringBuilder accountNumber = new StringBuilder();

		for (int i = 0; i < 14; i++) {

			accountNumber.append(random.nextInt(10));
		}

		return accountNumber.toString();

	}

}
