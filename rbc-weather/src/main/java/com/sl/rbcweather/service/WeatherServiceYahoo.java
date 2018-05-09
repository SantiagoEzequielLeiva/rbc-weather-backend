package com.sl.rbcweather.service;

import java.io.IOException;
import java.util.Date;

import javax.xml.bind.JAXBException;

import com.github.fedy2.weather.YahooWeatherService;
import com.github.fedy2.weather.data.Channel;
import com.github.fedy2.weather.data.unit.DegreeUnit;
import com.sl.rbcweather.model.Board;

/**
 * @author Santiago Leiva
 * 03/05/2018
 */

public class WeatherServiceYahoo implements WeatherService {
	
	private YahooWeatherService weatherService;
	
	@Override
	public Board updateBoard(Board board) {
		try {
			this.weatherService = new YahooWeatherService();
			
			Channel channel = this.weatherService.getForecast(board.getWoeid(), DegreeUnit.FAHRENHEIT);
			
			board.setTemperature(channel.getItem().getCondition().getTemp());
			board.setDegreeUnits(Character.toString(channel.getUnits().getTemperature().name().charAt(0)));
			board.setCode(channel.getItem().getCondition().getCode());
			board.setDescription(channel.getItem().getCondition().getText());
			board.setIconPath("http://l.yimg.com/a/i/us/we/52/".concat(String.valueOf(channel.getItem().getCondition().getCode())).concat(".gif"));
			board.setLastCheck(new Date());
			
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
		
		return board;
	}

}
