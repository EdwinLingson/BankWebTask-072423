package utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListUtils {
	
	public BankUser getUserwithUid(List<BankUser> listofUsers,int uid){
		
		
		for(BankUser user:listofUsers) {
			if(user.getUserId()==uid) {
				return user;
			}
		}
		return null;
	}
public static char getPossibleAccount(List<BankAccount> list){
		
		if(list.size()==0) return 'b';
		else if(list.get(0).getType().equals("Checking")) return 's';
		else return 'c';
		
	}

public static Map<Integer,List<Transaction>> getbyYear(List <Transaction> transactions){
	
	HashMap<Integer,List<Transaction>> yearList = new HashMap<Integer,List<Transaction>>();
//	Calendar calndr = Calendar.getInstance();
	Calendar cal = Calendar.getInstance();
	
	for (Transaction transaction : transactions) {
		cal.setTime(transaction.getdOT());
		int year = cal.get(Calendar.YEAR);
		if(yearList.get(year)==null) {
			List<Transaction> aTransaction = new ArrayList<Transaction>();
			aTransaction.add(transaction);
			yearList.put(year, aTransaction);
		}
		else {
			List<Transaction> aTransaction = yearList.get(year);
			aTransaction.add(transaction);
			yearList.put(year, aTransaction);
		}
	}
	
	return yearList;
	
	
}

public static Map<String,List<Transaction>> getbyMonth(List <Transaction> transactions){
	
	HashMap<String,List<Transaction>> yearList = new HashMap<String,List<Transaction>>();
//	Calendar calndr = Calendar.getInstance();
	Calendar cal = Calendar.getInstance();
	
	for (Transaction transaction : transactions) {
		cal.setTime(transaction.getdOT());
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		String key = year+"-"+(month+1);
		if(yearList.get(key)==null) {
			List<Transaction> aTransaction = new ArrayList<Transaction>();
			aTransaction.add(transaction);
			yearList.put(key, aTransaction);
		}
		else {
			List<Transaction> aTransaction = yearList.get(key);
			aTransaction.add(transaction);
			yearList.put(key, aTransaction);
		}
	}
	
	return yearList;
	
	
}

public static Map<String,List<Transaction>> getbyDay(List <Transaction> transactions){
	
	HashMap<String,List<Transaction>> yearList = new HashMap<String,List<Transaction>>();
//	Calendar calndr = Calendar.getInstance();
	Calendar cal = Calendar.getInstance();
	
	for (Transaction transaction : transactions) {
		cal.setTime(transaction.getdOT());
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DATE);
		String key = year+"-"+(month+1)+"-"+day;
		if(yearList.get(key)==null) {
			List<Transaction> aTransaction = new ArrayList<Transaction>();
			aTransaction.add(transaction);
			yearList.put(key, aTransaction);
		}
		else {
			List<Transaction> aTransaction = yearList.get(key);
			aTransaction.add(transaction);
			yearList.put(key, aTransaction);
		}
	}
	
	return yearList;
	
	
}
}
