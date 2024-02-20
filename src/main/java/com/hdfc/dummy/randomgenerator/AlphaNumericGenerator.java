package com.hdfc.dummy.randomgenerator;

import java.util.Random;

public class AlphaNumericGenerator {
	
	
public static String generator(int length) {
		
	  String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
      Random random = new Random();
      StringBuilder value = new StringBuilder();

      for (int i = 0; i < length; i++) {
          int index = random.nextInt(characters.length());
          value.append(characters.charAt(index));
      }
		
		
		return value.toString();
		
}

}
