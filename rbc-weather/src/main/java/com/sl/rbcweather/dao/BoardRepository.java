package com.sl.rbcweather.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sl.rbcweather.model.Board;

/**
 * @author Santiago Leiva
 * 03/05/2018
 */

public interface BoardRepository extends JpaRepository<Board, Long> {
	
	@SuppressWarnings("unchecked")
	Board save(Board board);
	
	@Query("SELECT b FROM Board AS b WHERE b.lastCheck IS NULL OR b.lastCheck <= :lastCheck")
	List<Board> findBoardsReadyToUpdate(@Param("lastCheck") Date lastCheck);
	
	@Query("SELECT b FROM Board AS b WHERE b.woeid = :woeid")
	Board findByWoeid(@Param("woeid") String woeid);
	
	@Query("SELECT b FROM Board AS b WHERE b.city = :term OR b.country = :term")
	List<Board> findByTerm(@Param("term") String term);
	
	@Query("SELECT b.woeid FROM Board AS b WHERE b.city = :term OR b.country = :term")
	List<String> getWoeidByTerm(@Param("term") String term);
}
