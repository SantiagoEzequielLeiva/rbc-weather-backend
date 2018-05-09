package com.sl.rbcweather.service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sl.rbcweather.dao.BoardRepository;
import com.sl.rbcweather.model.Board;
import com.sl.rbcweather.util.LocationFinder;

/**
 * @author Santiago Leiva
 * 03/05/2018
 */

@Transactional
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	private WeatherService weatherService = new WeatherServiceYahoo();

	@Override
	public List<Board> list() {
		return this.boardRepository.findAll();
	}
	
	@Override
	public Board getById(Long id) {
		return this.boardRepository.findById(id).get();
	}
	
	@Override
	public Board save(Board board) {
		return this.boardRepository.save(board);
	}

	@Override
	public Board updateBoard(Board board) {

		if ( board.isReadyToUpdate() ) {
			board = weatherService.updateBoard(board);
			save(board);
		}
		
		return board;
	}

	@Override
	public List<Board> boardsReadyToUpdate() {
		Calendar lastCheckCalendar = GregorianCalendar.getInstance();
		
		lastCheckCalendar.add(Calendar.HOUR, -1);
		
		return this.boardRepository.findBoardsReadyToUpdate( lastCheckCalendar.getTime() );
	}

	@Override
	public Board findByWoeid(String woeid) {
		return this.boardRepository.findByWoeid(woeid);
	}

	@Override
	public List<String> getWoeidByTerm(String term) {
		return this.boardRepository.getWoeidByTerm(term);
	}

	@Override
	public List<Board> findByTerm(String term) {
		LocationFinder locationFinder = new LocationFinder();
		
		List<Board> boards = this.boardRepository.findByTerm(term);
		
		try {
			boards.addAll(locationFinder.findByTerm(term, this.getWoeidByTerm(term)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return boards;
	}

}
