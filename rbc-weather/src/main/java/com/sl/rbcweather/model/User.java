package com.sl.rbcweather.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = { CascadeType.PERSIST })
	@JoinTable(name = "user_board",
			joinColumns = { @JoinColumn(name = "id_user") },
			inverseJoinColumns = { @JoinColumn(name = "id_board") })
	private Set<Board> boards = new HashSet<Board>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Board> getBoards() {
		return boards;
	}

	public void setBoards(Set<Board> boards) {
		this.boards = boards;
	}
	
	public void addBoard(Board board) {
		this.boards.add(board);
	}

}
