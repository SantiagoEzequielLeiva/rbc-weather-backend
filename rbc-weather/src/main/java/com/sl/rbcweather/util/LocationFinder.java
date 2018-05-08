package com.sl.rbcweather.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sl.rbcweather.model.Board;
import com.sl.rbcweather.util.yahooQuery.Place;
import com.sl.rbcweather.util.yahooQuery.YahooQueryResponse;

/**
 * @author Santiago Leiva
 * 07/05/2018
 */

public class LocationFinder {
	
	private static final String LOCATION_FINDER_BASE_URL = "http://query.yahooapis.com/v1/public/yql";
	
	/**
	 * Se retorna un listado de Boards buscando primero las locaciones que coincidan con el termino ingresado.
	 * @param term
	 * @return List<Board>
	 * @throws IOException 
	 */
	public List<Board> findByTerm(String term) throws IOException {
		List<Board> boards = new ArrayList<Board>();
		ObjectMapper mapper = new ObjectMapper().enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

		URL url = new URL( prepareQueryUrl(term) );
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		
		BufferedReader in = new BufferedReader( new InputStreamReader(connection.getInputStream()) );
		
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		while ( (inputLine = in.readLine()) != null )
			response.append(inputLine);
			
		in.close();

		YahooQueryResponse queryResponse = mapper.readValue(response.toString(), YahooQueryResponse.class);
		
		for (Place place : queryResponse.getQuery().getResults().getPlace()) {
			Board board = new Board();
			
			board.setCity(place.getName());
			board.setCountry(place.getCountry().getContent());
			board.setWoeid(place.getWoeid());
			
			boards.add( board );
		}
		
		return boards;
	}
	
	private String prepareQueryUrl(String term) throws UnsupportedEncodingException {
		String urlString = LOCATION_FINDER_BASE_URL;
		
		urlString += "?q=";
		urlString += URLEncoder.encode("select * from geo.places where text = \"%" + term + "%\"", "UTF-8");
		urlString += "&format=json";
		
		return urlString;
	}

}
