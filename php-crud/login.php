<?php
require "init.php";
$username = $_POST["username"];  
$password = $_POST["password"];  

$query = "select * from userinfo where username like '".$username."' and password like '".$password."';";
$result = mysqli_query($con,$query);
if (mysqli_num_rows($result)>0) {
	$response = array();
	$code = "login_true";
	$row = mysqli_fetch_array($result);
	$name = $row[0];
	$message = "Login Success Welcome : ".$name;
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode(array("server_response"=>$response));
}
else{
	$response = array();
	$code = "Login_failed";
	$message = "Please Login again";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode(array("server_response"=>$response));
}
mysqli_close($con);

?>