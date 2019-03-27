<!DOCTYPE html>
<html>
<body>
<table border="1">

<?php

    $username = $_POST['username'];
    $password = $_POST['password'];

    //echo $username;
    //echo $password;

    $conn = mysqli_connect("localhost","www-data","123456") or die("connect mysqli failed");
    mysqli_select_db($conn, "user_info") or die(mysqli_error($conn));

    $result = mysqli_query($conn, "select * from loginTbl where username = '$username'");

    if($result!=null && mysqli_num_rows($result)!=0) {
	$row = mysqli_fetch_array($result);
	if($password==$row['password']) {
            //echo "same password";
            include('homepage.html');
	} else {
            echo "wrong password";
	}
    } else {
        echo "not found the user:".$username;
    }
    mysqli_free_result($result);
    mysqli_close($conn);
?>

</table>
</body>
</html>
