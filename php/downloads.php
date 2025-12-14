<?php
// 2. 定义保存目录（使用绝对路径更稳定，也可保留相对路径）
$saveDir = './data/';

// 3. 接收并过滤参数
$filename = $_POST['filename'] ?? '';

// 8. 拼接保存路径（强制加 .txt 后缀，避免文件名无后缀）
$savePath = $saveDir . $filename;

$retu = file_get_contents($savePath);
if($retu == false){
    exit("no");
}

echo $retu;
?>
