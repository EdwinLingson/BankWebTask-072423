<%@page import="utils.BankManager"%>
<%

BankManager bm = (BankManager)session.getAttribute("manager");

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
          <li><a href="managerLoginRes.jsp" class="nav-link px-2 text-white">Home</a></li>
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
<div class="card-header py-3 h4">Please Enter your Details: </div>
    <form class="row g-3 p-3" action="createUserRes.jsp" method="post">
       <div class="row mb-3 pt-3">
        <label for="fname" class="col-sm-2 col-form-label">First Name</label>
        <div class="col-sm-7">
          <input type="text" class="form-control" id="fname" placeholder="Enter your First Name" name="fname" required>
        </div>
      </div>
      <div class="row mb-3">
        <label for="sname" class="col-sm-2 col-form-label">Last Name</label>
        <div class="col-sm-7">
          <input type="text" class="form-control" id="sname" placeholder="Enter your Last Name" name="lname" required>
        </div>
      </div>
      <div class="row mb-3">
        <label for="fname" class="col-sm-2 col-form-label">Email</label>
        <div class="col-sm-7">
          <input type="email" class="form-control" id="fname" placeholder="email@xyz.com" name="email" required>
        </div>
      </div>
      <div class="row mb-3">
        <label for="mobile" class="col-sm-2 col-form-label">Mobile</label>
        <div class="col-sm-7">
          <input type="number" class="form-control" id="mobile" placeholder="Enter your Phone Number" name="phNo" required>
        </div>
      </div>
      <div class="row mb-3">
        <label for="address" class="col-sm-2 col-form-label">Address 1</label>
        <div class="col-sm-7">
          <input type="text" class="form-control" id="address" placeholder="Enter your Address" name="address" required>
        </div>
      </div>
      
      <div class="row mb-3">
        
        <label for="address" class="col-sm-2 col-form-label">Address 2</label>
        <div class="col-sm-3">
          <input type="text" class="form-control" id="address" placeholder="City" name="city" required>
        </div>
        
       
        <div class="col-sm-2">
          <input type="text" class="form-control" id="address" placeholder="State" name="state" required>
        </div>
        
        <div class="col-sm-2">
          <input type="number" class="form-control" id="address" placeholder="ZIP" name="zip" required>
        </div>
      </div>
    
      <div class="row mb-3">
        <label for="uname" class="col-sm-2 col-form-label">User Name</label>
        <div class="col-sm-7">
          <input type="text" class="form-control" id="uname" placeholder="Enter your Username" name="uname" required>
        </div>
      </div>
      <div class="row mb-3">
        <label for="pword" class="col-sm-2 col-form-label">Password</label>
        <div class="col-sm-7">
          <input type="password" class="form-control" id="pword" placeholder="Enter your Password" name="pwd" required>
        </div>
      </div>
      <input type="hidden" name="mgrId" value = <%= bm.getUserId() %>>
     	<div class="text-center">
      <button type="submit" class=" col-sm-6 btn btn-primary">Register</button>
      </div>
    </form>
  </div>	
</body>
</html>