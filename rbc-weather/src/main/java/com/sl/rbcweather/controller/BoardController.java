package com.sl.rbcweather.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sl.rbcweather.model.Board;
import com.sl.rbcweather.model.User;
import com.sl.rbcweather.service.BoardService;
import com.sl.rbcweather.service.UserService;
import com.sl.rbcweather.util.RestResponse;

@CrossOrigin
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
	public RestResponse addBoard(@PathVariable String username, @RequestBody Board board) {
		this.userService.addBoard(username, board);
		
		return new RestResponse(HttpStatus.OK.value(), "The board has been added");
	}
	
	@DeleteMapping(value = "/{username}/{board}")
	public RestResponse removeBoard(@PathVariable String username, @PathVariable Long board) {
		this.userService.removeBoard(username, this.boardService.getById(board));
		
		return new RestResponse(HttpStatus.OK.value(), "The board has been removed");
	}
	
	@GetMapping(value = "/location/{term}")
	public List<Board> locationBoards(@PathVariable String term) {
		return this.boardService.findByTerm(term.trim().toLowerCase());
	}
	
}
