package com.rebersincar.kampusetkinlik.Activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rebersincar.kampusetkinlik.Adapters.KategoriAdapter;
import com.rebersincar.kampusetkinlik.Adapters.KatildigimAdapter;
import com.rebersincar.kampusetkinlik.Models.KategoriPojo;
import com.rebersincar.kampusetkinlik.R;
import com.rebersincar.kampusetkinlik.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KategoriActivity extends AppCompatActivity {

    ListView listView;
    TextView baslik;
    KategoriAdapter kategoriAdapter;
    List<KategoriPojo> list;
    int kategori_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);
        listView = findViewById(R.id.kategoriPosts);
        baslik = findViewById(R.id.kategoriHead);
        listYukle();
        postAc();
    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),SelectKategoriActivity.class);
        startActivity(intent);
    }

    public void listYukle()
    {
        Bundle extras = getIntent().getExtras();
        kategori_id=extras.getInt("kategori_id");
        Call<List<KategoriPojo>> requestKategori = ManagerAll.getInstance().KategoriSec(kategori_id+1);
        requestKategori.enqueue(new Callback<List<KategoriPojo>>() {
            @Override
            public void onResponse(Call<List<KategoriPojo>> call, Response<List<KategoriPojo>> response) {
                if (response.isSuccessful())
                {
                    list = response.body();
                    Log.i("deneme",list.toString());

                    kategoriAdapter = new KategoriAdapter(list,getApplicationContext(),KategoriActivity.this);
                    listView.setAdapter(kategoriAdapter);
                    baslik.setText(SelectKategoriActivity.Categories[kategori_id]);
                }
                else
                    Log.i("deneme",list.toString());
            }

            @Override
            public void onFailure(Call<List<KategoriPojo>> call, Throwable t) {

            }
        });
    }

    public void postAc()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (list.get(position).getHead()==null)
                {
                    listView.setClickable(false);
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(),PostActivity.class);
                    intent.putExtra("head",list.get(position).getHead());
                    intent.putExtra("date",list.get(position).getDate());
                    intent.putExtra("id",list.get(position).getId());
                    intent.putExtra("image",list.get(position).getImage());
                    intent.putExtra("main",list.get(position).getMain());
                    intent.putExtra("time",list.get(position).getTime());
                    intent.putExtra("katilim",list.get(position).getKatilim());
                    intent.putExtra("kategori",list.get(position).getKategori());
                    intent.putExtra("puan",list.get(position).getPuan());
                    intent.putExtra("begenme",list.get(position).getBegenme());
                    startActivity(intent);
                }
            }
        });
    }
}
