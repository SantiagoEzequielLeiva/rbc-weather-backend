package com.sl.rbcweather.util;

import java.util.Comparator;

import com.sl.rbcweather.model.Board;

/**
 * @author Santiago Leiva
 * 12/07/2018
 */

public class BoardComparators {
	public static final Comparator<Board> CITY = (Board b1, Board b2) -> b1.getCity().compareTo(b2.getCity());
	public static final Comparator<Board> COUNTRY = (Board b1, Board b2) -> b1.getCountry().compareTo(b2.getCountry());
}
