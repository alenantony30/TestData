package com.hdfc.dummy.randomgenerator;

import java.util.Random;

import com.github.javafaker.Faker;

public class FullNameGenerator {

	// private String middleName;

	public static PersonalDetails generator() {
		Faker faker = new Faker();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String fullName = firstName + " " + lastName;
		String email = (firstName +"."+ lastName + "@gmail.com").toLowerCase();

		String[] prefixArray = { "MR.", "MRS.", "MS." };
		String[] relationShipArray= {"Son","Daugther","Father","Mother"};
		Random random = new Random();
		StringBuilder value = new StringBuilder();
		int index = random.nextInt(prefixArray.length);
		value.append(prefixArray[index]);

		PersonalDetails personalDetails = new PersonalDetails();

		personalDetails.setEmailID(email);
		personalDetails.setFirstName(firstName);
		personalDetails.setLastName(lastName);
		personalDetails.setFullName(fullName);
		personalDetails.setPrefix(value.toString());
		
		if(value.toString().equals("MR.")) {
			personalDetails.setGender("M");
			personalDetails.setSex("Male");
		}
		else {
			personalDetails.setGender("F");
			personalDetails.setSex("Female");
		}
		

		personalDetails.setRelation(relationShipArray[random.nextInt(4)]);
		personalDetails.setFatherName(faker.name().fullName());
		personalDetails.setMotherName(faker.name().fullName());
		personalDetails.setGuardian(faker.name().fullName());
		

		return personalDetails;

	}

}
