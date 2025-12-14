<?php
// 1. 强制限制仅 POST 请求（防止 GET 访问导致参数为空）
if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
    exit("no");
}

// 2. 定义保存目录（使用绝对路径更稳定，也可保留相对路径）
$saveDir = './data/';

// 3. 接收并过滤参数
$filename = $_POST['filename'] ?? '';
$filedata = $_POST['filedata'] ?? '';

// 8. 拼接保存路径（强制加 .txt 后缀，避免文件名无后缀）
$savePath = $saveDir . $filename;

// 9. 写入文件（指定 UTF-8 编码，避免中文乱码）
// file_put_contents 第二个参数传字符串时，默认按 UTF-8 写入（需确保 filedata 是 UTF-8 编码）
$writeResult = file_put_contents($savePath, $filedata);

// 10. 最终判断（注意：file_put_contents 成功时返回写入的字节数，0 表示空内容（但你已校验 filedata 非空））
if ($writeResult === false) {
    exit("no");
} else {
    echo "yes";
}
?>
