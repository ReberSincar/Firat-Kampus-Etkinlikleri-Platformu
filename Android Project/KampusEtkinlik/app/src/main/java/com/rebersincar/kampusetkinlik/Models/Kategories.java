package com.rebersincar.kampusetkinlik.Models;

public class Kategories {

    public String kategori_ismi;

    public Kategories(String kategori_ismi) {
        this.kategori_ismi = kategori_ismi;
    }

    @Override
    public String toString() {
        return "Kategories{" +
                "kategori_ismi='" + kategori_ismi + '\'' +
                '}';
    }

    public String getKategori_ismi() {
        return kategori_ismi;
    }

}
