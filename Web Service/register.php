<?php
include("ayar.php");

$ad=$_POST["ad"];
$soyad=$_POST["soyad"];
$mail=$_POST["email"];
$guvenlikSoru=$_POST["soru"];
$guvenlikCevap=$_POST["cevap"];
$sifre=$_POST["sifre"];

class Result
{
        public $result;
        public $tf;
}

$sonuc = new Result();

$kontrol = mysqli_query($baglanti,"Select * from Users where email='$mail'");

if(mysqli_num_rows($kontrol)==0)
{
        $ekle = mysqli_query($baglanti,"insert into Users(ad,soyad,email,sifre,guvenlik_sorusu,guvenlik_cevap) Values('$ad','$soyad','$mail','$sifre','$guvenlikSoru','$guvenlikCevap')");
        if($ekle)
        {
               $sonuc->result="Kayit Basarili";
               $sonuc->tf=true;
        }
}
else
{
       $sonuc->result="Mail Zaten Kayitli";
       $sonuc->tf=false;
}

echo(json_encode($sonuc));


?>