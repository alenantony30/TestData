package com.hdfc.dummy.randomgenerator;

import java.util.Random;

public class UdcGenerator {

	public static String generator() {

		Random random = new Random();

		StringBuilder udc = new StringBuilder("HDFAE");
		for (int i = 0; i < 4; i++) {

			udc.append(random.nextInt(10));

		}

		udc.append("H");

		for (int i = 0; i < 5; i++) {

			udc.append(random.nextInt(10));

		}

		return udc.toString();
	}

}
