<?php

$uniev=$_POST["yemek"];

function ara($bas, $son, $yazi)
{
        @preg_match_all('/' . preg_quote($bas, '/') .
        '(.*?)'. preg_quote($son, '/').'/i', $yazi, $m);
        return @$m[1];
}

$link = "http://uevi.firat.edu.tr";
$icerik = file_get_contents($link);

$yemek= ara('<p>','</p>',$icerik);
$kontrol = $yemek[1];
if(strpos($kontrol, '>') == true)
{
	$split1 = explode(">", $yemek[1]);
	$yemek1= $split1[1];
	$split1 = explode("<", $yemek1);
	$yemek[1] = $split1[0];

	$split1 = explode(">", $yemek[2]);
	$yemek1= $split1[1];
	$split1 = explode("<", $yemek1);
	$yemek[2] = $split1[0];

	$split1 = explode(">", $yemek[3]);
	$yemek1= $split1[1];
	$split1 = explode("<", $yemek1);
	$yemek[3] = $split1[0];

	$split1 = explode(">", $yemek[4]);
	$yemek1= $split1[1];
	$split1 = explode("<", $yemek1);
	$yemek[4] = $split1[0];
}

class Yemekler
{
    public $bir;
    public $iki;
    public $uc;
    public $dort;
}

$result = new Yemekler();

$result->bir = $yemek[1];
$result->iki = $yemek[2];
$result->uc = $yemek[3];
$result->dort = $yemek[4];

echo(json_encode($result));


?>