<?php
    $files = scandir("./data/");
    $return_list=[];

    foreach ($files as $file) {
        if ($file !== '.' && $file !== '..') {
            $return_list[] = $file;
        }
    }

    echo implode(',',  $return_list);
?>
