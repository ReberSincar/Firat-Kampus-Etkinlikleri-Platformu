package com.rebersincar.kampusetkinlik.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.rebersincar.kampusetkinlik.Adapters.KatildigimAdapter;
import com.rebersincar.kampusetkinlik.Adapters.TodayAdapter;
import com.rebersincar.kampusetkinlik.Models.KatildigimPojo;
import com.rebersincar.kampusetkinlik.Models.TodayPojo;
import com.rebersincar.kampusetkinlik.R;
import com.rebersincar.kampusetkinlik.RestApi.ManagerAll;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KatildigimActivity extends AppCompatActivity {

    ListView listView;
    KatildigimAdapter adapter;
    List<KatildigimPojo> list;

    int userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katildigim);

        listView = findViewById(R.id.katilmaPosts);
        userid = LoginActivity.sharedPreferences.getInt("uye_id",0);

        listTodayActivitys();
        postAc();
    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    public void listTodayActivitys()
    {
        list = new ArrayList<>();
        Call<List<KatildigimPojo>> request = ManagerAll.getInstance().Katildigim(userid);
        request.enqueue(new Callback<List<KatildigimPojo>>() {
            @Override
            public void onResponse(Call<List<KatildigimPojo>> call, Response<List<KatildigimPojo>> response) {
                list = response.body();
                adapter = new KatildigimAdapter(list,getApplicationContext());
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<KatildigimPojo>> call, Throwable t) {

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
