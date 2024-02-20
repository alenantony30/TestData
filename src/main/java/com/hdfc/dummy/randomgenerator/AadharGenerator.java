package com.hdfc.dummy.randomgenerator;

import java.util.Random;

public class AadharGenerator {

	//private String aadharNo;
	
	
	public static String generator() {
		
		Random random=new Random();
		
		StringBuilder vid= new StringBuilder();
		
		for(int i=0; i<12;i++) {
			
			vid.append(random.nextInt(10));
		}
		
		
		return vid.toString();
	}

}
