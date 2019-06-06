<?php
require "init.php"; 
$sql = "SELECT * FROM tb_outcome";
$res = mysqli_query($con,$sql);
while($row = mysqli_fetch_assoc($res)){
	$response = array();
	$output[] = $row;
	}	
	array_push($response, array($output));
	echo json_encode(array("response"=>$output));
	mysqli_close($con);
?>