package com.hdfc.dummy.randomgenerator;

import java.util.Random;

public class ObjectUniqueIdGenerator {

	public static String generator() {

		String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuilder value = new StringBuilder();
		
		for (int i = 0; i < 8; i++) {
	          int index = random.nextInt(characters.length());
	          value.append(characters.charAt(index));
	      }
		
		value.append("-");
		for (int i = 0; i < 4; i++) {
	          int index = random.nextInt(characters.length());
	          value.append(characters.charAt(index));
	      }
		
		value.append("-");
		for (int i = 0; i < 4; i++) {
	          int index = random.nextInt(characters.length());
	          value.append(characters.charAt(index));
	      }
		
		value.append("-");
		for (int i = 0; i < 4; i++) {
	          int index = random.nextInt(characters.length());
	          value.append(characters.charAt(index));
	      }
		
		value.append("-");
		
		for (int i = 0; i < 12; i++) {
	          int index = random.nextInt(characters.length());
	          value.append(characters.charAt(index));
	      }
		
		
		return value.toString();

	}

}
