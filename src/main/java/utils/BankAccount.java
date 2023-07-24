package utils;

public class BankAccount {
	private String acctNumber;
	private String type;
	private double balance;
	public BankAccount(String acctNumber, String type, double balance) {
		super();
		this.acctNumber = acctNumber;
		this.type = type;
		this.balance = balance;
	}
	public String getAcctNumber() {
		return acctNumber;
	}
	public void setAcctNumber(String acctNumber) {
		this.acctNumber = acctNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	

}
