package utils;

import java.sql.Date;

public class Transaction {
	private int tId;
	private double amt;
	private String action;
	private Date dOT;
	
	public Transaction(int tId, double amt, String action, Date dOt) {
		super();
		this.tId = tId;
		this.amt = amt;
		this.action = action;
		this.dOT = dOt;
	}
	public Transaction(int tId, double amt) {
		super();
		this.tId = tId;
		this.amt = amt;
	}
	public int gettId() {
		return tId;
	}
	public void settId(int tId) {
		this.tId = tId;
	}
	public double getAmt() {
		return amt;
	}
	public void setAmt(double amt) {
		this.amt = amt;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Date getdOT() {
		return dOT;
	}
	public void setdOT(Date dOT) {
		this.dOT = dOT;
	}
	
	
}
