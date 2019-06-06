<?php 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//Getting values 
		$id = $_POST["id"];
 		$Date = $_POST['Date'];
 		$outcome = $_POST["outcome"];
 		$monoutcome = $_POST["monoutcome"];
 		$Note = $_POST["Note"];
 		date_default_timezone_set("Asia/Bangkok");
   		$Date = date("Y-m-d H:i:s", $Date / 1000);
		
		//importing database connection script 
		require "init.php";
		
		//Creating sql query 
		$sql = "UPDATE tb_outcome SET Date = '$Date', outcome = '$outcome' ,monoutcome = '$monoutcome', Note = '$Note' WHERE id = $id;";
		
		//Updating database table 
		if(mysqli_query($con,$sql)){
			echo 'Outcome Updated Successfully ID : '.$id;
		}else{
			echo 'Could Not Update Outcome Try Again';
		}
		
		//closing connection 
		mysqli_close($con);
	}