<?php 
	//Getting Id
	$id = $_GET['id'];
 
	//Importing database
	require "init.php";
	
	//Creating sql query
	$sql = "DELETE FROM tb_income WHERE id=$id";
	
	//Deleting record in database 
	if(mysqli_query($con,$sql)){
		echo 'Income Deleted Successfully ID :' .$id;
	}else{
		echo 'Could Not Delete Income Try Again';
	}
	
	//closing connection 
	mysqli_close($con);