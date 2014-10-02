package com.boroda.data;

import org.apache.commons.dbutils.QueryRunner;
import org.sqlite.SQLiteDataSource;

import java.io.File;

/**
 * Created by dmitrystarchak on 29/09/14.
 */
public class BaseConnector {
	private SQLiteDataSource ds;

	protected QueryRunner run;

	public BaseConnector(File dbPath) {
		ds = new SQLiteDataSource();
		ds.setUrl("jdbc:sqlite:" + dbPath.getPath() + "/main.db");

		run = new QueryRunner(ds);
	}
}
