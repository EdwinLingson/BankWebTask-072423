<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.jasper.compiler.NewlineReductionServletWriter"%>
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
	
	DBManipManager dbManip = new DBManipManager(dbDetails);
	DBManipUser dbManipUser = new DBManipUser(dbDetails);
	AccountManip accountManip = new AccountManip(dbDetails);
	ListUtils listUtils = new ListUtils();
	BankManager bankManager = (BankManager)session.getAttribute("manager");
	
	String userId = request.getParameter("id");
	List<BankUser> listofUsers = (List<BankUser>)session.getAttribute("listofUsers");
	
	BankUser user = listUtils.getUserwithUid(listofUsers, Integer.parseInt(userId));
	
	List<BankAccount> listofBankAccounts = dbManipUser.getAccounts(user.getUserId());
	session.setAttribute("user", user);
	boolean isLoggin = true;
	
	


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

<%
	if(isLoggin) {
%>
<div class="card text-dark bg-secondary text-center ps-5 mx-auto mt-5" style="width:50%">

<div class="card-header py-3 h4">
Details of User: 
	<%= (user.getFname() + " " + user.getSname()) %>
 </div>
<div class="card-body">
	<div class="row">
		<div class="col text-end">
			First Name		
		</div>
		<div class="col  text-start">
			<%= user.getFname() %>		
		</div>
	</div>
	<div class="row">
		<div class="col text-end">
			Last Name		
		</div>
		<div class="col  text-start">
			<%= user.getSname() %>		
		</div>
	</div>
	<div class="row">
		<div class="col text-end">
			Email		
		</div>
		<div class="col  text-start">
			<%= user.getEmail() %>		
		</div>
	</div>
	<div class="row">
		<div class="col text-end">
			Phone		
		</div>
		<div class="col  text-start">
			<%= user.getPhNo() %>		
		</div>
	</div>
	<div class="row">
		<div class="col text-end">
			Address		
		</div>
		<div class="col  text-start">
			<%= user.getAddress() +", " +user.getCity()+ " ," +user.getState() %>		
		</div>
	</div>
	<div class="row">
		<div class="col text-end">
			Zip
		</div>
		<div class="col  text-start">
			<%= user.getZip() %>		
		</div>
	</div>
	<div class="row">
		<div class="col text-end">
			Manager ID
		</div>
		<div class="col text-start">
			<%= user.getMgr() %>
		</div>
	</div>
		<div class="row py-3">
		<div class="col text-end">
			<a href = "editUser.jsp">
				<button class="btn btn-primary col-5">Edit</button> 
				</a>
		</div>
		<div class="col text-start">
			<% if(user.getActive()==1){ %>
			<a href = "deleteUser.jsp">
				<button class="btn btn-primary col-5">Deactivate</button> 
			</a>
			<%
			} else
			{
			%>
			<a href = "activateUser.jsp">
				<button class="btn btn-primary col-5">Activate</button> 
			</a>
			<% } %>
		</div>
	</div>
	</div>
	</div>
<%
if (user.getActive()==1){
%>
	<div class="card text-dark bg-secondary text-center ps-5 mx-auto mt-5" style="width:50%">


<div class="card-header py-3 h4">
<%=user.getFname() %>
	has Following accounts </div>

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
	<% for(BankAccount acct:listofBankAccounts) { %>
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
		<% if(listofBankAccounts.size()<2){
			char f =ListUtils.getPossibleAccount(listofBankAccounts);
			%>
		<p>Do you want to create an another account. If yes click 
			<a href=<%="createAccount.jsp?type="+f %> >here</a>
		</p>
		<% } %>
	</div>
	</div>
	<%}		 %>
<%
	}
	else {

%> 
<div class="card text-dark bg-secondary text-center ps-5 mx-auto mt-5" style="width:50%">
<div class="card-header py-3 h4">Login Failed </div>
  </div>
<%
	}
%>
	
</body>
</html>