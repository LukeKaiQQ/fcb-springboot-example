package tw.com.fcb.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class SpringbootRepository {
//	DB 連線資訊
	private final String JDBC_DRIVER = "org.h2.Driver";
	private final String DB_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
	private final String USERNAME = "admin";
	private final String PASSWORD = "";
	
	Connection connection = null;
	Statement statement = null;
	PreparedStatement pStatement = null;
	ResultSet resultSet = null;
	
	CommonArea commonArea = null;
	List<CommonArea> lists = null;
	
//	Constructor
	public SpringbootRepository() throws Exception {
		Class.forName(JDBC_DRIVER);
			
		System.out.println("Connecting to database...");
		connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
	}
	
//	example 2
	public List<CommonArea> findAll() throws Exception {
		log.info("{}", "Query for findAll()...");
		
		statement = connection.createStatement();
		String querySql = "SELECT * FROM TEST_TB";
		resultSet = statement.executeQuery(querySql);
			
		lists = new ArrayList<CommonArea>();
		while(resultSet.next()) {
//			System.out.println("ID: " + resultSet.getString("id"));
//			System.out.println("NAME: " + resultSet.getString("name"));
//			System.out.println("DATE: " + resultSet.getDate("date"));
//			System.out.println("TIME: " + resultSet.getTime("time"));
//			System.out.println("RATE: " + resultSet.getBigDecimal("rate"));
//			System.out.println("AMOUNTB: " + resultSet.getBigDecimal("amount_b"));
//			System.out.println("AMOUNTS: " + resultSet.getBigDecimal("amount_s"));
				
			commonArea = new CommonArea();
			commonArea.setId(resultSet.getString("id"));
			commonArea.setName(resultSet.getString("name"));
			commonArea.setDate(resultSet.getDate("date").toLocalDate());
			commonArea.setTime(resultSet.getTime("time").toLocalTime());
			commonArea.setRate(resultSet.getBigDecimal("rate"));
			commonArea.setAmountB(resultSet.getBigDecimal("amount_b"));
			commonArea.setAmountS(resultSet.getBigDecimal("amount_s"));
				
			lists.add(commonArea);
		}
		
		return lists;
	}
	
//	example 3
	public List<CommonArea> findById(String id) throws Exception {
		log.info("{}", "Query for findById()...");
		
		String querySql = "SELECT * FROM TEST_TB WHERE id = ?";
		pStatement = connection.prepareStatement(querySql);
		pStatement.setString(1, id);
		resultSet = pStatement.executeQuery();
		
		lists = new ArrayList<CommonArea>();
		while(resultSet.next()) {
			commonArea = new CommonArea();
			commonArea.setId(resultSet.getString("id"));
			commonArea.setName(resultSet.getString("name"));
			commonArea.setDate(resultSet.getDate("date").toLocalDate());
			commonArea.setTime(resultSet.getTime("time").toLocalTime());
			commonArea.setRate(resultSet.getBigDecimal("rate"));
			commonArea.setAmountB(resultSet.getBigDecimal("amount_b"));
			commonArea.setAmountS(resultSet.getBigDecimal("amount_s"));
					
			lists.add(commonArea);
		}
		
		return lists;
	}
	
//	example 4
//	public void insert() throws Exception {
//		String insertSql = "INSERT INTO TEST_TB VALUES(?,?,?,?,?,?,?)";
//		pStatement = connection.prepareStatement(insertSql);
//		pStatement.setString(1, "A987654321");
//		pStatement.setString(2, "HAHA");
//		pStatement.setObject(3, LocalDate.now());
//		pStatement.setObject(4, LocalTime.now());
//		pStatement.setBigDecimal(5, BigDecimal.valueOf(99999.99999));
//		pStatement.setBigDecimal(6, BigDecimal.valueOf(12345.99));
//		pStatement.setBigDecimal(7, BigDecimal.valueOf(12345.99));
//		pStatement.executeUpdate();
//		pStatement.clearParameters();
//		
//		log.info("{}", "Insert success...");
//	}
	
	public void insert(CommonArea commonArea) throws Exception {
		String insertSql = "INSERT INTO TEST_TB VALUES(?,?,?,?,?,?,?)";
		pStatement = connection.prepareStatement(insertSql);
		pStatement.setString(1, commonArea.getId());
		pStatement.setString(2, commonArea.getName());
		pStatement.setObject(3, commonArea.getDate());
		pStatement.setObject(4, commonArea.getTime());
		pStatement.setBigDecimal(5, commonArea.getRate());
		pStatement.setBigDecimal(6, commonArea.getAmountB());
		pStatement.setBigDecimal(7, commonArea.getAmountS());
		pStatement.executeUpdate();
		pStatement.clearParameters();
		
		log.info("{}", "Insert success...");
	}
	
//	example 5
	public void update() throws Exception {
		String updateSql = "UPDATE TEST_TB SET AMOUNT_B = ?, DATE = ?, TIME = ? WHERE id = ?";
		pStatement = connection.prepareStatement(updateSql);
		pStatement.setString(1, "9999999.99");
		pStatement.setObject(2, LocalDate.now());
		pStatement.setObject(3, LocalTime.now());
		pStatement.setString(4, "86483XXX");
		
		int affectedRow = pStatement.executeUpdate();
		log.info("{}", "Total " + affectedRow + " data updated");
		
		pStatement.clearParameters();
	}
}
