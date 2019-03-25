<?php
include("ayar.php");

$id=$_GET["id"];

$sorgu = mysqli_query($baglanti,"Select * from Activitys where id='$id'");

$kontrol = mysqli_num_rows($sorgu);


class Result
{
        public $tf;
        public $sonuc;
}

$result = new Result();

if($kontrol>0)
{
        $resim = mysqli_fetch_assoc($sorgu);
        $resim = $resim["image"];
        unlink($resim);
        
        $silJoin = mysqli_query($baglanti,"Delete from Joins where etkinlik_id='$id'");
        $silLike = mysqli_query($baglanti,"Delete from Likes where etkinlik_id='$id'");
        $sil = mysqli_query($baglanti,"Delete from Activitys where id='$id'");
        
        $result->tf=true;
        $result->sonuc="Etkinlik Kaldırıldı.";
}
else
{
        $result->tf=false;
        $result->sonuc="Bir Hata Oluştu.";
}

echo(json_encode($result));

?>