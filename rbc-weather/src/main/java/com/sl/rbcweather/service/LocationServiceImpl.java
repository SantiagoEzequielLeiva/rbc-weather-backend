package com.sl.rbcweather.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sl.rbcweather.dao.LocationRepository;
import com.sl.rbcweather.model.Location;

/**
 * @author Santiago Leiva
 * 02/05/2018
 */

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository LocationRepository;
	
	@Override
	public List<Location> list() {
		return this.LocationRepository.findAll();
	}

}
