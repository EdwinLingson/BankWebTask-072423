package utils;

public class DbDetails {
	private String driver;
	private String url;
	private String uname;
	private String password;
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public DbDetails(String driver, String url, String uname, String password) {
		super();
		this.driver = driver;
		this.url = url;
		this.uname = uname;
		this.password = password;
	}
	

}
