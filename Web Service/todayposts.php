<?php

include("ayar.php");

$zaman = $_GET["zaman"];

$sorgula = mysqli_query($baglanti, "Select * from Activitys where date='$zaman' order by id desc");
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
                $ktg = $atama["Kategori"];
                $ktg = mysqli_fetch_assoc(mysqli_query($baglanti,"Select kategori from Kategoriler where id='$ktg'"));
                $result->kategori = $ktg["kategori"];
                $sayac=$sayac+1;
                $result->id=$atama["id"];
                $result->userid=$atama["user_id"];
                $result->head=$atama["head"];
                $result->main=$atama["main"];
                $result->image=$atama["image"];
                $result->date=$atama["date"];
                $result->time=$atama["time"];
                $result->katilim=$atama["katilim"];
                $result->puan=$atama["puan"];
                $result->begenme=$atama["begenme"];
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