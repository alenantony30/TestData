package com.hdfc.dummy.service;

import com.hdfc.dummy.entity.RevGeoCode;

public interface IRevGeoCodeService {
	
	public  RevGeoCode generateRevGeoCode() ;
	
	public RevGeoCode add(RevGeoCode revGeoCode);

}
