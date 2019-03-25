<?php

include("ayar.php");

$userid=$_GET["userid"];

$sorgula = mysqli_query($baglanti, "Select * from Activitys where user_id='$userid' order by id desc");
$sayac=0;

class Result
{
        public $id;
        public $userid;
        public $head;
        public $main;
        public $kategori;
        public $image;
        public $date;
        public $time;
        public $katilim;
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
                $result->id=$atama["id"];
                $result->userid=$atama["user_id"];
                $result->head=$atama["head"];
                $result->main=$atama["main"];
                $result->kategori=$atama["Kategori"];
                $result->image=$atama["image"];
                $result->date=$atama["date"];
                $result->time=$atama["time"];
                $result->katilim=$atama["katilim"];
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