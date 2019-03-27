<?php

function showSession() {
    //开始使用session
    session_start();
    //设置一个session
    $_SESSION['test'] = time();
    //显示当前的session_id
    echo "session_id:".session_id();
    echo "<br>";
    echo "\n";

    //读取session值
    echo "session.test:".$_SESSION['test'];
    echo "\n";

    //销毁一个session
    unset($_SESSION['test']);
    echo "<br>";
    echo "\n";
    var_dump($_SESSION);
}

showSession();
