package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManipManager {
	Connection conn = null;
	
	
	public DBManipManager(DbDetails dbDetails) {
		super();
		this.conn = DBConfig.getMyConnection(dbDetails);
	}
	
	
	public int insertManagerToDB(BankManager bankManager)  {
		String sqlStmt = "Insert into bankmanager (`fname`, `sname`, `email`, `phone`, `address`, `city`, `state`, `zip`, `doj`)"
				+ "values (?,?,?,?,?,?,?,?,?)";
		
		String authSqlStmt = "INSERT INTO `banktaskauth` (`userId`, `username`, `password`, `moru`) values (?, ?, ?, ?)";
		
		/*
		 * INSERT INTO `testdb`.`users` (`cid`, `uname`, `pword`) VALUES ('30', 'edwi', '1234');
INSERT INTO `testdb`.`users` (`cid`, `uname`, `pword`) VALUES ('31', 'edu', '3456');

		 */
		
		PreparedStatement preparedStatement;
		try {
			conn.setAutoCommit(false);
			preparedStatement = conn.prepareStatement(sqlStmt,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1,bankManager.getFname());
			preparedStatement.setString(2,bankManager.getSname());
			preparedStatement.setString(3,bankManager.getEmail());
			preparedStatement.setString(4,bankManager.getPhNo());
			preparedStatement.setString(5,bankManager.getAddress());
			preparedStatement.setString(6,bankManager.getCity());
			preparedStatement.setString(7,bankManager.getState());
			preparedStatement.setString(8,bankManager.getZip());
			preparedStatement.setString(9,bankManager.getDoj());
			System.out.println(preparedStatement.toString());
			preparedStatement.executeUpdate();
	        
	        ResultSet tableKeys = preparedStatement.getGeneratedKeys();
	        tableKeys.next();
	        bankManager.setUserId(tableKeys.getInt(1));
	        preparedStatement = conn.prepareStatement(authSqlStmt);
	        System.out.println(preparedStatement.toString());
	        preparedStatement.setInt(1, bankManager.getUserId());
	        preparedStatement.setString(2, bankManager.getU_uname());
	        preparedStatement.setString(3, bankManager.getPwd());
	        preparedStatement.setString(4, "M");
	        System.out.println(preparedStatement.toString());
	        preparedStatement.executeUpdate();
	        conn.commit();
	        
		}
	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

        return bankManager.getUserId();
	}
	
	public int authenticateUser(String uname, String pwd) {
		
		String sqlStmt= "Select * from banktaskauth where username =? and password =? and moru='M'";
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sqlStmt);
			preparedStatement.setString(1, uname);
			preparedStatement.setString(2, pwd);
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println(preparedStatement.toString());
			if(rs.next()) {
				return rs.getInt(1);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	/**
	 * @param uname
	 * @return
	 */
	public BankManager getManagerDetails(int uid) {
		String sqlStmt= "Select * from bankmanager where managerId =?";
		BankManager bankManager = new BankManager();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sqlStmt);
			preparedStatement.setInt(1, uid);
			System.out.println(preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				bankManager.setUserId(rs.getInt(1));
				bankManager.setFname(rs.getString(2));
				bankManager.setSname(rs.getString(3));
				bankManager.setEmail(rs.getString(4));
				bankManager.setPhNo(rs.getString(5));
				bankManager.setAddress(rs.getString(6));
				bankManager.setCity(rs.getString(7));
				bankManager.setState(rs.getString(8));
				bankManager.setZip(rs.getString(9));
				bankManager.setDoj(rs.getString(10));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return bankManager;
	}
}
