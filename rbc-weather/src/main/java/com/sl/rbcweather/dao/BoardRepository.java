package com.sl.rbcweather.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sl.rbcweather.model.Board;

/**
 * @author Santiago Leiva
 * 03/05/2018
 */

public interface BoardRepository extends JpaRepository<Board, Long> {
	
	@SuppressWarnings("unchecked")
	Board save(Board board);
	
	@Query("SELECT b FROM Board b where b.lastCheck is null or b.lastCheck <= ?1")
	List<Board> findBoardsReadyToUpdate(Date lastCheck);
	
	@Query("SELECT b FROM Board b where b.woeid = ?1")
	Board findByWoeid(String woeid);
	
}
