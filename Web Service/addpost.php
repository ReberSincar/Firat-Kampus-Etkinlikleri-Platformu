<?php
include("ayar.php");

$user_id =$_POST["user_id"];
$head = $_POST["head"];
$main = $_POST["main"];
$kategori = $_POST["kategori"];
$image = $_POST["image"];
$date = $_POST["date"];
$time = $_POST["time"];

$karakterler = "1234567890abcdefghijklmnopqrstuvwxyz";
$imagename = rand(0,999999).$karakterler{rand(0,35)}.rand(0,999999).$karakterler{rand(0,35)}.rand(0,999999).$karakterler{rand(0,35)}.rand(0,999999).$karakterler{rand(0,35)};
$path = "Images/$imagename.jpg";

class Result
{
        public $user_id;
        public $post_id;
        public $tf;
}

$result = new Result();
$ekle = mysqli_query($baglanti,"insert into Activitys(user_id,head,main,Kategori,image,date,time) Values('$user_id','$head','$main','$kategori','$path','$date','$time')");

//$ekle = mysqli_query($baglanti,"insert into Activitys(user_id,head,main,photo,date,time) Values('1','1','1','1','1','1')");

if($ekle)
{
        file_put_contents($path,base64_decode($image));
        $sorgu = mysqli_query($baglanti,"Select * from Activitys where user_id='$user_id' order by id desc");
        $sorgu = mysqli_fetch_assoc($sorgu);
        $result->user_id=$user_id;
        $result->post_id=$sorgu["id"];
        $result->tf=true;
}
else
{
        $result->tf=false;
}

echo(json_encode($result));







?>