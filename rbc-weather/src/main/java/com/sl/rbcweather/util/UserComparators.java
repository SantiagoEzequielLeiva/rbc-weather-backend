package com.sl.rbcweather.util;

import java.util.Comparator;

import com.sl.rbcweather.model.User;

/**
 * @author Santiago Leiva
 * 12/05/2018
 */

public class UserComparators {
	public static final Comparator<User> USERNAME = (User u1, User u2) -> u1.getUsername().compareTo(u2.getUsername());
}
