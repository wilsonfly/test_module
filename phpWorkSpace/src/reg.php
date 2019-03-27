<?php


//mark
function testReplace() {
    $patterns = array ('/(19|20)(\d{2})-(\d{1,2})-(\d{1,2})/',
        '/^\s*{(\w+)}\s*=/');
    $replace = array ('\3/\4/\1\2', '$\1 =');//\3等效于$3,\4等效于$4，依次类推
    echo preg_replace($patterns, $replace, '{startDate} = 1999-5-27'); //结果为：$startDate = 5/27/1999
    echo "\n";
}

function testReplace1() {
    $str = 'one     two';
    $str = preg_replace('/\s+/', ' ', $str);
    echo $str; // 结果改变为'one two'
    echo "\n";
}

function testReplace2() {
    $str = '主要有以下几个文件：index.php, style.css, common.js';
    $patterns='/(\w+\.\w+)/';
    $replace='<em>$1</em>';
    $str=preg_replace($patterns,$replace,$str);
    echo $str;
    echo "\n";
}

function verifyInfoForm() {
    $user = array(
        'name' => 'spark1985',
        'email' => 'spark@imooc.com',
        'mobile' => '13312345678'
    );
    //进行一般性验证
    if (empty($user)) {
        die('用户信息不能为空');
    }
    if (strlen($user['name']) < 6) {
        die('用户名长度最少为6位');
    }
    //用户名必须为字母、数字与下划线
    if (!preg_match('/^\w+$/i', $user['name'])) {
        die('用户名不合法');
    }
    //验证邮箱格式是否正确
    if (!preg_match('/^[\w\.]+@\w+\.\w+$/i', $user['email'])) {
        die('邮箱不合法');
    }
    //手机号必须为11位数字，且为1开头
    if (!preg_match('/^1\d{10}$/i', $user['mobile'])) {
        die('手机号不合法');
    }
    echo '用户信息验证成功';
    echo "\n";
}


testReplace();
testReplace1();
testReplace2();
verifyInfoForm();
