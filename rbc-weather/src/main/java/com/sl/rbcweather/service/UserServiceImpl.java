package com.sl.rbcweather.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sl.rbcweather.dao.UserRepository;
import com.sl.rbcweather.model.Board;
import com.sl.rbcweather.model.User;

/**
 * @author Santiago Leiva
 * 03/05/2018
 */

@Transactional
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User save(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public List<User> list() {
		return this.userRepository.findAll();
	}

	@Override
	public User getById(Long id) {
		return this.userRepository.findById(id).get();
	}

	@Override
	public User getByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}

	@Override
	public void addBoard(String username, Board board) {
		User user = getByUsername(username);
		
		user.addBoard(boardService.updateBoard(board));
		
		save(user);
	}

}
