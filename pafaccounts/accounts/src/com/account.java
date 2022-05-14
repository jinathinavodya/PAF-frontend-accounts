package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Account {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrogriddb", "root", "jina12");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//read
	public String readAccount()  
	{   
		String output = ""; 
	
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border=\"1\">"
					+ "		<tr>"
					+ "			<th>Account ID</th>"
					+ "			<th>City ID</th>"
					+ "			<th>Building No</th>"
					+ "			<th>Total Amount to Pay</th>"
					+ "			<th>User ID</th>"
					+ "		</tr>";
	 
			String query = "SELECT * FROM accounts";
			Statement stat = con.createStatement();
			ResultSet rSet = stat.executeQuery(query);
	 
			// iterate through the rows in the result set    
			while(rSet.next()) {
				String accountID = Integer.toString(rSet.getInt("accountID"));
				String city = Integer.toString(rSet.getInt("city"));
				String buildingNo = Double.toString(rSet.getDouble("buildingNo"));
				String totalAmtToPay = rSet.getString("totalAmtToPay");
				String userID = rSet.getString("userID");

				// Add into the HTML table 
				output += "<tr><td><input id='hidaccountIDUpdate' name='hidaccountIDUpdate' type='hidden' value='" + accountID + "'>" + userID+ "</td>";
				output += 	"<td>" + buildingNo +  "</td>";
				output += 	"<td>" + totalAmtToPay +  "</td>";
				output += 	"<td>" + city +  "</td>";
				output += 	"</tr>";

				// buttons     
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-accid='" + accountID + "'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-accid='" + accountID + "'>" + "</td></tr>"; 
		
			}
			con.close(); 
	 
			// Complete the HTML table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the account.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
	//insert account data
	public String insertAccount(String city, String buildingNo, String totalAmtToPay, String userID)  
	{   
		String output = ""; 
	 
		try
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to database";
			} 
	 
			// create a prepared statement 
			String query = "INSERT INTO accounts (`accountID`, `city`,`buildingNo`, `totalAmtToPay`, `userID` )\"\r\n"
					+ "							+ \"VALUES (?,?,?,?,?)"; 
	 
	 
			PreparedStatement prepStat = con.prepareStatement(query);
	 
			// binding values    
			prepStat.setInt(1, 0);
			prepStat.setString(2, city);
			prepStat.setString(3, buildingNo);
			prepStat.setDouble(4, Double.parseDouble(totalAmtToPay));
			prepStat.setString(5, userID);
			prepStat.setInt(6, Integer.parseInt(accountID));
			
			// execute the statement    
			prepStat.execute();    
			con.close(); 
	   
			String newAccount = readAccount(); 
//			output =  "{\"status\":\"success\", \"data\": \"" + newAccount + "\"}";   
			output = "Account Inserted Successfully!";

		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting new Account.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	}
	
	//update
	
	public String updateAccount(String accountID, String userID, String city, String buildingNo, String totalAmtToPay)    
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to database.";
			} 
	 
			// create a prepared statement    
			String query = "UPDATE accounts SET accountID=?, city=?, buildingNo=?, totalAmtToPay=?, where userID=?"; 
	 
			PreparedStatement prepStat = con.prepareStatement(query);
			
			//binding values
			prepStat.setInt(1, 0);
			prepStat.setString(2, city);
			prepStat.setString(3, buildingNo);
			prepStat.setDouble(4, Double.parseDouble(totalAmtToPay));
			prepStat.setString(5, userID);
			prepStat.setInt(6, Integer.parseInt(accountID));
			
			 // execute the statement
			prepStat.execute();    
			con.close(); 
	 
			String newAccount = readAccount();    
			output = "{\"status\":\"success\", \"data\": \"" + newAccount + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the Account.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	} 
	
	//delete
	public String deleteAccount(String accountID)   
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for deleting."; 
			} 
	 
			// create a prepared statement    
			String query = "DELETE FROM accounts WHERE accountID=?";  
	 
			PreparedStatement prepStat = con.prepareStatement(query); 
	 
			// binding values    
			prepStat.setInt(1, Integer.parseInt(accountID)); 
	 
			// execute the statement    
			prepStat.execute();    
			con.close(); 
	 
			String newAccount = readAccount();  
			    
			output = "{\"status\":\"success\", \"data\": \"" +  newAccount + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the account.\"}";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
}
