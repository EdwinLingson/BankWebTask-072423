package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBManipUser {
	Connection conn = null;
	
	
	public DBManipUser(DbDetails dbDetails) {
		super();
		this.conn = DBConfig.getMyConnection(dbDetails);
	}





	public int insertCustomerToDB(BankUser bankUser)  {
		String sqlStmt = "Insert into bankuser2 (`fname`, `sname`, `email`, `phone`, `address`, `city`, `state`, `zip`, `mgr`,`active`)"
				+ "values (?,?,?,?,?,?,?,?,?,1)";
		
		String authSqlStmt = "INSERT INTO `banktaskauth` (`userId`, `username`, `password`, `moru`,`active`) values (?, ?, ?, ?, 1)";
		
		/*
		 * INSERT INTO `testdb`.`users` (`cid`, `uname`, `pword`) VALUES ('30', 'edwi', '1234');
INSERT INTO `testdb`.`users` (`cid`, `uname`, `pword`) VALUES ('31', 'edu', '3456');

		 */
		
		PreparedStatement preparedStatement;
		try {
			conn.setAutoCommit(false);
			preparedStatement = conn.prepareStatement(sqlStmt,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1,bankUser.getFname());
			preparedStatement.setString(2,bankUser.getSname());
			preparedStatement.setString(3,bankUser.getEmail());
			preparedStatement.setString(4,bankUser.getPhNo());
			preparedStatement.setString(5,bankUser.getAddress());
			preparedStatement.setString(6,bankUser.getCity());
			preparedStatement.setString(7,bankUser.getState());
			preparedStatement.setString(8,bankUser.getZip());
			preparedStatement.setString(9,bankUser.getMgr());
			System.out.println(preparedStatement.toString());
			preparedStatement.executeUpdate();
	        
	        ResultSet tableKeys = preparedStatement.getGeneratedKeys();
	        tableKeys.next();
	        bankUser.setUserId(tableKeys.getInt(1));
	        preparedStatement = conn.prepareStatement(authSqlStmt);
	        System.out.println(preparedStatement.toString());
	        preparedStatement.setInt(1, bankUser.getUserId());
	        preparedStatement.setString(2, bankUser.getU_uname());
	        preparedStatement.setString(3, bankUser.getPwd());
	        preparedStatement.setString(4, "U");
	        System.out.println(preparedStatement.toString());
	        preparedStatement.executeUpdate();
	        conn.commit();
	        
		}
	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

        return bankUser.getUserId();
	}
	
	
	public int editUser(BankUser bankUser)  {
		String sqlStmt = "UPDATE `bankuser2` SET `fname` = ?, `sname` = ?, `email` = ?,"
				+ " `phone` = ?, `address` = ?, `city` = ?, `state` = ?, `zip` = ?, `mgr` = ? "
				+ "WHERE (`userId` = ?)";
		
		String authSqlStmt = "UPDATE `testdb`.`banktaskauth` SET `username` = ?, `password` = ? WHERE (`userId` = ?)";
		
		/*
		 * INSERT INTO `testdb`.`users` (`cid`, `uname`, `pword`) VALUES ('30', 'edwi', '1234');
INSERT INTO `testdb`.`users` (`cid`, `uname`, `pword`) VALUES ('31', 'edu', '3456');

		 */
		
		PreparedStatement preparedStatement;
		try {
			conn.setAutoCommit(false);
			preparedStatement = conn.prepareStatement(sqlStmt,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1,bankUser.getFname());
			preparedStatement.setString(2,bankUser.getSname());
			preparedStatement.setString(3,bankUser.getEmail());
			preparedStatement.setString(4,bankUser.getPhNo());
			preparedStatement.setString(5,bankUser.getAddress());
			preparedStatement.setString(6,bankUser.getCity());
			preparedStatement.setString(7,bankUser.getState());
			preparedStatement.setString(8,bankUser.getZip());
			preparedStatement.setString(9,bankUser.getMgr());
			preparedStatement.setInt(10,bankUser.getUserId());
			System.out.println(preparedStatement.toString());
			preparedStatement.executeUpdate();
			
			
			preparedStatement = conn.prepareStatement(authSqlStmt);
			System.out.println(preparedStatement.toString());
			preparedStatement.setString(1, bankUser.getU_uname());
			preparedStatement.setString(2, bankUser.getPwd());
			preparedStatement.setInt(3, bankUser.getUserId());
			System.out.println(preparedStatement.toString());
			preparedStatement.executeUpdate();
			conn.commit();
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
		return bankUser.getUserId();
	}
	
	public int deActivateUser(BankUser bankUser,int flag)  {
		String sqlStmt = "UPDATE `banktaskauth` SET `active` = ? WHERE (`userId` = ?)";
		
		String authSqlStmt = "UPDATE `testdb`.`bankuser2` SET `active` = ? WHERE (`userId` = ?)";
		
		/*
		 * INSERT INTO `testdb`.`users` (`cid`, `uname`, `pword`) VALUES ('30', 'edwi', '1234');
INSERT INTO `testdb`.`users` (`cid`, `uname`, `pword`) VALUES ('31', 'edu', '3456');

		 */
		
		PreparedStatement preparedStatement;
		try {
			conn.setAutoCommit(false);
			preparedStatement = conn.prepareStatement(sqlStmt,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1,flag);
			preparedStatement.setInt(2,bankUser.getUserId());
			System.out.println(preparedStatement.toString());
			preparedStatement.executeUpdate();
			
			
			preparedStatement = conn.prepareStatement(authSqlStmt);
			System.out.println(preparedStatement.toString());
			preparedStatement.setInt(1, flag);
			preparedStatement.setInt(2, bankUser.getUserId());
			System.out.println(preparedStatement.toString());
			preparedStatement.executeUpdate();
			conn.commit();
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
		return bankUser.getUserId();
	}
	
	public int authenticateUser(String uname, String pwd) {
		
		String sqlStmt= "Select * from banktaskauth where username =? and password =? and moru='U' and active=1";
		
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
	
public AuthObject getAuthValues(int uId) {
		
		String sqlStmt= "Select username, password from banktaskauth where userId =?";
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sqlStmt);
			preparedStatement.setInt(1, uId);
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println(preparedStatement.toString());
			if(rs.next()) {
				return new AuthObject(rs.getString(1),rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	/**
	 * @param uname
	 * @return
	 */
	public BankUser getCustomerDetails(int uid) {
		String sqlStmt= "Select * from bankuser2 where userId =?";
		BankUser bankUser = new BankUser();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sqlStmt);
			preparedStatement.setInt(1, uid);
			System.out.println(preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				bankUser.setUserId(rs.getInt(1));
				bankUser.setFname(rs.getString(2));
				bankUser.setSname(rs.getString(3));
				bankUser.setEmail(rs.getString(4));
				bankUser.setPhNo(rs.getString(5));
				bankUser.setAddress(rs.getString(6));
				bankUser.setCity(rs.getString(7));
				bankUser.setState(rs.getString(8));
				bankUser.setZip(rs.getString(9));
				bankUser.setMgr(rs.getString(10));
				bankUser.setActive(rs.getInt(11));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return bankUser;
	}
	
	
	public List<BankAccount> getAccounts(int userId) {
		List<BankAccount> listOfAccounts  = new ArrayList();
		String sqlStmt= "Select * from bankaccts where userId= ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sqlStmt);
			preparedStatement.setInt(1, userId);
			ResultSet rs;
		
			rs = preparedStatement.executeQuery();
		
			while(rs.next()) {
				int cId = rs.getInt(1);
				String acct_nmbr = rs.getString(2); 
				double balance= rs.getDouble(3); 
				String type= rs.getString(5).equals("S")?"Savings":"Checking";
				
				BankAccount bankAccount = new BankAccount(acct_nmbr,type,balance);
				
				listOfAccounts.add(bankAccount);
				
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return listOfAccounts;
	}
	
//	public static  Candidate getCandidate(Connection con,int cId) throws SQLException{
//	
//		String sqlStmt= "Select * from CandidateDb where cId =?";
//		PreparedStatement preparedStatement = con.prepareStatement(sqlStmt);
//		preparedStatement.setInt(1, cId);
//		Candidate candidate = null;
//		ResultSet rs = preparedStatement.executeQuery();
//		while(rs.next()) {
//			int newCid = rs.getInt(1);
//			String name = rs.getString(2); 
//			String address= rs.getString(3); 
//			String age= rs.getString(4); 
//			String experience = rs.getString(5); 
//			String skills = rs.getString(6);
//			String city = rs.getString(7);
//			String expectedSalary = rs.getString(8);
//			
//			candidate = new Candidate(name, address, age, experience, skills, city, expectedSalary,newCid);
//						
//		}
//		return candidate;
//	}
//	
//	public static  int doLogin(Connection con,String uname, String pword) throws SQLException{
//		
//		String sqlStmt= "Select * from Users where uname =? and pword=?";
//		PreparedStatement preparedStatement = con.prepareStatement(sqlStmt);
//		preparedStatement.setString(1, uname);
//		preparedStatement.setString(2, pword);
//		ResultSet rs = preparedStatement.executeQuery();
////		System.out.println(preparedStatement.toString());
//		if (rs.next()) {
//			return rs.getInt(1);
//		}
//		return 0;
//	}
//	
//	public static  boolean updateCandidate(Connection con,Candidate candidate, int selection,String newVal) throws SQLException{
//		
//		String sqlStmt= "UPDATE candidatedb SET ? = ? WHERE (cId = ?)";
//		
//		switch(selection) {
//		case 1: sqlStmt = String.format("UPDATE candidatedb SET %s = '%s' WHERE (cId = ?)","Name",newVal);break;
//		case 2: sqlStmt = String.format("UPDATE candidatedb SET %s = '%s' WHERE (cId = ?)","Address",newVal);break;
//		case 3: sqlStmt = String.format("UPDATE candidatedb SET %s = '%s' WHERE (cId = ?)","Age",newVal);break;
//		case 4: sqlStmt = String.format("UPDATE candidatedb SET %s = '%s' WHERE (cId = ?)","Experience",newVal);break;
//		case 5: sqlStmt = String.format("UPDATE candidatedb SET %s = '%s' WHERE (cId = ?)","Skills",newVal);break;
//		case 6: sqlStmt = String.format("UPDATE candidatedb SET %s = '%s' WHERE (cId = ?)","ExpLocation",newVal);break;
//		case 7: sqlStmt = String.format("UPDATE candidatedb SET %s = '%s' WHERE (cId = ?)","ExpSalary",newVal);break;
//		}
//		
//		PreparedStatement preparedStatement = con.prepareStatement(sqlStmt);
//		preparedStatement.setInt(1, candidate.getcId());
//		int res= preparedStatement.executeUpdate();
//		return res==1?true:false;
//	}
//	
//public static  boolean deleteCandidate(Connection con,Candidate candidate) throws SQLException{
//		
//		String sqlStmt= "DELETE FROM candidatedb WHERE (cId = ?)";
//		
//				
//		PreparedStatement preparedStatement = con.prepareStatement(sqlStmt);
//		preparedStatement.setInt(1, candidate.getcId());
//		int res= preparedStatement.executeUpdate();
//		return res==1?true:false;
//	}
}
