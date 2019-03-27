<?php
#echo "123";
$conn = new mysqli();
$conn -> connect('localhost','www-data','123456','test');

if ($conn -> connect_errno) {
    printf("Connect failed: %s\n", $conn->connect_error);
    exit();
}

echo "345";
$sql = "select * from test_table";
$query = $conn->query($sql);
while($row = $query->fetch_array()){
    echo $row['property1'];
}

$query1 -> free_result();
$conn -> close();
?>

