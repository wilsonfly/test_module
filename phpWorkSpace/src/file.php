<?php

function showFileTime() {
    $filename = './file.php';
    echo '所有者：'.fileowner($filename)."\n";
    echo '创建时间：'.filectime($filename)."\n";
    echo '修改时间：'.filemtime($filename)."\n";
    echo '最后访问时间：'.fileatime($filename)."\n";

    //format the time
    echo '最后访问时间：'.date('Y-m-d H:i:s',fileatime($filename))."\n";

    //给$mtime赋值为文件的修改时间
    $mtime = time(); 
    //通过计算时间差 来判断文件内容是否有效
    if (time() - $mtime > 3600) {
        echo '<br>缓存已过期';
    } else {
        echo file_get_contents($filename);
    }
}

function getFileSize() {
    //$filename = "./file.php";
    $filename = "./Hanyu-47th.mp3";
    $size = filesize($filename);
    echo $size."\n";

    $sizeFormat = number_format($size,4);
    echo $sizeFormat."\n";

    $sizeK = $size / pow(1024,1);
    echo $sizeK."\n";

    $sizeM = $size / pow(1024,2);
    echo $sizeM."\n";
}

function writeFile() {
    $filename = "./tmp.txt";
    $arr = array("aaa"=>"111","bbb"=>"222");
    file_put_contents($filename,"xxxxxxx");
    file_put_contents($filename,"yyyy");
    file_put_contents($filename,$arr);

    /*
    $fp = fopen($filename,'w');
    fwrite($fp,"hhhhhhhhello");
    fwrite($fp,"wwwwwwwworld");
    fclose($fp);
    */
}

function deleteFile() {
    $dir = "./tmp/";
    foreach(glob($dir."./*") as $filename) {
        unlink($filename);
    }
    rmdir($dir);

}
//showFileTime();
//getFileSize();
//writeFile();
deleteFile();
