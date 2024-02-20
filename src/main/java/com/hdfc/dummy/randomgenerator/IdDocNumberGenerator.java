package com.hdfc.dummy.randomgenerator;

import java.util.Random;

public class IdDocNumberGenerator {

	public static String generator() {

		Random random = new Random();

		StringBuilder docNum = new StringBuilder("ABV00");
		for (int i = 0; i < 3; i++) {
			docNum.append(random.nextInt(10));
		}
		return docNum.toString();
	}
}
