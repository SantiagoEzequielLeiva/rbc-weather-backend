package com.sl.rbcweather.util.yahooQuery;

import lombok.Data;

/**
 * @author Santiago Leiva
 * 07/05/2018
 */

@Data
public class BoundingBox {

	private LatitudeLongitude southWest;
	private LatitudeLongitude northEast;
	
}
