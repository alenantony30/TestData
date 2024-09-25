package com.hdfc.dummy.randomgenerator;

import com.github.javafaker.Faker;

public class AddressGenerator {

	public static Address generator() {
		
		Faker faker = new Faker();

		Address address = new Address();
		
		address.setAddressLine1("Apt.No."+faker.address().buildingNumber());
		address.setAddressLine2(faker.address().streetName());
		address.setAddressLine3(faker.address().streetAddress());

		address.setCity(faker.address().city());
		address.setCountry(faker.address().country());
		address.setState(faker.address().state());
		address.setZipCode(faker.address().zipCode());
		
		return address;
	}

}
