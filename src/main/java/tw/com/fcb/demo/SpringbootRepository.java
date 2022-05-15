package tw.com.fcb.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

@Repository
public class SpringbootRepository {
	
	private final String JDBC_DRIVER = "org.h2.Driver";
	private final String DB_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
	private final String USERNAME = "admin";
	private final String PASSWORD = "";
	
	Connection connection = null;
	Statement statement = null;
	PreparedStatement pStatement = null;
	ResultSet resultSet = null;
	
//	Constructor
	public SpringbootRepository() {
		try {
			Class.forName(JDBC_DRIVER);
			
			System.out.println("Connecting to database...");
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	example 2
	public void findAll() {
		try {
			System.out.println("Query...");
			statement = connection.createStatement();
			
			String querySql = "SELECT * FROM TEST_TB";
			resultSet = statement.executeQuery(querySql);
			while(resultSet.next()) {
				System.out.println("ID: " + resultSet.getString("id"));
				System.out.println("NAME: " + resultSet.getString("name"));
				System.out.println("DATE: " + resultSet.getDate("date"));
				System.out.println("TIME: " + resultSet.getTime("time"));
				System.out.println("RATE: " + resultSet.getBigDecimal("rate"));
				System.out.println("AMOUNTB: " + resultSet.getBigDecimal("amount_b"));
				System.out.println("AMOUNTS: " + resultSet.getBigDecimal("amount_s"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	example 3
	public void findById(String id) {
		try {
			String querySql = "SELECT * FROM TEST_TB WHERE id = ?";
			pStatement = connection.prepareStatement(querySql);
			pStatement.setString(1, id);
			resultSet = pStatement.executeQuery();
			
			while(resultSet.next()) {
				System.out.println("ID: " + resultSet.getString("id"));
				System.out.println("NAME: " + resultSet.getString("name"));
				System.out.println("DATE: " + resultSet.getDate("date"));
				System.out.println("TIME: " + resultSet.getTime("time"));
				System.out.println("RATE: " + resultSet.getBigDecimal("rate"));
				System.out.println("AMOUNTB: " + resultSet.getBigDecimal("amount_b"));
				System.out.println("AMOUNTS: " + resultSet.getBigDecimal("amount_s"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
