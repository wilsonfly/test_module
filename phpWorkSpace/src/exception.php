<?php

function showTrace() {
    try {
        throw new Exception('something is wrong');
    } catch(Exception $e) {
        echo 'Error:'.$e->getMessage()."\n";
        echo $e->getTraceAsString()."\n";
        echo $e->getFile()."\n";
        echo $e->getLine()."\n";

        $msg = $e->getMessage()."\n";
        $msg .= $e->getTraceAsString()."\n";
        $msg .= $e->getFile().":".$e->getLine()."\n";
        file_put_contents("./error.log", $msg);
    }
    echo "异常处理后，继续执行其他代码\n";
}

showTrace();
