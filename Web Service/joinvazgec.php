<?php
include("ayar.php");

$user_id = $_POST["userid"];
$etkinlik_id = $_POST["etkinlikid"];

$sil = mysqli_query($baglanti,"Delete from Joins where user_id='$user_id' and etkinlik_id='$etkinlik_id'");

class Result
{
        public $tf;
        public $sonuc;
}

$result = new Result();

if($sil)
{
        $cek = mysqli_fetch_assoc(mysqli_query($baglanti,"Select katilim from Activitys where id='$etkinlik_id'"));
        $katilim = $cek["katilim"];
        $katilim = $katilim-1;
        mysqli_query($baglanti,"Update Activitys Set katilim='$katilim' where id='$etkinlik_id'");
        $result->tf=true;
        $result->sonuc="Etkinlikten Katılımınız Kaldırıldı";
}
        
else
{
        $result->tf=false;
        $result->sonuc="Bir Sorun Oluştu";
}
        

echo(json_encode($result));


?>