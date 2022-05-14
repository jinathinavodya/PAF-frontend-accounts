<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>

<title>Accounts</title>
</head>
<body>


	<div class="container">
		 <div class="row">
			 <div class="col-8">
			 
				 <h1 class="m-3">Account details</h1>
				 <form id="formAccount">
				 
				 	<!-- AccountID -->
					<!-- <div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblAccountID">Account ID: </span>
						</div>
						
						<input type="text" id="txtAccountID" name="txtAccountID">
					</div> -->
					
					<!-- city -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblcity">City: </span>
						</div>
						
						<input type="text" id="txtcity" name="txtcity">
					</div>
					
					<!-- buildingNo -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblbuildingNo ">Building No : </span>
						</div>
						
						<input type="text" id="txtbuildingNo " name="txtbuildingNo" placeholder="Enter buildingNo">
					</div>
					
					<!-- totalAmtToPay -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lbltotalAmtToPay">Total Amount To Pay: </span>
						</div>
						
						<input type="text" id="txttotalAmtToPay" name="txttotalAmtToPay" placeholder="amount">
					</div>
					
					
					<div id="alertSuccess" class="alert alert-success"></div>
 					<div id="alertError" class="alert alert-danger"></div>
					
					
					<input type="button" id="btnSave" value="Save" class="btn btn-primary">
					<input type="hidden" id="hidaccountIDSave" name="hidaccountIDSave" value="">
				 
				 </form>
			 </div>
		 </div>
		
		 <br>
		 <div class="row">
			 <div class="col-12" id="colAccount">
				<%
					Account accObj = new Account();
					out.print(accObj.readAccount());
				%>
			 </div>
		 </div>
	</div>

</body>
</html>