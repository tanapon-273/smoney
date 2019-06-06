 <?php  
 require "init.php";
 $name = $_POST["name"];  
 $username = $_POST["username"];  
 $password = $_POST["password"];  
$query = "select * from userinfo where username like '".$username."';";
$result = mysqli_query($con,$query);
if (mysqli_num_rows($result)>0) {
 	# code...
 	$response = array();
 	$code = "reg_false";
 	$message = "User Already Exist..";
 	array_push($response, array("code" =>$code,"message"=>$message));
 	echo json_encode(array("server_response"=>$response));
}
else
{
	$query = "INSERT INTO userinfo VALUES('".$name."','".$username."','".$password."');";
	$result = mysqli_query($con,$query);
	if (!$result)
	 {
		# code...
		$response = array();
		$code = "reg_false";
		$message = "Some Server error occurred. Try again...";
		array_push($response, array("code" =>$code,"message"=>$message));
 		echo json_encode(array("server_response"=>$response));
	}
	else
	{
		$response = array();
		$code = "reg_true";
		$message = "Registration Success...";
		array_push($response, array("code" =>$code,"message"=>$message));
 		echo json_encode(array("server_response"=>$response));
	}
} 
mysqli_close($con);
 ?>  