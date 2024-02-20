package com.hdfc.dummy.randomgenerator;

import java.util.Random;

public class MobileNumberGenerator {
	
public static String generator() {
		
		Random random=new Random();
		
		
		int startingDigitsArray[]= {9,8,7};
		int startingDigitsIndex=random.nextInt(startingDigitsArray.length);
		int startingDigit= startingDigitsArray[startingDigitsIndex];
		
		String s= startingDigit+"";
		StringBuilder mobileNumber= new StringBuilder(s);
		
		for(int i=0; i<9;i++) {
			
			mobileNumber.append(random.nextInt(10));
		}
		
		
		return "91"+mobileNumber.toString();
	}


}
