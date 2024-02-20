package com.hdfc.dummy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hdfc.dummy.entity.RevGeoCode;

public interface RevGeoCodeRepo extends JpaRepository<RevGeoCode, Integer> {
	
	@Query("SELECT r FROM RevGeoCode r WHERE r.lat = :lat and r.lng = :lng")
	public RevGeoCode getDataByLatAndLng(@Param("lat") String lat,@Param("lng") String lng);

}
