package com.sl.rbcweather.model;

import java.sql.Timestamp;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Santiago Leiva
 * 02/05/2018
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "board")
@Access(AccessType.FIELD)
public class Board extends ParentEntity {
	
	private Location location;
	private Integer temperature;
	private String degreeUnits;
	private String iconPath;
	private Integer code;
	private String description;
	private Timestamp lastCheck;

}
