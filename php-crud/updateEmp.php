<?php 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//Getting values 
		$id = $_POST["id"];
 		$Date = $_POST['Date'];
 		$income = $_POST["income"];
 		$monincome = $_POST["monincome"];
 		$Note = $_POST["Note"];
 		date_default_timezone_set("Asia/Bangkok");
   //      $Date = strtotime($Date);
 		// $Date = date('Y-m-d H:i:s',$Date);
		$Date = date("Y-m-d H:i:s", $Date / 1000);
		require "init.php";
		
		//Creating sql query 
		$sql = "UPDATE tb_income SET Date = '$Date', income = '$income' ,monincome = '$monincome', Note = '$Note' WHERE id = $id;";
		
		//Updating database table 
		if(mysqli_query($con,$sql)){
			echo 'Income Updated Successfully ID : '.$id;
		}else{
			echo 'Could Not Update Income Try Again';
		}
		
		//closing connection 
		mysqli_close($con);
	}