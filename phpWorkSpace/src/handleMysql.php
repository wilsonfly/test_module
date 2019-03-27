<?php

function judge() {
    if (function_exists('mysql_connect')) {
        echo "mysql already installed\n";
    }
}


judge();
