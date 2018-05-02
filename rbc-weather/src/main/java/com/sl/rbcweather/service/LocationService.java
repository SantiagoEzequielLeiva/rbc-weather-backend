package com.sl.rbcweather.service;

import java.util.List;

import com.sl.rbcweather.model.Location;

public interface LocationService {
	
	/**
	 * Se obtiene el listado de locaciones.
	 * @return List<Location>
	 */
	List<Location> list();

}
