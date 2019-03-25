<?php
include("ayar.php");

$mail = $_POST["mail"];

class Result
{
        public $soru;
        public $cevap;
        public $sonuc;
        public $tf;
}

$result = new Result();

$sorgu = mysqli_query($baglanti,"Select * from Users where email='$mail'");

$kontrol = mysqli_num_rows($sorgu);

if($kontrol>0)
{
        $atama = mysqli_fetch_assoc($sorgu);
        $result->soru=$atama["guvenlik_sorusu"];
        $result->cevap=$atama["guvenlik_cevap"];   
        $result->sonuc="Güvenlik Sorusu Getirildi";
        $result->tf=true;
}
else
{
        $result->sonuc="Bu Mail Adresi Kayıtlı Değil";
        $result->tf=false;
}

echo(json_encode($result));



?>