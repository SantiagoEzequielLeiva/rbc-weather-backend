package com.sl.rbcweather.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.sl.rbcweather.model.Board;
import com.sl.rbcweather.service.BoardService;
import com.sl.rbcweather.util.BoardComparator;

@CrossOrigin
@EnableScheduling
@RestController
public class WebSocketController {
	
	private static final String DESTINATION = "/topic/updates";

	private final SimpMessagingTemplate template;

	@Autowired
	public WebSocketController(SimpMessagingTemplate template) {
		this.template = template;
	}

	@Autowired
	private BoardService boardService;

	@Scheduled(fixedDelay = 1800000)
	public void updateBoards() {
		this.boardService.updateBoards();

		List<Board> boards = this.boardService.list();

		Collections.sort(boards, BoardComparator.CITY);

		this.template.convertAndSend(DESTINATION, boards);
	}
	
	@MessageMapping("/board/new")
	public void newBoard() {
		List<Board> boards = this.boardService.list();
		Collections.sort(boards, BoardComparator.CITY);
		this.template.convertAndSend(DESTINATION, boards);
	}

}
