package com.rebersincar.kampusetkinlik.Models;

public class YemekPojo {

    private String bir;
    private String iki;
    private String uc;
    private String dort;

    public String getYemek1(){
        return bir;
    }
    public String getYemek2(){
        return iki;
    }
    public String getYemek3(){
        return uc;
    }
    public String getYemek4(){
        return dort;
    }

    @Override
    public String toString(){
        return
                "YemekPojo{" +
                        "yemek1 = '" + bir + '\'' +
                        "yemek2 = '" + iki + '\'' +
                        "yemek3 = '" + uc + '\'' +
                        "yemek4 = '" + dort + '\'' +
                        "}";
    }
}
