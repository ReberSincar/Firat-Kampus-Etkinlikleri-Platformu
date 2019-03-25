<?php

include("ayar.php");

$userid=$_POST["userid"];

$sorgula = mysqli_query($baglanti, "Select * from Joins where user_id='$userid' order by id desc");
$sayac=0;

class Result
{
        public $id;
        public $userid;
        public $head;
        public $main;
        public $image;
        public $date;
        public $time;
        public $kategori;
        public $katilim;
        public $puan;
        public $begenme;
        public $tf;
        public $sonuc;

}

$result = new Result();

$say = mysqli_num_rows($sorgula);

echo("[");

if($say>0)
{
                
        while($atama=mysqli_fetch_assoc($sorgula))
        {
                $etkinlik = $atama["etkinlik_id"];
                $sorguActivity = mysqli_fetch_assoc(mysqli_query($baglanti,"Select * from Activitys where id='$etkinlik'"));
                $sayac=$sayac+1;
                $result->id=$sorguActivity["id"];
                $result->userid=$sorguActivity["user_id"];
                $result->head=$sorguActivity["head"];
                $result->main=$sorguActivity["main"];
                $result->kategori=$sorguActivity["Kategori"];
                $result->image=$sorguActivity["image"];
                $result->date=$sorguActivity["date"];
                $result->time=$sorguActivity["time"];
                $result->katilim=$sorguActivity["katilim"];
                $result->begenme=$sorguActivity["begenme"];
                $result->puan=$sorguActivity["puan"];
                $result->tf=true;
                $result->sonuc="Kayitlar Listelendi";
                echo(json_encode($result));
                
                if($say>$sayac)
                {
                        echo(",");
                }
        
        
        }
        
}
else
{
        $result->tf=false;
        $result->sonuc="Kayit Yok";
        echo(json_encode($result));

}

echo("]");


?>