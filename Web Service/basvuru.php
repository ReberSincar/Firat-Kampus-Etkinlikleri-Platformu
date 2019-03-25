<?php

include("ayar.php");

$userid = $_POST["userid"];

class Result
{
        public $tf;
        public $sonuc;
}

$result = new Result();

$kontrol = mysqli_num_rows(mysqli_query($baglanti,"Select * from Basvurular where user_id='$userid'"));

if($kontrol==0)
{
        $ad = mysqli_fetch_assoc(mysqli_query($baglanti,"Select * from Users where id = '$userid'"));
        $ad = $ad["ad"]." ".$ad["soyad"];
        $sorgu = mysqli_query($baglanti,"Insert into Basvurular(user_id,ad_soyad) Values('$userid','$ad')");
        
        if($sorgu)
        {
                $result->tf=true;
                $result->sonuc="Başvurunuz Yapıldı";
        }
        else
        {
                $result->tf=false;
                $result->sonuc="Bir Hata Oluştu";
        }
}
else
{
        $result->tf=false;
        $result->sonuc="Zaten Başvurunuz Bulunuyor";
}



echo(json_encode($result));

?>