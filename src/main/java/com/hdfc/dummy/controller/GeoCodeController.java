package com.hdfc.dummy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.dummy.entity.RevGeoCode;
import com.hdfc.dummy.randomgenerator.RandomGenrator;
import com.hdfc.dummy.service.IRevGeoCodeService;

@RestController
@RequestMapping("/geocode")
public class GeoCodeController {
	
	@Autowired
	IRevGeoCodeService iRevGeoCodeService;
	
	@PostMapping("/add")
	public RevGeoCode add(@RequestBody RevGeoCode revGeoCode) {
		return iRevGeoCodeService.add(revGeoCode);
	}
	
	@GetMapping("/generate")
	public RevGeoCode generate() {
		
		
		
		return iRevGeoCodeService.generateRevGeoCode();
	}
	
	@GetMapping("/generateFields")
	public void generateData() {
		RandomGenrator randomGenrator =new RandomGenrator(iRevGeoCodeService);
		
		randomGenrator.randomValueGenerator();
	}
	

}
