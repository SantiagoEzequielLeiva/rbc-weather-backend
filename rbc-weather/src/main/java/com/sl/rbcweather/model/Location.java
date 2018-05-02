package com.sl.rbcweather.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Santiago Leiva
 * 02/05/2018
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "location")
@Access(AccessType.FIELD)
public class Location extends ParentEntity {
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	@Column(name = "woeid", unique = true, nullable = false)
	private String woeid;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWoeid() {
		return woeid;
	}

	public void setWoeid(String woeid) {
		this.woeid = woeid;
	}

}
