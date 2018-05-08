package com.sl.rbcweather.util.yahooQuery;

import lombok.Data;

/**
 * @author Santiago Leiva
 * 06/05/2018
 */

@Data
public class Place {
	
	private String lang;
    private String xmlns;
    private String yahoo;
    private String uri;
    private String woeid;
    private PlaceTypeName placeTypeName;
    private String name;
    private Admin country;
    private Admin admin1;
    private Admin admin2;
    private Admin admin3;
    private Locality locality1;
    private Locality locality2;
    private Locality postal;
    private LatitudeLongitude centroid;
    private BoundingBox boundingBox;
    private String areaRank;
    private String popRank;
    private Locality timezone;
    
}
