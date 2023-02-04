package in.shantun.util;

import java.io.*;
import java.sql.*;
import java.sql.DriverManager;
import java.util.Properties;

public class JdbcUtil {
	private JdbcUtil() {
		
	}
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	//this will provide the physical connection.(old way)
	public static Connection getJdbcConnection() throws SQLException , IOException {
		String dbLocation = "D:\\FULL STACK JAVA DEVELOPMENT INEURON\\6 Small Project using using jdbc servlet jsp\\Project-1\\src\\main\\java\\in\\shantun\\properties\\db.properties";
		FileInputStream fis = new FileInputStream(dbLocation);
		Properties properties = new Properties();
		properties.load(fis);
		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		Connection connection = DriverManager.getConnection(url , username , password);
		return connection;
	}

}
