package com.hdfc.dummy.randomgenerator;

import java.util.Random;

public class IncomeGenerator {

	public static String generator() {

		Random random = new Random();
		int income = (random.nextInt(200) + 1) * 5000;

		return income + "";

	}

}
