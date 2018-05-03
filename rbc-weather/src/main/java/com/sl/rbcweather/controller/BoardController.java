package com.sl.rbcweather.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sl.rbcweather.model.Board;
import com.sl.rbcweather.model.User;
import com.sl.rbcweather.service.BoardService;
import com.sl.rbcweather.service.UserService;
import com.sl.rbcweather.util.RestResponse;

@RestController
@RequestMapping({"/boards"})
public class BoardController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<Board> boards() {
		return this.boardService.list();
	}
	
	@GetMapping(value = "/{username}")
	public List<Board> userBoards(@PathVariable String username) {
		User user = this.userService.getByUsername(username);
		
		return new ArrayList<Board>(user.getBoards());
	}
	
	@PostMapping(value = "/{username}")
	public RestResponse addBoard(@PathVariable String username, @RequestBody Board board) throws JsonParseException, JsonMappingException, IOException {
		this.userService.addBoard(username, board);
		
		return new RestResponse(HttpStatus.OK.value(), "The board has been added");
	}
}