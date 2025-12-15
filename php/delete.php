<?php

    // 1. 基础防护：仅允许 POST 请求
    if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
        http_response_code(405); // 方法不允许
        exit('仅支持POST请求');
    }

    $filename = $_POST['filename'] ?? '';

    $filePath = './data/' . $filename;

    if (file_exists($filePath)) {
        chmod($filePath, 0644); // 赋予写权限
        unlink($filePath);
        exit("yes");
    }

    echo "no";

?>
