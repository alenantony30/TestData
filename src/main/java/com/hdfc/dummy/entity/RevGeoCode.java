package com.hdfc.dummy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class RevGeoCode {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
	private String houseNumber;
	private String houseName;
	private String poi;
	private String poiDist;
	private String street;
	private String streetDist;
	private String subSubLocality;
	private String subLocality;
	private String locality;
	private String village;
	private String district;
	private String subDistrict;
	private String city;
	private String state;
	private String pincode;
	private String lat;
	private String lng;
	private String area;
	private String formattedAddress;
	

}
