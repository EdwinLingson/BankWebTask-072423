<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="utils.*, java.util.List" %>
<%


	ServletContext context = request.getServletContext();
	session = request.getSession();
	String uname = context.getInitParameter("uname");
	String password = context.getInitParameter("password");
	String driver = context.getInitParameter("driverName");
	String url = context.getInitParameter("url");
	DbDetails dbDetails = new DbDetails(driver, url, uname, password);
	boolean isLoggin =false;
	DBManipManager dbManip = new DBManipManager(dbDetails);
	
	BankManager bankManager = (BankManager) session.getAttribute("manager");
	if(bankManager==null)
	{
		String u_uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
	
		int userID = dbManip.authenticateUser(u_uname, pwd);
	
		isLoggin  = userID!=0? true: false;
		if(isLoggin){
			bankManager = dbManip.getManagerDetails(userID);
			bankManager.setU_uname(u_uname);
			if(bankManager!=null)
			session.setAttribute("manager",bankManager);
		}
	}
	else{
		isLoggin =true;
	}
	List<BankUser> listofUsers = dbManip.getListofUsers();
	session.setAttribute("listofUsers",listofUsers);
	


%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>ABC Bank</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">  
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>    
</head>

<body class="text-bg-dark">
 <header class="p-3 bg-dark text-white">
    <div class="container">
      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
        <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
          <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"/></svg>
        </a>

        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
          <li><a href="managerLoginRes.jsp" class="nav-link px-2 text-white">Home</a></li>
        </ul>
			<div class="text-end">
			<%
	if(isLoggin) {
%>
            <a href ="logout.jsp">
          <button type="button" class="btn btn-warning me-2">Logout</button>
          </a>
          <%} 
		else { %>
          <a href ="../index.html">
          <button type="button" class="btn btn-warning me-2">Login</button>
          </a>
          <%} %>
        </div>
      </div>
    </div>
  </header>


<div class="card text-dark bg-secondary text-center ps-5 mx-auto mt-5" style="width:50%">
<%
	if(isLoggin) {
%>
<div class="card-header py-3 h4">
Hi 
	<%= (bankManager.getFname() + " " + bankManager.getSname()) %>
You have Logged In </div>
<div class="card-body">
	<table class="table table-secondary">
			<tr>
				<th>
				UserId
				</th>
				<th>
				email
				</th>
				<th>
				phone
				</th>
				<th>
				Action
				</th>
				<th>
				Active
				</th>
			</tr>
	<% for(BankUser user:listofUsers) { %>
			<tr>
				<td>
				<%= user.getUserId() %>
				</td>
				<td>
				<%= user.getEmail() %>
				</td>
				<td>
				<%= user.getPhNo() %>
				</td>
				<td>
				<a href = <%= "viewAccounts.jsp?id="+user.getUserId() %>>
				Show details
				</a>
				</td>
				
				<td>
				<%= user.getActive()==1?"Active":"Inactive" %>
				</td>
			</tr>
			<%
			}
	%>
		</table>
		<p>Do you want to create an another User. If yes click 
			<a href="createUser.jsp" >here</a>
		</p>
	</div>
<%
	}
	else {

%> 

<div class="card-header py-3 h4">Login Failed </div>
<%
	}
%>
  </div>	
</body>
</html>