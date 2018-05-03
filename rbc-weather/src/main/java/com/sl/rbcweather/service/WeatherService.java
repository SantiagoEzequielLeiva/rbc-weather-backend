package com.sl.rbcweather.service;

import java.util.Date;

import com.sl.rbcweather.model.Board;

/**
 * @author Santiago Leiva
 * 02/05/2018
 */

public interface WeatherService {
	
	/**
	 * Se actualiza el board de una locacion en base a su codigo WOEID
	 * @param board
	 * @return Board
	 */
	Board updateBoard(Board board);
	
	/**
	 * Se actualiza el board de una locacion en base a su codigo WOEID
	 * @param board
	 * @param lastCheck
	 * @return Board
	 */
	Board updateBoard(Board board, Date lastCheck);

}
