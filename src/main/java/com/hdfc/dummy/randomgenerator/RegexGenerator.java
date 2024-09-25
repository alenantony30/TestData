package com.hdfc.dummy.randomgenerator;

import com.mifmif.common.regex.Generex;

public class RegexGenerator {

	public static String generator(String exp, int min, int max, int limit) {

		Generex generx;
		if (min == 0 && max == 0 && limit == 0) {
			generx = new Generex(exp);
		} else {
			generx = new Generex(exp + "{" + min + "," + max + "}");
		}
		String value;
		System.out.println(value = generx.random());
		return value;
	}

}
