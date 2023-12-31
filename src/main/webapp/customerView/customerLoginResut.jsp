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
	
	DBManipUser dbManip = new DBManipUser(dbDetails);
	boolean isLoggin =false;
	int userID =0;
	BankUser bankUser = (BankUser)session.getAttribute("user");
	if(bankUser==null)
	{String u_uname = request.getParameter("uname");
	String pwd = request.getParameter("pwd");
	
	userID = dbManip.authenticateUser(u_uname, pwd);
	
	 isLoggin = userID!=0? true: false;
	
	if(isLoggin){
		bankUser = dbManip.getCustomerDetails(userID);
		bankUser.setU_uname(u_uname);
		if(bankUser!=null)
		session.setAttribute("user",bankUser);
	}
	}
	else {
		isLoggin = true;
		userID = bankUser.getUserId();
	}
	
	List<BankAccount> listOfAccounts = dbManip.getAccounts(userID);
	session.setAttribute("listOfAccounts", listOfAccounts);

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
          <li><a href="customerLoginResut.jsp" class="nav-link px-2 text-white">Home</a></li>
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
	<%= bankUser.getFname() + " " + bankUser.getSname()%>
You have Following Accounts </div>
	<div class="card-body">
	<table class="table table-secondary">
			<tr>
				<th>
				Account Number
				</th>
				<th>
				Balance
				</th>
				<th>
				Type
				</th>
				<th>
				Actions
				</th>
			</tr>
	<% for(BankAccount acct:listOfAccounts) { %>
			<tr>
				<td>
				<%= acct.getAcctNumber() %>
				</td>
				<td>
				<%= acct.getBalance() %>
				</td>
				<td>
				<%= acct.getType() %>
				</td>
				<td>
				<a href = <%= "seeTransactions.jsp?id="+acct.getAcctNumber() %>>
				See transactions
				</a>
				</td>
			</tr>
			<%
			}
	%>
		</table>
		<% if(listOfAccounts.size()<2){
			char f =ListUtils.getPossibleAccount(listOfAccounts);
			%>
		<p>Do you want to create an another account. If yes click 
			<a href=<%="createAccount.jsp?type="+f %> >here</a>
		</p>
		<% } %>
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
<%
	if(isLoggin) {
%>
<div class="card text-dark bg-primary text-center ps-5 mx-auto mt-5" style="width:50%">
<div class="card-header h3">Deposit/ Withdraw from accounts</div>
<div class="card-body">

	<div class ="row">
		<div class = "col">
			<div class="text-start">
            <a href ="accountAction.jsp?act=with">
          <button type="button" class="btn btn-warning me-2">Withdraw</button>
          </a>
        </div>
		</div>
		<div class = "col">
			<div class="text-end">
            <a href ="accountAction.jsp?act=dep">
          <button type="button" class="btn btn-warning me-2">Deposit</button>
          </a>
        </div>
		</div>

	</div>

</div>
</div>
<%
}
%>
 	
</body>
</html>