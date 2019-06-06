<?php  error_reporting(E_ALL & ~E_NOTICE);
 $db_name = "inout_android";  
 $mysql_user = "root";  
 $mysql_pass = "";  
 $server_name = "localhost";
 $con = mysqli_connect($server_name,$mysql_user,$mysql_pass,$db_name);
mysqli_set_charset($con,"utf8");
 // if (!$con) {
 //   	# code...
 //   	die("Erroe database connect".mysqli_connect_error());
 //   }
 //   else{
 //   	echo "Database Connect Success";
 //   }
     
 ?>  