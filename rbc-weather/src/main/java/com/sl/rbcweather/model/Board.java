package com.sl.rbcweather.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Santiago Leiva
 * 02/05/2018
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "board")
@Access(AccessType.FIELD)
public class Board extends ParentEntity {
	
	@Column(name = "city", nullable = false)
	private String city;
	
	@Column(name = "country", nullable = false)
	private String country;
	
	@Column(name = "woeid", unique = true, nullable = false)
	private String woeid;

	@Column(name = "temperature")
	private Integer temperature;

	@Column(name = "degree_units")
	private String degreeUnits;

	@Column(name = "icon_path")
	private String iconPath;

	@Column(name = "code")
	private Integer code;

	@Column(name = "description")
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_check")
	private Date lastCheck;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			},
			mappedBy = "boards")
	private Set<User> users = new HashSet<User>();

	public Board() {}

	public Board(String city, String country, String woeid, Integer temperature, String degreeUnits, String iconPath,
			Integer code, String description, Date lastCheck) {
		super();
		this.city = city;
		this.country = country;
		this.woeid = woeid;
		this.temperature = temperature;
		this.degreeUnits = degreeUnits;
		this.iconPath = iconPath;
		this.code = code;
		this.description = description;
		this.lastCheck = lastCheck;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getWoeid() {
		return woeid;
	}

	public void setWoeid(String woeid) {
		this.woeid = woeid;
	}

	public Integer getTemperature() {
		return temperature;
	}

	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}

	public String getDegreeUnits() {
		return degreeUnits;
	}

	public void setDegreeUnits(String degreeUnits) {
		this.degreeUnits = degreeUnits;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getLastCheck() {
		return lastCheck;
	}

	public void setLastCheck(Date lastCheck) {
		this.lastCheck = lastCheck;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
