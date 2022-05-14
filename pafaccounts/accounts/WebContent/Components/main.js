 $(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	 {
	 	$("#alertSuccess").hide();
	 }
	 $("#alertError").hide(); 

}); 


$(document).on("click", "#btnSave", function(event)
{
	//Clear status msges---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	//Form validation-------------------
	var status = validateAccountForm();
	
	//If not valid
	if (status != true)
	{
		 $("#alertError").text(status);
		 $("#alertError").show();
		return;
	}
	
	//If valid
	$("#formAccount").submit();
	
	var type = ($("#hidaccountIDSave").val() == "") ? "POST" : "PUT";

	$.ajax(
 	{
 		url : "accountAPI",
 		type : type,
 		data : $("#formAccount").serialize(),
 		dataType : "text",
 		complete : function(response, status)
 		{
 			onAccountSaveComplete(response.responseText, status);
 		}
 	}); 
});



function onAccountSaveComplete(response, status)
	{
		if (status == "success")
		{
			 var resultSet = JSON.parse(response);
 			 if (resultSet.status.trim() == "success")
			 {
 				$("#alertSuccess").text("Successfully saved.");
 				$("#alertSuccess").show();
 				$("#divMeterGrid").html(resultSet.data);
 			 } 
 			 else if (resultSet.status.trim() == "error")
			 {
 				$("#alertError").text(resultSet.data);
 				$("#alertError").show();
 			 }
 		} 
 		else if (status == "error")
 		{
 			$("#alertError").text("Error while saving.");
 			$("#alertError").show();
 		} 
 		else
 		{
 			$("#alertError").text("Unknown error while saving..");
 			$("#alertError").show();
 		}
		$("#hidMeterIDSave").val("");
 		$("#formMeter")[0].reset();
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidaccountIDSave").val($(this).data("accountID"));
	$("#txtcity").val($(this).closest("tr").find('td:eq(0)').text());
	$("#txtbuildingNo").val($(this).closest("tr").find('td:eq(1)').text());
	$("#txttotalAmtToPay").val($(this).closest("tr").find('td:eq(2)').text());
	$("#txtuserID").val($(this).closest("tr").find('td:eq(3)').text());
}); 


// REMOVE==========================================
$(document).on("click", ".btnRemove", function(event)
{
	 $.ajax(
 		{
 			url : "accountAPI",
 			type : "DELETE",
 			data : "accountID=" + $(this).data("accountID"),
 			dataType : "text",
 			complete : function(response, status)
 			{
 				onMeterDeleteComplete(response.responseText, status);
 			}
 		});
}); 

function onAccountDeleteComplete(response, status)
{
		if (status == "success")
 		{
 			var resultSet = JSON.parse(response);
 			if (resultSet.status.trim() == "success")
 			{
 				$("#alertSuccess").text("Successfully deleted.");
 				$("#alertSuccess").show();
 				$("#divMeterGrid").html(resultSet.data);
 			} 
 			else if (resultSet.status.trim() == "error")
 			{
 				$("#alertError").text(resultSet.data);
 				$("#alertError").show();
 			}
 		} 
 		else if (status == "error")
 		{
 				$("#alertError").text("Error while deleting.");
 				$("#alertError").show();
 		} 
 		else
 		{
 				$("#alertError").text("Unknown error while deleting..");
 				$("#alertError").show();
 		}
}


function validateAccountForm()
{
	//userID
	if ($("#txtuserID").val().trim() == "")
	{
		return "Insert User ID";
	}
	
	//city
	if ($("#txtcity").val().trim() == "")
	{
		return "Insert city";
	}
	
	//buildingNo
	if ($("#txtbuildingNo").val() == "")
	{
		return "Insert building no";
	}
	
	//totalAmtToPay
	if ($("#txttotalAmtToPay").val() == "")
	{
		return "Enter amount";
	}
	
	return true;
}


// function getPaymentCard(accountID, amount, payMethod, payDate)
// {
	
// 	var payment = "";
// 	payment += "<div class=\"student card bg-light m-2\" style=\"max-width: 10rem; float: left;\">";
// 	payment += "<div class=\"card-body\">";
// 	payment += "accountID: " + accountID + ",";
// 	payment += "<br>";
// 	payment += "Amount: " + amount + ",";
// 	payment += "<br>";
// 	payment += "Pay Method: " + payMethod + ",";
// 	payment += "<br>";
// 	payment += "Date: " + payDate;
// 	payment += "</div>";
// 	payment += "<input type=\"button\" value=\"Remove\" class=\"btn btn-danger remove\">";
// 	payment += "</div>";
	
// 	return payment;
// }

