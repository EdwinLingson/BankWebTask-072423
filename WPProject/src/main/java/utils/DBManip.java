package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBManip {
	static Connection conn = null;
	
	
	public DBManip(DbDetails dbDetails) {
		super();
		this.conn = getMyConnection(dbDetails);
	}


	public static Connection getMyConnection(DbDetails dbDetails) {
        try {
            Class.forName(dbDetails.getDriver());
            Connection connection = DriverManager.getConnection(dbDetails.getUrl(), dbDetails.getUname(), dbDetails.getPassword());
            if (connection == null) {
                System.out.println("Connection Failed");
            }
            return connection;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {	
            throw new RuntimeException(e);
        }
    }


	public int insertToDB(Employee employee)  {
		String sqlStmt = "Insert into EmployeeTask (`fName`, `sName`, `email`, `pwd`, `phNO`, `address`, `zip`, `wpCheck`)"
				+ "values (?,?,?,?,?,?,?,?)";
		
		/*
		 * INSERT INTO `testdb`.`users` (`cid`, `uname`, `pword`) VALUES ('30', 'edwi', '1234');
INSERT INTO `testdb`.`users` (`cid`, `uname`, `pword`) VALUES ('31', 'edu', '3456');

		 */
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(sqlStmt,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1,employee.getfName());
			preparedStatement.setString(2,employee.getsName());
			preparedStatement.setString(3,employee.getEmail());
			preparedStatement.setString(4,employee.getPwd());
			preparedStatement.setString(5,employee.getPhone());
			preparedStatement.setString(6,employee.getAddress());
			preparedStatement.setString(7,employee.getZip());
			preparedStatement.setBoolean(8,employee.isWp());
			preparedStatement.executeUpdate();
	        
	        ResultSet tableKeys = preparedStatement.getGeneratedKeys();
	        tableKeys.next();
	        employee.seteId(tableKeys.getInt(1));
		}
	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
        return employee.geteId();
	}
	
//	public List<Product> getProduct() {
//		List<Product> listOfProducts  = new ArrayList();
//		String sqlStmt= "Select * from product_jsp";
//		try {
//			PreparedStatement preparedStatement = conn.prepareStatement(sqlStmt);
//			ResultSet rs;
//		
//			rs = preparedStatement.executeQuery();
//		
//			while(rs.next()) {
//				int cId = rs.getInt(1);
//				String pId = rs.getString(2); 
//				String pName= rs.getString(3); 
//				String pPrice= rs.getString(4);
//				
//				Product product = new Product(cId,pId,pName,pPrice);
//				
//				listOfProducts.add(product);
//				
//			}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		return listOfProducts;
//	}
	
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
