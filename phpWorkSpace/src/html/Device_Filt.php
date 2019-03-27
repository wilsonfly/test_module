<!DOCTYPE html>
<html>
<body>
<table border="1">
<tr>
<th>MAC</th>
<th>version</th>
<th>Model</th>
</tr>

<?php

    $query = $_POST['MAC'];


    echo $query;
    $conn = mysqli_connect("localhost","www-data","123456") or die("connect mysqli failed");
    mysqli_select_db($conn, "device_info") or die(mysqli_error($conn));

    $selectAllResult = mysqli_query($conn, "select * from device_prop where mac = '$query'");


    while($row = mysqli_fetch_array($selectAllResult)) {
    ?>
<tr>
<td><?php echo $row['mac'];?></td>
<td><?php echo $row['version'];?></td>
<td><?php echo $row['model'];?></td>
</tr>
<?php
    }
    mysqli_free_result($result);
    mysqli_close($conn);
?>

</table>
</body>
</html>
