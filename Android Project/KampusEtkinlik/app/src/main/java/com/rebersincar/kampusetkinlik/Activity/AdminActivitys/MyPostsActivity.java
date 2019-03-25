package com.rebersincar.kampusetkinlik.Activity.AdminActivitys;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.rebersincar.kampusetkinlik.Activity.AlertDialogClass;
import com.rebersincar.kampusetkinlik.Activity.LoginActivity;
import com.rebersincar.kampusetkinlik.Activity.MainActivity;
import com.rebersincar.kampusetkinlik.Adapters.MyActivityAdapter;
import com.rebersincar.kampusetkinlik.Models.MyActivitysPojo;
import com.rebersincar.kampusetkinlik.R;
import com.rebersincar.kampusetkinlik.RestApi.ManagerAll;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPostsActivity extends AppCompatActivity {
    ListView listView;
    MyActivityAdapter myActivityAdapter;
    List<MyActivitysPojo> listPojo;
    AlertDialogClass alertDialogClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_posts);
        listView = findViewById(R.id.myposts);
        listMyActivitys();
        editPost();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listMyActivitys();

    }

    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit");
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    public void listMyActivitys()
    {
        listPojo = new ArrayList<>();
        final int user_id = LoginActivity.sharedPreferences.getInt("uye_id",0);

        Call<List<MyActivitysPojo>> request= ManagerAll.getInstance().myActivitys(user_id);
        request.enqueue(new Callback<List<MyActivitysPojo>>() {
            @Override
            public void onResponse(Call<List<MyActivitysPojo>> call, Response<List<MyActivitysPojo>> response) {
                if (response.isSuccessful())
                {
                    listPojo = response.body();

                    myActivityAdapter = new MyActivityAdapter(listPojo,getApplicationContext(),MyPostsActivity.this);
                    listView.setAdapter(myActivityAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<MyActivitysPojo>> call, Throwable t) {
                Toast.makeText(MyPostsActivity.this, "Bir hata oluştu.", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void editPost()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int etkinikid,userid,kategori;
                String head,main,date,time,image;

                if (listPojo.get(position).isTf())
                {
                    etkinikid = listPojo.get(position).getId();
                    userid = listPojo.get(position).getUserid();
                    head = listPojo.get(position).getHead();
                    main = listPojo.get(position).getMain();
                    date = listPojo.get(position).getDate();
                    time = listPojo.get(position).getTime();
                    image = listPojo.get(position).getImage();
                    kategori = listPojo.get(position).getKategori();
                    Log.i("deneme", "MyPostsKategori " + kategori);
                    alertDialogClass = new AlertDialogClass(getApplicationContext(), MyPostsActivity.this, etkinikid, userid, head, main, date, time, image, kategori);
                }
                else
                {
                    listView.setClickable(false);
                }


            }
        });
    }
}
