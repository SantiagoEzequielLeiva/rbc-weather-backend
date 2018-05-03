package com.sl.rbcweather.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sl.rbcweather.model.Board;

/**
 * @author Santiago Leiva
 * 03/05/2018
 */

public interface BoardRepository extends JpaRepository<Board, Long> {
	
	@SuppressWarnings("unchecked")
	Board save(Board board);

}
