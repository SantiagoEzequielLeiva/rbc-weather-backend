package com.sl.rbcweather.service;

import java.util.List;

import com.sl.rbcweather.model.Board;
import com.sl.rbcweather.model.User;

/**
 * @author Santiago Leiva
 * 03/05/2018
 */

public interface UserService {
	
	/**
	 * Se crea el usuario en caso que no exista y se actualiza si existe.
	 * @param user
	 * @return User
	 */
	User save(User user);
	
	/**
	 * Se obtiene la lista de usuarios guardados
	 * @return List<User>
	 */
	List<User> list();
	
	/**
	 * Obtenemos un usuario por su Id
	 * @param id
	 * @return User
	 */
	User getById(Long id);
	
	/**
	 * Obtenemos un usuario por su username
	 * @param username
	 * @return User
	 */
	User getByUsername(String username);
	
	/**
	 * Se agrega a un board a la coleccion de boards de un usuario
	 * @param username
	 * @param board
	 */
	void addBoard(String username, Board board);

}
