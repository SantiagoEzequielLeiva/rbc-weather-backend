package com.sl.rbcweather.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sl.rbcweather.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
