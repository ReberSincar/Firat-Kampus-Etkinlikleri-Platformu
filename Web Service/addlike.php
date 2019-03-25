<?php
include("ayar.php");

$etkinlik_id=$_GET["etkinlikid"];
$user_id=$_GET["userid"];

class Result
{
        public $tf;
        public $sonuc;
}

$result = new Result();

$kontrolLike = mysqli_num_rows(mysqli_query($baglanti,"Select * from Likes where user_id='$user_id' and etkinlik_id='$etkinlik_id'"));
if($kontrolLike==0)
{
        mysqli_query($baglanti,"Insert into Likes(user_id,etkinlik_id) Values('$user_id','$etkinlik_id')");
        $like1 = mysqli_fetch_assoc(mysqli_query($baglanti,"Select begenme from Activitys where id='$etkinlik_id'"));
        $like2 = $like1["begenme"];
        $like2 = $like2+1;
                        
        mysqli_query($baglanti,"Update Activitys Set begenme='$like2' where id='$etkinlik_id'");
                        
        $join = mysqli_fetch_assoc(mysqli_query($baglanti,"Select katilim from Activitys where id='$etkinlik_id'"));
        $join = $join["katilim"];
                        
        $puan = ($like2*100)/$join;
        mysqli_query($baglanti,"Update Activitys Set puan='$puan' where id='$etkinlik_id'");
                        
        $result->tf=true;
        $result->sonuc="Beğendiniz";
}

echo(json_encode($result));


?>