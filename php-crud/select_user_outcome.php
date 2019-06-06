<?php
require "init.php"; 
$username = $_POST["username"];
$sql = "SELECT * FROM tb_outcome where username = '".$username."'";
$res = mysqli_query($con,$sql);
while($row = mysqli_fetch_assoc($res)){
	$response = array();
	$output[] = $row;
	}
	array_push($response, array($output));	
	echo json_encode(array("response"=>$output));
	//json_encode(array("server_response"=>$response));
	mysqli_close($con);
?>