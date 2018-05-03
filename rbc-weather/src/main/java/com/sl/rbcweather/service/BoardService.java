package com.sl.rbcweather.service;

import java.util.List;

import com.sl.rbcweather.model.Board;

/**
 * @author Santiago Leiva
 * 03/05/2018
 */

public interface BoardService {

	/**
	 * Se obtiene el listado de boards guardados
	 * @return List<Board>
	 */
	List<Board> list();
	
	/**
	 * Se guarda/actualiza un board
	 * @param board
	 * @return Board
	 */
	Board save(Board board);
	
	/**
	 * Se actualizan los datos del board
	 * @param location
	 * @return Board
	 */
	Board updateBoard(Board board);
}
