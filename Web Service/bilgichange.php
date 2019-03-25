<?php

include("ayar.php");

$userid=$_POST["userid"];
$ad=$_POST["ad"];
$soyad=$_POST["soyad"];
$mail=$_POST["mail"];

class Result
{
        public $tf;
        public $sonuc;
}

$result = new Result();

$sorgu = mysqli_query($baglanti,"Update Users Set ad='$ad',soyad='$soyad',email='$mail' where id='$userid'");

if($sorgu)
{
        $result->sonuc="Bilgileriniz Değiştirildi";
        $result->tf=true;
}
else
{
        $result->sonuc="Bir Hata Oluştu";
        $result->tf=false;
}

echo(json_encode($result));




?>