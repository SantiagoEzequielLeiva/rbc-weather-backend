package com.sl.rbcweather.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sl.rbcweather.dao.LocationRepository;
import com.sl.rbcweather.model.Location;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository LocationRepository;
	
	@Override
	public List<Location> list() {
		return this.LocationRepository.findAll();
	}

}
