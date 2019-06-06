<?php 
	
	//Getting the requested id
	$id = $_GET['id'];
	
	//Importing database
	require "init.php";
	
	//Creating sql query with where clause to get an specific employee
	$sql = "SELECT * FROM tb_outcome WHERE id=$id";
	
	//getting result 
	$r = mysqli_query($con,$sql);
	
	//pushing result to an array 
	$result = array();
	$row = mysqli_fetch_array($r);
	array_push($result,array(
			"id"=>$row['id'],
			"username"=>$row['username'],
			"Date"=>$row['Date'],
			"outcome"=>$row['outcome'],
			"monoutcome"=>$row['monoutcome'],
			"Note"=>$row['Note']
		));

	//displaying in json format 
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);