<?php
include("ayar.php");

$soru=$_POST["soru"];
$cevap=$_POST["cevap"];
$userid=$_POST["userid"];

class Result
{
        public $sonuc;
        public $tf;
}

$result = new Result();

$sorgu=mysqli_query($baglanti,"Update Users Set guvenlik_sorusu='$soru',guvenlik_cevap='$cevap' where id='$userid'");

if($sorgu)
{
        $result->tf=true;
        $result->sonuc="Güvenlik Bilgileriniz Değiştirildi";
}
else
{
        $result->tf=false;
        $result->sonuc="Bir Hata Oluştu";
}

echo(json_encode($result));


?>