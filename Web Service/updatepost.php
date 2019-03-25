<?php
include("ayar.php");

$id = $_POST["id"];
$user_id =$_POST["user_id"];
$head = $_POST["head"];
$main = $_POST["main"];
$kategori = $_POST["kategori"];
$image = $_POST["image"];
$oldimage=$_POST["oldimage"];
$date = $_POST["date"];
$time = $_POST["time"];

/*$id = "19";
$user_id ="1";
$head = "head";
$main = "main";
$image = "image";
//$oldimage=$_POST["oldimage"];
$date = "30/08/2018";
$time = "05.30";*/

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
$ekle = mysqli_query($baglanti,"Update Activitys Set head='$head',main='$main',Kategori='$kategori',image='$path',date='$date',time='$time' where id='$id'");

//$ekle = mysqli_query($baglanti,"Update Activitys Set head='abc',main='asfsdfsv',image='$path',date='fsda',time='dsadg' where id='18'");

if($ekle)
{
        unlink("$oldimage");
        file_put_contents($path,base64_decode($image));
        $result->user_id=$user_id;
        $result->post_id=$id;
        $result->tf=true;
}
else
{
        $result->tf=false;
}

echo(json_encode($result));







?>