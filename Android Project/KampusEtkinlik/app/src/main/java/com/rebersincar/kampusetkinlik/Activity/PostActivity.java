package com.rebersincar.kampusetkinlik.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rebersincar.kampusetkinlik.Models.AddLikePojo;
import com.rebersincar.kampusetkinlik.Models.JoinPojo;
import com.rebersincar.kampusetkinlik.Models.KontroJoin;
import com.rebersincar.kampusetkinlik.Models.KontrolLike;
import com.rebersincar.kampusetkinlik.Models.LikePojo;
import com.rebersincar.kampusetkinlik.R;
import com.rebersincar.kampusetkinlik.RestApi.ManagerAll;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    ImageView img,joinImg,likeImg;
    TextView mainText,headText,dateText,katilimText,likeText,kategoriText,postKatil,postBegen;
    String head,main,image,date,time;
    LinearLayout join,like;
    int id,userid,katilimsayisi,begenme;
    Float puan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        define();
        yukle();
        kontrol();
        katil();
        begen();
    }

    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit");
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
    }

    public void define()
    {
        likeText = findViewById(R.id.likesayi);
        img = findViewById(R.id.postImage);
        mainText = findViewById(R.id.contentpost);
        dateText = findViewById(R.id.postDate);
        headText = findViewById(R.id.postHead);
        katilimText = findViewById(R.id.katilimsayi);
        kategoriText = findViewById(R.id.postKategori);
        join = findViewById(R.id.katil);
        like = findViewById(R.id.begen);
        postKatil = findViewById(R.id.postKatil);
        postBegen = findViewById(R.id.postBegen);
        joinImg = findViewById(R.id.joinPost);
        likeImg = findViewById(R.id.likePost);
    }
    public void yukle()
    {
        Bundle extras = getIntent().getExtras();
        id = extras.getInt("id");
        userid=LoginActivity.sharedPreferences.getInt("uye_id",-1);
        head = extras.getString("head");
        main = extras.getString("main");
        image = extras.getString("image");
        date = extras.getString("date");
        time = extras.getString("time");
        katilimsayisi = extras.getInt("katilim");
        puan = extras.getFloat("puan");
        begenme = extras.getInt("begenme");
        image="http://firatkampus.atwebpages.com/"+image;
        int kategori = extras.getInt("kategori");
        kategoriText.setText(SelectKategoriActivity.Categories[kategori-1]);
        headText.setText(head.toUpperCase());
        dateText.setText(date+"     "+time);
        mainText.setText(main);
        likeText.setText(""+begenme);
        katilimText.setText(""+katilimsayisi);
        Picasso.with(getApplicationContext()).load(image).fit().into(img);
    }

    public void kontrol()
    {
        Call<KontroJoin> requestKontrolJoin = ManagerAll.getInstance().kontroljoin(userid,id);
        requestKontrolJoin.enqueue(new Callback<KontroJoin>() {
            @Override
            public void onResponse(Call<KontroJoin> call, Response<KontroJoin> response) {
                if (response.body().isTf())
                {
                    joinImg.setImageResource(R.drawable.user);
                    postKatil.setTextColor(Color.BLUE);
                }
                else
                {
                    joinImg.setImageResource(R.drawable.unuser);
                    postKatil.setTextColor(Color.GRAY);
                }
            }

            @Override
            public void onFailure(Call<KontroJoin> call, Throwable t) {

            }
        });

        Call<KontrolLike> requestKontrolLike = ManagerAll.getInstance().kontrollike(userid,id);
        requestKontrolLike.enqueue(new Callback<KontrolLike>() {
            @Override
            public void onResponse(Call<KontrolLike> call, Response<KontrolLike> response) {
                if (response.body().isTf())
                {
                    likeImg.setImageResource(R.drawable.like2);
                    postBegen.setTextColor(Color.BLUE);
                }
                else
                {
                    likeImg.setImageResource(R.drawable.unlike);
                    postBegen.setTextColor(Color.GRAY);
                }
            }

            @Override
            public void onFailure(Call<KontrolLike> call, Throwable t) {

            }
        });
    }

    public void katil()
    {
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (postKatil.getCurrentTextColor()==Color.GRAY)
                {
                    String today = bugun();
                    boolean kontrolTarih = KontrolTarih(date,today);
                    if (kontrolTarih==true)
                    {
                        join.setClickable(false);
                        joinImg.setImageResource(R.drawable.user);
                        postKatil.setTextColor(Color.BLUE);
                        Call<JoinPojo> requestJoin = ManagerAll.getInstance().addjoin(userid,id);
                        requestJoin.enqueue(new Callback<JoinPojo>() {
                            @Override
                            public void onResponse(Call<JoinPojo> call, Response<JoinPojo> response) {
                                if (response.body().isTf())
                                {
                                    join.setClickable(true);
                                    katilimsayisi=katilimsayisi+1;
                                    katilimText.setText(""+katilimsayisi);
                                }
                                else
                                    Toast.makeText(PostActivity.this, "Bir Sorun Oluştu", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onFailure(Call<JoinPojo> call, Throwable t) {

                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(PostActivity.this, "Etkinliğin Tarihi Geçmiş Katılım Sağlayamazsınız", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    String today = bugun();
                    boolean kontrolTarih = KontrolTarih(date,today);
                    if (kontrolTarih==true)
                    {
                        join.setClickable(false);
                        joinImg.setImageResource(R.drawable.unuser);
                        postKatil.setTextColor(Color.GRAY);
                        Call<JoinPojo>  requestJoin = ManagerAll.getInstance().JoinVazgec(userid,id);
                        requestJoin.enqueue(new Callback<JoinPojo>() {
                            @Override
                            public void onResponse(Call<JoinPojo> call, Response<JoinPojo> response) {
                                if (response.body().isTf())
                                {
                                    join.setClickable(true);
                                    katilimsayisi=katilimsayisi-1;
                                    katilimText.setText(""+katilimsayisi);

                                }
                                else
                                    Toast.makeText(PostActivity.this, "Bir Sorun Oluştu", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<JoinPojo> call, Throwable t) {

                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(PostActivity.this, "Etkinliğin Tarihi Geçmiş Katılımdan Vazgeçemezsiniz", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    public void begen()
    {
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (postBegen.getCurrentTextColor() == Color.GRAY)
                {
                    like.setClickable(false);
                    likeImg.setImageResource(R.drawable.like2);
                    postBegen.setTextColor(Color.BLUE);
                    Call<AddLikePojo> requestAddLike = ManagerAll.getInstance().AddLike(userid,id);
                    requestAddLike.enqueue(new Callback<AddLikePojo>() {
                        @Override
                        public void onResponse(Call<AddLikePojo> call, Response<AddLikePojo> response) {
                            if (response.body().getTf())
                            {
                                like.setClickable(true);
                                begenme=begenme+1;
                                likeText.setText(""+begenme);
                            }
                            else
                                Toast.makeText(PostActivity.this, "Bu Etkinliği Zaten Beğendiniz", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<AddLikePojo> call, Throwable t) {
                        }
                    });
                }
                else
                {
                    like.setClickable(false);
                    postBegen.setTextColor(Color.GRAY);
                    likeImg.setImageResource(R.drawable.unlike);
                    Call<LikePojo> requestLike = ManagerAll.getInstance().BegenVazgec(userid,id);
                    requestLike.enqueue(new Callback<LikePojo>() {
                        @Override
                        public void onResponse(Call<LikePojo> call, Response<LikePojo> response) {
                            if (response.body().isTf())
                            {
                                like.setClickable(true);
                                begenme=begenme-1;
                                likeText.setText(""+begenme);
                            }
                            else
                                Toast.makeText(PostActivity.this, "Bir Sorun Oluştu", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Call<LikePojo> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }

    public String bugun()
    {
        Date simdikiZaman = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        return df.format(simdikiZaman);

    }

    public boolean KontrolTarih(String etkinlik, String today)
    {
        StringTokenizer tarihEtkinlik = new StringTokenizer(etkinlik, "/");
        StringTokenizer tarihToday = new StringTokenizer(today,"/");


        int dayEtkinlik = Integer.valueOf(tarihEtkinlik.nextToken());
        int monthEtkinlik = Integer.valueOf(tarihEtkinlik.nextToken());
        int yearEtkinlik = Integer.valueOf(tarihEtkinlik.nextToken());

        int dayToday = Integer.valueOf(tarihToday.nextToken());
        int monthToday = Integer.valueOf(tarihToday.nextToken());
        int yearToday = Integer.valueOf(tarihToday.nextToken());

        if (yearEtkinlik>yearToday)
        {
            return true;
        }
        else if (yearEtkinlik == yearToday)
        {
            if (monthEtkinlik>monthToday)
            {
                return true;
            }
            else if (monthEtkinlik == monthToday)
            {
                if (dayEtkinlik>dayToday)
                {
                    return true;
                }
                else if (dayEtkinlik == dayToday)
                {
                    return true;
                }
            }
        }

        return false;
    }
}
