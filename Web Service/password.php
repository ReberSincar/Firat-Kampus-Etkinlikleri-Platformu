<?php

include("ayar.php");

$userid=$_POST["userid"];

class Result
{
        public $password;
        public $tf;
}

$result = new Result();

$sorgu = mysqli_query($baglanti,"Select sifre from Users where id='$userid'");

if($sorgu)
{
        $atama = mysqli_fetch_assoc($sorgu);
        
        $result->password=$atama["sifre"];
        $result->tf=true;
}
else
{
        $result->tf=false;
}

echo(json_encode($result));




?>