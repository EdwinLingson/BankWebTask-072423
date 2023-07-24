package utils;

import java.awt.Desktop.Action;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountManip {
	
	Connection conn = null;
	
	
	public AccountManip(DbDetails dbDetails) {
		super();
		this.conn = DBConfig.getMyConnection(dbDetails);
	}
	
	public String createAccount(BankUser bankUser, String acctype, String deposit) {
		
		
		String sqlStmt = "Insert into bankaccts ( `balance`, `userId`, `type`)"
				+ "values (?,?,?)";
		String updateStmt = "UPDATE `bankaccts` SET `acct_number` = ? WHERE (`AccId` = ?)";
		String acct_number =null;		
		PreparedStatement preparedStatement;
		try {
			conn.setAutoCommit(false);
			preparedStatement = conn.prepareStatement(sqlStmt,Statement.RETURN_GENERATED_KEYS);
			double dep = Double.parseDouble(deposit);
			
			String actype  = acctype.equals("1")?"S":"C";
			
			preparedStatement.setDouble(1,dep);
			preparedStatement.setInt(2,bankUser.getUserId());
			preparedStatement.setString(3,actype);
			
			System.out.println(preparedStatement.toString());
			preparedStatement.executeUpdate();
	        
	        ResultSet tableKeys = preparedStatement.getGeneratedKeys();
	        tableKeys.next();
	        int newKey = tableKeys.getInt(1);
	        acct_number = createHash(bankUser.getMgr()) + newKey ;
	        
	        preparedStatement = conn.prepareStatement(updateStmt);
	        System.out.println(preparedStatement.toString());
	        preparedStatement.setString(1, acct_number);
	        preparedStatement.setInt(2, newKey);
	        System.out.println(preparedStatement.toString());
	        preparedStatement.executeUpdate();
	        conn.commit();
	        
		}
	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

        return acct_number;
	}
	
	public Transaction doAction(String act, String accountNo, String amount) {
		
		
		String sqlStmt = "SELECT balance FROM bankaccts WHERE (`Acct_number` = ?);";
		String updateStmt = "UPDATE `bankaccts` SET `balance` = ? WHERE (`Acct_number` = ?);";
		String doTransaction = "INSERT INTO `banktransactions` (`AcctNo`, `Action`, `newBal`) VALUES (?, ?, ?)";
		
		double prev_amt=0;
		PreparedStatement preparedStatement;
		try {
			conn.setAutoCommit(false);
			preparedStatement = conn.prepareStatement(sqlStmt);
			double amt = Double.parseDouble(amount);
			
			
			
			preparedStatement.setString(1,accountNo);
			
			System.out.println(accountNo);
			
			System.out.println(preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			
			if(rs!=null)
	        prev_amt = rs.getDouble(1);
			
			System.out.println(prev_amt);
			preparedStatement = conn.prepareStatement(updateStmt);
	        preparedStatement.setDouble(1, prev_amt + newAmt(amount, act));
	        preparedStatement.setString(2, accountNo);
	        System.out.println(preparedStatement.toString());
	        preparedStatement.executeUpdate();
	        
	        preparedStatement = conn.prepareStatement(doTransaction,Statement.RETURN_GENERATED_KEYS);
	        preparedStatement.setString(1, accountNo);
	        preparedStatement.setString(2, act);
	        preparedStatement.setDouble(3, prev_amt + newAmt(amount, act));
	        System.out.println(preparedStatement.toString());
	        preparedStatement.executeUpdate();
	        
	        ResultSet tableKeys = preparedStatement.getGeneratedKeys();
	        tableKeys.next();
	        int newKey = tableKeys.getInt(1);
	        conn.commit();
	        
	        return new Transaction(newKey, prev_amt + newAmt(amount, act));
		}
	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Transaction> getTransactions(String acctNo) {
		List<Transaction> listOfTransactions  = new ArrayList();
		String sqlStmt= "Select * from banktransactions where AcctNo= ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sqlStmt);
			preparedStatement.setString(1, acctNo);
			ResultSet rs;
		
			rs = preparedStatement.executeQuery();
		
			while(rs.next()) {
				int tId = rs.getInt(1);
				String acct_nmbr = rs.getString(2); 
				double amt= rs.getDouble(4); 
				String action= rs.getString(3);
				
				Transaction bankAccount = new Transaction(tId,amt,action);
				
				listOfTransactions.add(bankAccount);
				
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return listOfTransactions;
	}
	
	private double newAmt (String amt,String act) {
		if(act.equals("dep")) {
			return Double.parseDouble(amt);
		}
		else return Double.parseDouble(amt)*-1;
	}
	private String createHash(String mgr) {
		return mgr.hashCode()+mgr;
	}
	
}
