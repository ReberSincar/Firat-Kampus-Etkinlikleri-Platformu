package com.rebersincar.kampusetkinlik.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.rebersincar.kampusetkinlik.Adapters.SelectKategoriAdapter;
import com.rebersincar.kampusetkinlik.Models.Kategories;
import com.rebersincar.kampusetkinlik.R;

import java.util.ArrayList;
import java.util.List;

public class SelectKategoriActivity extends AppCompatActivity {

    ListView list;
    Intent intent;
    final List<Kategories> kategoriler = new ArrayList<>();
    public static String[] Categories= {
            "Eğitim",
            "Etkinlik",
            "Festival",
            "Gezi",
            "Kamp",
            "Kariyer Günleri",
            "Konferans",
            "Konser",
            "Networking",
            "Ödül Töreni",
            "Panel",
            "Sempozyum",
            "Yarışma",
            "Zirve",
    };
    public static int[] kategoriIdArray = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_kategori);
        list = findViewById(R.id.kategoriList);
        ekle();

        SelectKategoriAdapter adapter = new SelectKategoriAdapter(this,kategoriler);
        list.setAdapter(adapter);

        ac();
    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    public void ekle()
    {
        for (int i=0;i<Categories.length;i++)
        {
            kategoriler.add(new Kategories(Categories[i]));
        }
    }

    public void ac()
    {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    intent = new Intent(getApplicationContext(),KategoriActivity.class);
                    intent.putExtra("kategori_id",position);
                    startActivity(intent);
            }
        });
    }
}
