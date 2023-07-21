package utils;

public class Employee {

	private String fName;
	private String sName;
	private String address;
	private String zip;
	private String pwd;
	private String email;
	private String phone;
	private boolean wp;
	private int eId;
	
	public Employee(String fName, String sName, String address, String zip, String email, String pwd, String phone, boolean wp) {
		super();
		this.fName = fName;
		this.sName = sName;
		this.address = address;
		this.zip = zip;
		this.pwd =pwd;
		this.email = email;
		this.phone = phone;
		this.wp = wp;
	}
	
	
	
	public String getPwd() {
		return pwd;
	}



	public void setPwd(String pwd) {
		this.pwd = pwd;
	}



	public int geteId() {
		return eId;
	}

	public void seteId(int eId) {
		this.eId = eId;
	}

	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isWp() {
		return wp;
	}
	public void setWp(boolean wp) {
		this.wp = wp;
	}
	
	
	
}
