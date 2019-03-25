<?php


$to="rebersincar07@gmail.com";
$konu ="Konu";
$mesaj ="İçerik";
$sender ="From: <dogrulama@firatkampus.atwebpages.com>";
$gonder = mail($to,$konu,$mesaj,$sender);
if($gonder)
{
        echo("Gonderildi");
}
else
{
        echo("Hata");
}
?>