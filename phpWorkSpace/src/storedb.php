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
    $index=0;
    $stepSize=10;
    do {
        $selectAllResult = mysqli_query($conn, "select * from device_prop limit $index,$stepSize");
        if($selectAllResult==null || mysqli_num_rows($selectAllResult)==0) {
            break;
        }
        while($row = mysqli_fetch_array($selectAllResult)) {
            echo $row["mac"]." ".$row["version"]." ".$row["model"]."\n";
        }
        echo "=======i:".$index."\n";
        $index+=$stepSize;
    }while(true);
    //show latest data

    mysqli_free_result($result);
    mysqli_close($conn);
}

storeDB("99:11:22:33:44:00","v1.3","y32");
//storeDB("99:11:22:33:44:01","v1.3","y32");
//storeDB("99:11:22:33:44:02","v1.3","y32");
//storeDB("99:11:22:33:44:03","v1.3","y32");
//storeDB("99:11:22:33:44:04","v1.3","y32");
//storeDB("99:11:22:33:44:05","v1.3","y32");
//storeDB("99:11:22:33:44:05","v1.4","y32");
//storeDB("99:11:22:33:44:06","v1.3","y32");
//storeDB("99:11:22:33:44:07","v1.3","y32");
//storeDB("99:11:22:33:44:07","v1.5","y32");
//storeDB("99:11:22:33:44:08","v1.5","y32");
//storeDB("99:11:22:33:44:09","v1.5","y32");
//storeDB("99:11:22:33:44:10","v1.5","y32");
//storeDB("99:11:22:33:44:11","v1.5","y32");
//storeDB("99:11:22:33:44:12","v1.5","y32");
//storeDB("99:11:22:33:44:13","v1.5","y32");
//storeDB("99:11:22:33:44:14","v1.5","y32");
//storeDB("99:11:22:33:44:15","v1.5","y32");
//storeDB("99:11:22:33:44:16","v1.5","y32");
//storeDB("99:11:22:33:44:17","v1.5","y32");
//storeDB("99:11:22:33:44:18","v1.5","y32");
//storeDB("99:11:22:33:44:19","v1.5","y32");
//storeDB("99:11:22:33:44:20","v1.5","y32");
//storeDB("99:11:22:33:44:21","v1.5","y32");
//storeDB("99:11:22:33:44:22","v1.5","y32");
//storeDB("99:11:22:33:44:23","v1.5","y32");
//storeDB("99:11:22:33:44:24","v1.5","y32");
//storeDB("99:11:22:33:44:25","v1.5","y32");
//storeDB("99:11:22:33:44:26","v1.5","y32");
//storeDB("99:11:22:33:44:27","v1.5","y32");
//storeDB("99:11:22:33:44:28","v1.5","y32");
//storeDB("99:11:22:33:44:29","v1.5","y32");
//storeDB("99:11:22:33:44:30","v1.5","y32");
//storeDB("99:11:22:33:44:31","v1.5","y32");
//storeDB("99:11:22:33:44:32","v1.5","y32");
//storeDB("99:11:22:33:44:33","v1.5","y32");
//storeDB("99:11:22:33:44:34","v1.5","y32");
//storeDB("99:11:22:33:44:35","v1.5","y32");
//storeDB("99:11:22:33:44:36","v1.5","y32");
//storeDB("99:11:22:33:44:37","v1.5","y32");
//storeDB("99:11:22:33:44:38","v1.5","y32");
//storeDB("99:11:22:33:44:39","v1.5","y32");
//storeDB("99:11:22:33:44:40","v1.5","y32");
//storeDB("99:11:22:33:44:41","v1.5","y32");
//storeDB("99:11:22:33:44:42","v1.5","y32");
//storeDB("99:11:22:33:44:43","v1.5","y32");
//storeDB("99:11:22:33:44:44","v1.5","y32");
//storeDB("99:11:22:33:44:45","v1.5","y32");
