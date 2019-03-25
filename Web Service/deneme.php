<?php
include("ayar.php");


$dt_min = new DateTime("last sunday"); // Geçen Pazar
$dt_min->modify('+1 day'); // Hafta Başını Buluyoruz.
$dt_max = clone($dt_min);
$dt_max->modify('+6 days'); // Hafta Sonunu Buluyoruz.
$haftabasi = $dt_min->format('d.m.Y');
$haftasonu = $dt_max->format('d/m/Y');

echo($haftasonu);
?>