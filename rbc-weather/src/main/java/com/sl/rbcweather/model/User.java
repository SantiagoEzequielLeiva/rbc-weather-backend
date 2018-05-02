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
@Table(name = "user")
@Access(AccessType.FIELD)
public class User extends ParentEntity {
	
	@Column(name = "username", unique = true, nullable = false)
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
