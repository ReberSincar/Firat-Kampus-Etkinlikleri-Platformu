<?php
include("ayar.php");

$user_id = $_GET["userid"];
$etkinlik_id = $_GET["etkinlikid"];

$kontrol = mysqli_num_rows(mysqli_query($baglanti,"Select * from Joins where user_id='$user_id' and etkinlik_id='$etkinlik_id'"));

class Result
{
        public $tf;
}

$result = new Result();

if($kontrol>0)
        $result->tf=true;
else
        $result->tf=false;

echo(json_encode($result));





?>