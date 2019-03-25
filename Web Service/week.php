<?php

include("ayar.php");

$date = $_POST["week"];

$dt_min = new DateTime("last sunday");
$dt_min->modify('+1 day');
$pazartesi = $dt_min->format('d/m/Y');
$dt_min->modify('+1 day');
$sali = $dt_min->format('d/m/Y');
$dt_min->modify('+1 day');
$carsamba = $dt_min->format('d/m/Y');
$dt_min->modify('+1 day');
$persembe = $dt_min->format('d/m/Y');
$dt_min->modify('+1 day');
$cuma = $dt_min->format('d/m/Y');
$dt_min->modify('+1 day');
$cumartesi = $dt_min->format('d/m/Y');
$dt_min->modify('+1 day');
$pazar = $dt_min->format('d/m/Y');


$sorgula = mysqli_query($baglanti, "Select * from Activitys where
date='$pazartesi' or
date='$sali' or
date='$carsamba' or
date='$persembe' or
date='$cuma' or
date='$cumartesi' or
date='$pazar'
order by id desc");

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
                $sayac=$sayac+1;
                $result->kategori = $atama["Kategori"];                
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