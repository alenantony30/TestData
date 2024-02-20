package com.hdfc.dummy.randomgenerator;

import com.github.javafaker.Faker;

public class LastNameGenerator {

	
	//private String lastName;
	
	public static String generator() {
		Faker faker = new Faker();
		String lastName = faker.name().lastName();
		return lastName;

	}

}
