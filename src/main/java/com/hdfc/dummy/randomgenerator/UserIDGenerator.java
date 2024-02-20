package com.hdfc.dummy.randomgenerator;

import java.util.Random;

public class UserIDGenerator {

	public static String generator() {

		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
		StringBuilder userID = new StringBuilder();

		int index = random.nextInt(characters.length());
		userID.append(characters.charAt(index));

		for (int i = 0; i < 5; i++) {
			userID.append(random.nextInt(10));
		}

		return userID.toString();

	}

}
