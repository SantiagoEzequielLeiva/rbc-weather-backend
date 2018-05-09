package com.sl.rbcweather.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Santiago Leiva
 * 02/05/2018
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "board")
@Access(AccessType.FIELD)
@Data
@EqualsAndHashCode(exclude = { "users" })
public class Board implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "city", nullable = false)
	private String city;
	
	@Column(name = "country", nullable = false)
	private String country;
	
	@Column(name = "place_type", nullable = false)
	private String placeType;
	
	@Column(name = "woeid", unique = true, nullable = false)
	private String woeid;

	@Column(name = "temperature")
	private Integer temperature;

	@Column(name = "degree_units", length = 1)
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
	
	/**
	 * Se chequea si el board esta listo para actualizar.
	 * Actualmente, se actualiza un board en caso que no tenga algun chequeo hecho o este haya sido hace mas de una hora.
	 * @return Boolean
	 */
	@JsonIgnore
	@Transient
	public Boolean isReadyToUpdate() {
		
		if ( this.lastCheck == null ) {
			return true;
		} else {
			Calendar uncheckedSince = GregorianCalendar.getInstance();
			uncheckedSince.add(Calendar.HOUR_OF_DAY, -1);
			
			return this.lastCheck.before(uncheckedSince.getTime());
		}
	}

}
