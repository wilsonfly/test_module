<?php


function storeDB($mac, $version, $model) {
    $createTbl = "create table device_prop(mac char(17) not null unique primary key,version char(16),model char(16))";
    $selectSql = "select * from device_prop where mac='".$mac."'";
    $insertSql = "insert into device_prop (mac,version,model) values ('".$mac."','".$version."','".$model."')";
    $updateSql = "update device_prop set version='".$version."',model='".$model."' where mac='".$mac."'";

    $conn = mysqli_connect("localhost","www-data","123456") or die("connect mysqli failed");
    mysqli_select_db($conn, "device_info") or die(mysqli_error($conn));

    //create the table once it does not exist
    mysqli_query($conn, $createTbl);

    $result = mysqli_query($conn,$selectSql);
    if($result != null && mysqli_num_rows($result)!=0) {
        //print_r($result);
        echo "will update data\n";
        mysqli_query($conn, $updateSql);
    } else {
        //var_dump($result);
        echo "will insert data\n";
        mysqli_query($conn, $insertSql);
    }

    //show latest data
    $selectAllResult = mysqli_query($conn, "select * from device_prop");
    while($row = mysqli_fetch_array($selectAllResult)) {
        echo $row["mac"]." ".$row["version"]." ".$row["model"]."\n";
    }
    //show latest data

    mysqli_free_result($result);
    mysqli_close($conn);
}


$header = getallheaders();
print_r($header);
echo "\n";
print_r($_SERVER['REQUEST_URI']);
echo "\n\n";
$jsonstr = file_get_contents('php://input');

$arr = json_decode($jsonstr,true);


StoreDB($arr['mac'],$arr['version'],$arr['modelName']);

print_r("store finish\n");




header('Content-Type: application/json');




?>
