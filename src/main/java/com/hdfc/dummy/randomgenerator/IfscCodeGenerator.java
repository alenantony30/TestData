package com.hdfc.dummy.randomgenerator;

import java.util.Random;

public class IfscCodeGenerator {
	
	
	public static String generator() {

		Random random = new Random();
		StringBuilder ifscCode = new StringBuilder("HDFC000");

		for (int i = 0; i < 4; i++) {

			ifscCode.append(random.nextInt(7));
		}

		return ifscCode.toString();

	}

}
