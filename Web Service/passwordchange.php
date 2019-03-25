<?php

include("ayar.php");

$userid=$_POST["userid"];
$password=$_POST["password"];

class Result
{
        public $tf;
        public $sonuc;
}

$result = new Result();

$sorgu = mysqli_query($baglanti,"Update Users Set sifre='$password' where id='$userid'");

if($sorgu)
{
        $result->sonuc="Şifreniz Değiştirildi";
        $result->tf=true;
}
else
{
        $result->sonuc="Bir Hata Oluştu";
        $result->tf=false;
}

echo(json_encode($result));




?>