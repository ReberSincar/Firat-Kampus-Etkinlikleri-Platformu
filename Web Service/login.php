<?php
include("ayar.php");

$mail=$_POST["mail"];
$parola=$_POST["sifre"];

Class Uye
{
  public $id;
  public $admin;
  public $email;  
}

$uye = new Uye();

$kontrol=@mysqli_fetch_assoc(mysqli_query($baglanti,"Select * from Users where email='$mail' and sifre='$parola'"));

$uye->id=$kontrol["id"];
$uye->admin=$kontrol["admin"];
$uye->email=$kontrol["email"];


echo(json_encode($uye));


?>