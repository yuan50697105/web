package com.web.utils;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;

public class DBUtils {
	private static BasicDataSource dataSource = null;
	private static QueryRunner runner = null;
	static {
		try {
			InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("db.properties");
			Properties properties = new Properties();
			properties.load(inputStream);
			dataSource = BasicDataSourceFactory.createDataSource(properties);
			runner=new QueryRunner(dataSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static QueryRunner getRunner(){
		return runner;
	}
}
