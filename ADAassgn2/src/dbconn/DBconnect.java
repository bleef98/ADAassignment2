package dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DBconnect {
	
	private Connection conn = null;
	private Statement statement = null;
	private ResultSet resSet = null;
	
	private static final String DRIVER="com.mysql.jdbc.Driver";
	private static final String DB_URL="jdbc:mysql://raptor2:3306/terrains";
	
	public void dbRead() throws Exception{
		
		// get username and password from keyboard
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter user name: ");
		String username = scan.nextLine();
		
		System.out.println("Enter password:");
		String password = scan.nextLine();
		
		try {
			Class.forName(DRIVER); //load the database driver for mysql
			System.out.println("Trying to open raptor2 database connection");
			
			Connection conn = DriverManager.getConnection(DB_URL, username, password);
			Statement stmt = conn.createStatement();
			
			//obtain the result set holding the names of current databases
			System.out.println("Executing SQL statement");
			
			String cmd = "";
			
			resSet = stmt.executeQuery(cmd);
		}
	}
	

}
