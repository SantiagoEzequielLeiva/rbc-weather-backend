package com.sl.rbcweather;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sl.rbcweather.model.Board;
import com.sl.rbcweather.service.BoardService;
import com.sl.rbcweather.service.WeatherService;
import com.sl.rbcweather.service.WeatherServiceYahoo;

/**
 * @author Santiago Leiva
 * 06/05/2018
 */

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
	
	@Autowired
	private BoardService boardService;
	
	private WeatherService weatherService = new WeatherServiceYahoo();

	/**
	 * Hacemos el update de los boards al iniciar la app
	 */
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		List<Board> boards = this.boardService.list();
		
		Date lastCheck = new Date();
		
		for (Board board : boards) {
			this.weatherService.updateBoard(board, lastCheck);
			this.boardService.save(board);
		}
	}
	
}
