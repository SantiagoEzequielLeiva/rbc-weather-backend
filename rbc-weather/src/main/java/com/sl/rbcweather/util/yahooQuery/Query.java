package com.sl.rbcweather.util.yahooQuery;

import java.util.Date;

import lombok.Data;

/**
 * @author Santiago Leiva
 * 06/05/2018
 */

@Data
public class Query {

	private Integer count;
	private Date created;
	private String lang;
	private Results results;

}
