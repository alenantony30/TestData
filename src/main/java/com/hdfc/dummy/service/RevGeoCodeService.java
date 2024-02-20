package com.hdfc.dummy.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfc.dummy.entity.RevGeoCode;
import com.hdfc.dummy.repository.RevGeoCodeRepo;

@Service
public class RevGeoCodeService implements IRevGeoCodeService {

	@Autowired
	RevGeoCodeRepo revGeoCodeRepo;

	@Override
	public RevGeoCode generateRevGeoCode() {

		Random random = new Random();
		int value = random.nextInt(11);
		String latArray[] = { "19.128041", "19.1657431", "19.1461175", "19.1652075", "19.1024869", "18.5112380",
				"15.2891602", "12.3251978", "10.0486000", "11.4141910", "11.9369637" };

		String lngArray[] = { "72.929439", "72.9997009", "72.9936309", "72.9628529", "72.8858507", "73.8577286",
				"73.9636252", "76.6315691", "76.3186000", "76.6986828", "79.8283082" };

		String lat = latArray[value];
		String lng = lngArray[value];

		RevGeoCode revGeoCode = revGeoCodeRepo.getDataByLatAndLng(lat, lng);

		System.out.println("Random value is " + value + " and the latitude is " + lat + ", longitude is " + lng);
		System.out.println(revGeoCode.toString());
		System.out.println();
		System.out.println("**************************************************************");
		System.out.println();

		return revGeoCode;
	}

	@Override
	public RevGeoCode add(RevGeoCode revGeoCode) {
		return revGeoCodeRepo.save(revGeoCode);

	}

}
