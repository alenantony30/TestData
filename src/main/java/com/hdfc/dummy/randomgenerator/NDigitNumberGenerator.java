package com.hdfc.dummy.randomgenerator;

import java.util.Random;

public class NDigitNumberGenerator {

	
public static String generator(int n) {
		
		Random random=new Random();
		
		StringBuilder randomNumber= new StringBuilder();
		
		for(int i=0; i<n;i++) {
			
			randomNumber.append(random.nextInt(10));
		}
		
		
		return randomNumber.toString();
	}
	
}
