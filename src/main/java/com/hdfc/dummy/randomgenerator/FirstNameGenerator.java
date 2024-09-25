package com.hdfc.dummy.randomgenerator;

import com.github.javafaker.Faker;

public class FirstNameGenerator {

	// private String firstName;

	public static String generator() {
		Faker faker = new Faker();
		String firstName = faker.name().firstName();
		return firstName;

	}

}
