package com.boroda.data;

import org.apache.commons.dbutils.QueryRunner;
import org.sqlite.SQLiteDataSource;

/**
 * Created by dmitrystarchak on 29/09/14.
 */
public class BaseConnector {
	private SQLiteDataSource ds;

	protected QueryRunner run;

	//TODO: generate path depending on username
	//TODO: generate path depending on OS
	//TODO: check if DB exists
	public BaseConnector() {
		ds = new SQLiteDataSource();
		ds.setUrl("jdbc:sqlite:/Users/dmitrystarchak/Library/Application Support/Skype/boroda.item/main.db");

		run = new QueryRunner(ds);
	}
}
