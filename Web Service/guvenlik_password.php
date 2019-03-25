<?php
include("ayar.php");

$mail = $_POST["mail"];
$sifre = $_POST["sifre"];

class Result
{
        public $tf;
        public $sonuc;
}

$result = new Result();

$sorgu = mysqli_query($baglanti,"Update Users Set sifre='$sifre' where email='$mail'");

if($sorgu)
{
        
        $result->tf=true;
        $result->sonuc = "Sifreniz Değiştirildi";      
}
else
{
        
        $result->tf=false;
        $result->sonuc = "Bir Hata Oluştu";      
}

echo(json_encode($result));



?>