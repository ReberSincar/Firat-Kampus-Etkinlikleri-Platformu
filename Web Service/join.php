<?php
include("ayar.php");

$etkinlik_id=$_POST["etkinlik_id"];
$user_id=$_POST["user_id"];
$sayi1=0;
$sayi2=0;

class Result
{
        public $tf;
        public $sonuc;
}

$result = new Result();

$kontrol = mysqli_num_rows(mysqli_query($baglanti,"Select * from Joins where user_id='$user_id' and etkinlik_id='$etkinlik_id'"));
if($kontrol==0)
{
        $sayi1 = mysqli_fetch_assoc(mysqli_query($baglanti,"Select katilim from Activitys where id='$etkinlik_id'"));
        $sayi2=$sayi1["katilim"];
        $sayi1=$sayi1["katilim"];
        $sayi2=$sayi2+1;
        $join = mysqli_query($baglanti,"Update Activitys Set katilim='$sayi2' where id='$etkinlik_id'");
        
        $sayi2 = mysqli_fetch_assoc(mysqli_query($baglanti,"Select katilim from Activitys where id='$etkinlik_id'"));
        $sayi2=$sayi2["katilim"];
                
        $like = mysqli_fetch_assoc(mysqli_query($baglanti,"Select begenme from Activitys where id='$etkinlik_id'"));
        $like = $like["begenme"];
                
        $puan = ($like*100)/$sayi2;
        mysqli_query($baglanti,"Update Activitys Set puan='$puan' where id='$etkinlik_id'");
}





if($sayi1<$sayi2)
{
        $sorgu = mysqli_query($baglanti,"Insert into Joins(user_id,etkinlik_id) Values('$user_id','$etkinlik_id')");
        $result->tf=true;
        $result->sonuc="Katılımınız Eklendi";
}
else
{
        $result->tf=false;
        $result->sonuc="Bu Etkinliğe Katılacağınızı Zaten Belirttiniz";
}
echo(json_encode($result));

?>