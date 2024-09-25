package com.hdfc.dummy.randomgenerator;

import java.util.Random;

public class PanGenerator {

	// private String panNo;

	public static String generator() {

		Random random = new Random();

		StringBuilder pan = new StringBuilder();

		for (int i = 0; i < 3; i++) {

			pan.append((char) ('A' + random.nextInt(26)));
		}

		pan.append('P');
		pan.append((char) ('A' + random.nextInt(26)));

		for (int i = 0; i < 4; i++) {

			pan.append(random.nextInt(10));
		}
		pan.append((char) ('A' + random.nextInt(26)));
		return pan.toString();
	}
}
