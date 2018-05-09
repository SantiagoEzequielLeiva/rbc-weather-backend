package com.sl.rbcweather.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
	 * @param woeidToDiscard
	 * @return List<Board>
	 * @throws Exception 
	 */
	public List<Board> findByTerm(String term, List<String> woeidToDiscard) throws Exception {
		List<Board> boards = new ArrayList<Board>();
		ObjectMapper mapper = new ObjectMapper().enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

		String query = prepareQuery(term, woeidToDiscard);
		
		StringBuilder sbUrl = new StringBuilder();
		
		sbUrl.append(LOCATION_FINDER_BASE_URL)
			.append("?q=")
			.append(query)
			.append("&format=json");
		
		URL url = new URL( sbUrl.toString() );
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
			board.setPlaceType(place.getPlaceTypeName().getContent());
			board.setWoeid(place.getWoeid());
			
			boards.add( board );
		}
		
		return boards;
	}
	
	private String prepareQuery(String term, List<String> woeidToDiscard) throws Exception {
		StringBuilder sbQuery = new StringBuilder();
		
		sbQuery.append("select *")
			.append(" from geo.places")
			.append(" where text =")
			.append(" \"%")
			.append(term)
			.append("%\"");
		
		if ( !woeidToDiscard.isEmpty() ) {
			sbQuery.append(" and place.woeid")
				.append(" not in (")
				.append( woeidToDiscard.toString().replaceAll("[\\[.\\]]", "") )
				.append(")");
		}
		
		return URLEncoder.encode(sbQuery.toString(), "UTF-8");
	}

}
