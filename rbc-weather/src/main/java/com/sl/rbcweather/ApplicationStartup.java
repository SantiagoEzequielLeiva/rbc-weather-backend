package com.sl.rbcweather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sl.rbcweather.service.BoardService;

/**
 * @author Santiago Leiva
 * 06/05/2018
 */

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
	
	@Autowired
	private BoardService boardService;

	/**
	 * Hacemos el update de los boards al iniciar la app
	 */
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		this.boardService.updateBoards();
	}
	
}
