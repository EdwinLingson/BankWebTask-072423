
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
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
	
	AccountManip dbManip = new AccountManip(dbDetails);
	
	
	String acct_number = request.getParameter("id");
		
	
	List<Transaction> listOfTransactions = dbManip.getTransactions(acct_number);
	
	Map<Integer,List<Transaction>> yearList = ListUtils.getbyYear(listOfTransactions);
	Set<Integer> years = yearList.keySet();
	Iterator<Integer> iterator = years.iterator();
	
	
	

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

            <a href ="logout.jsp">
          <button type="button" class="btn btn-warning me-2">Logout</button>
          </a>
        
        </div>
      </div>
    </div>
  </header>


<div class="card text-dark bg-secondary text-center ps-5 mx-auto mt-5" style="width:50%">
<%
	if(true) {
%>
<div class="card-header py-3 h4">

Transactions for account <%=acct_number %> by years </div>
	
		<div class="card-body">
	<% while(iterator.hasNext())
		{
		Integer year = (Integer)iterator.next();
		
		%>
		
	<table class="table table-secondary">
	<tr><th class="text-center h4" colspan="4"><%=year %></th></tr>
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
				Date of Transaction
				</th>
				
			</tr>
	<% for(Transaction acct: yearList.get(year)) { %>
			<tr>
				<td>
				<%= acct.gettId() %>
				</td>
				<td>
				<%= acct.getAmt() %>
				</td>
				<td>
				<%= acct.getAction().equals("with")?"Withdrawal":"Deposit" %>
				</td>
				<td>
				<%= acct.getdOT() %>
				</td>
			
			</tr>
			<%
			}
	}
	}
	%>
		</table>
	<a href=<%="seeTransactions.jsp?id="+acct_number %>>
		<button class = "btn btn-primary">
			Go Back to Transactions
		</button>
	</a>
	</div>
</div>
 	
</body>
</html>