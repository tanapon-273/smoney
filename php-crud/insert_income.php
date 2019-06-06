 <?php  
 require "init.php";
 $id = $_POST["id"];
 $username = $_POST["username"];  
 $DateMillisec = $_POST['Date'];
 $income = $_POST["income"];
 $monincome = $_POST["monincome"];
 $Note = $_POST["Note"];
 date_default_timezone_set("Asia/Bangkok");
 // $Date = strtotime($Date);
 // $Date = date('Y-m-d H:i:s',$Date);
 $Date = date("Y-m-d H:i:s", $DateMillisec / 1000);

 $query = "select * from tb_income where id like '".$id."';";
$result = mysqli_query($con,$query);
if (mysqli_num_rows($result)>0) {
 	# code...
 	$response = array();
 	$code = "income_false";
 	$message = "User Already Exist..";
 	array_push($response, array("code" =>$code,"message"=>$message));
 	echo json_encode(array("server_response"=>$response));
}
else
{
	$query = "INSERT INTO tb_income VALUES('".$id."','".$username."','".$Date."','".$income."','".$monincome."','".$Note."');";
	$result = mysqli_query($con,$query);
	if (!$result)
	 {
		# code...
		$response = array();
		$code = "income_false";
		$message = "Some Server error occurred. Try again...";
		array_push($response, array("code" =>$code,"message"=>$message));
 		echo json_encode(array("server_response"=>$response));
	}
	else
	{
		$response = array();
		$code = "income_true";
		$message = "Insert Income Success...";
		array_push($response, array("code" =>$code,"message"=>$message));
 		echo json_encode(array("server_response"=>$response));
	}
} 
mysqli_close($con);
 ?>  