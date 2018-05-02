package com.sl.rbcweather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sl.rbcweather.model.Location;
import com.sl.rbcweather.service.LocationService;

@RestController
@RequestMapping({"/location"})
public class LocationController {
	
	@Autowired
	private LocationService locationService;
	
	@GetMapping
	public List<Location> locations() {
		return this.locationService.list();
	}

}
