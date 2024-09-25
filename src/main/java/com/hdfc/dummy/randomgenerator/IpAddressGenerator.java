package com.hdfc.dummy.randomgenerator;

import java.util.Random;

public class IpAddressGenerator {

	public static String generator() {
		Random random = new Random();

		int part1 = random.nextInt(256);
		int part2 = random.nextInt(256);
		int part3 = random.nextInt(256);
		int part4 = random.nextInt(256);

		String ipAddress = String.format("%d.%d.%d.%d", part1, part2, part3, part4);

		return ipAddress;

	}
}