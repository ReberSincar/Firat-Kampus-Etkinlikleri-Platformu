<?php

include("ayar.php");

$date = $_POST["month"];

$split = explode("/", $date);
$tarih=$split[1]."/".$split[2];
$sorgula = mysqli_query($baglanti, "Select * from Activitys where date LIKE '%$tarih' order by id desc");
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
                $ay = explode("/", $atama["date"]);
                if($ay[1]==$split[1] && $ay[2]==$split[2])
                {
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
                        
                        $sayac=$sayac+1;
                        
                        
                        if($say>$sayac)
                        {
                                echo(",");
                        }
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