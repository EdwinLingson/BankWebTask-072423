<%@page import="utils.BankUser"%>
<%@page import="utils.BankManager"%>
<%
session = request.getSession();

BankManager bankManager = (BankManager)session.getAttribute("manager");
BankUser bankUser = (BankUser)session.getAttribute("user");
char id = request.getParameter("type").charAt(0);

%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>FwS Job Portal</title>
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
          <li><a href="managerLoginRes.jsp class="nav-link px-2 text-white">Home</a></li>
        </ul>
			<div class="text-end">
            <a href ="../index.html">
          <button type="button" class="btn btn-warning me-2">Login</button>
          </a>
        </div>
      </div>
    </div>
  </header>


<div class="card text-dark bg-secondary text-center ps-5 mx-auto mt-5" style="width:50%">
<div class="card-header py-3 h4">We are creating account for the User: 
<%= bankUser.getFname()
%>  
<p class="h5">
 Fill the following form
</p>
</div>
    <form class="row g-3 p-3" action="createAccountInDB.jsp" method="post">
       
      
    
      <div class="row mb-3 pt-2">
        <label for="pg" class="col-sm-5 col-form-label">Account Type</label>
				<% if(id=='b'){ %>
				<div class="col-sm-7">
					<select class="form-select" aria-label="Default select example" id="pg" name="acctype" required>
						<option selected>Select your Account Type</option>
						<option value="1">Savings</option>
						<option value="2">Checking</option>
					</select>
				</div>
				<%} else if(id=='c') { %>
				<div class="col-sm-7">
					<select disable class="form-select" aria-label="Default select example" id="pg" name="acctype" required>
						<option value="2" selected>Checking</option>
					</select>
				</div>
				<%}
				else {
				%>
				<div class="col-sm-7">
					<select disable class="form-select" aria-label="Default select example" id="pg" name="acctype" required>
						<option value="1" selected>Saving</option>
					</select>
				</div>
				<% } %>
			</div>
			
      <div class="row mb-3 pt-3">
        <label for="fname" class="col-sm-5 col-form-label">How much is the initial Deposit</label>
        <div class="col-sm-7">
          <input type="number" step="0.01" class="form-control" id="fname" placeholder="0.00" name="deposit" required>
        </div>
      </div>
     	<div class="text-center">
      <button type="submit" class=" col-sm-6 btn btn-primary">Create Account</button>
      </div>
    </form>
  </div>	
</body>
</html>