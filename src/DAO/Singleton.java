package DAO;

import java.sql.*;

public class Singleton {

	private static String url = "jdbc:mysql://localhost:3306/test2";
	private static String user = "sorn";
	private static String pswd = "shacal";
	private static Connection connect;
	
	public static Connection getInstance(){
		if(connect == null){
			try{
				connect = DriverManager.getConnection(url, user, pswd);
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		return connect;
	}
	
}
