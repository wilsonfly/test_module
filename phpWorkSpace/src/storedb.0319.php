<?php

function storeDB($mac, $version, $model) {
    $createTbl = "create table device_prop(mac char(17) not null unique primary key,version char(16),model char(16))"; 
    $selectSql = "select * from device_prop where mac='".$mac."'";
    $insertSql = "insert into device_prop (mac,version,model) values ('".$mac."','".$version."','".$model."')";
    $updateSql = "update device_prop set version='".$version."',model='".$model."' where mac='".$mac."'";

    $conn = mysqli_connect("localhost","root","huasheng2011") or die("connect mysqli failed");
    mysqli_select_db($conn, "device_info") or die(mysqli_error($conn));

    //create the table once it does not exist
    mysqli_query($conn, $createTbl);

    $result = mysqli_query($conn,$selectSql);
    if($result!=null && mysqli_num_rows($result)!=0) {
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

storeDB("99:11:22:33:44:05","v1.3","y32");
storeDB("99:11:22:33:44:05","v1.4","y32");
storeDB("99:11:22:33:44:06","v1.3","y32");
storeDB("99:11:22:33:44:07","v1.3","y32");
storeDB("99:11:22:33:44:07","v1.5","y32");
