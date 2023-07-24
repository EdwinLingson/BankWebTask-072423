package utils;

public class BankManager {
	private int userId;
	private String fname;
	private String sname;
	private String email;
	private String phNo;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String u_uname;
	private String pwd;
	private String doj;
	
	
	
	public BankManager() {
		super();
	}


	public BankManager(int userId, String fname, String sname, String email, String phNo, String address, String city,
			String state, String zip, String u_uname, String pwd, String mgr) {
		super();
		this.userId = userId;
		this.fname = fname;
		this.sname = sname;
		this.email = email;
		this.phNo = phNo;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.u_uname = u_uname;
		this.pwd = pwd;
		this.doj = mgr;
	}


	public BankManager(String fname, String sname, String email, String phNo, String address, String city, String state,
			String zip, String u_uname, String pwd, String mgr) {
		super();
		this.fname = fname;
		this.sname = sname;
		this.email = email;
		this.phNo = phNo;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.u_uname = u_uname;
		this.pwd = pwd;
		this.doj = mgr;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public String getSname() {
		return sname;
	}


	public void setSname(String sname) {
		this.sname = sname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhNo() {
		return phNo;
	}


	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getZip() {
		return zip;
	}


	public void setZip(String zip) {
		this.zip = zip;
	}


	public String getU_uname() {
		return u_uname;
	}


	public void setU_uname(String u_uname) {
		this.u_uname = u_uname;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getDoj() {
		return doj;
	}


	public void setDoj(String mgr) {
		this.doj = mgr;
	}
	
	
	
}
