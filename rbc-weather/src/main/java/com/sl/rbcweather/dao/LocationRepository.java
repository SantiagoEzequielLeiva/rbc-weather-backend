package com.sl.rbcweather.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sl.rbcweather.model.Location;

/**
 * @author Santiago Leiva
 * 02/05/2018
 */

public interface LocationRepository extends JpaRepository<Location, Long> {

}
