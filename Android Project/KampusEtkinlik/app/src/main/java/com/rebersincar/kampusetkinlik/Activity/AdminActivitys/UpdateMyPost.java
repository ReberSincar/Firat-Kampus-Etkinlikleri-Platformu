package com.rebersincar.kampusetkinlik.Activity.AdminActivitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.rebersincar.kampusetkinlik.Activity.MainActivity;
import com.rebersincar.kampusetkinlik.Activity.SelectKategoriActivity;
import com.rebersincar.kampusetkinlik.Models.UpdatePojo;
import com.rebersincar.kampusetkinlik.R;
import com.rebersincar.kampusetkinlik.RestApi.ManagerAll;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateMyPost extends AppCompatActivity {

    EditText updateHeader,updateContent;
    Button updateShare;
    ImageView updateImage;
    DatePicker updateDate;
    TimePicker updateTime;
    Spinner kategori;
    ProgressDialog pd;
    Bitmap bitmap;
    String head,main,image,date,time,month,day,minute,hour;
    int id,userid,kategoriId,gelenKategori;
    boolean imgKontrol;

    String[] kategoriler = new String[SelectKategoriActivity.Categories.length+1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_my_post);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id");
        userid = bundle.getInt("userid");
        head = bundle.getString("head");
        main = bundle.getString("main");
        image = bundle.getString("image");
        date = bundle.getString("date");
        time = bundle.getString("time");
        gelenKategori = bundle.getInt("kategori");

        Log.i("deneme",""+gelenKategori);



        defineViews();
        kategoriDizi();
        Adapters();
        doldur();
        selectImage();
        update();
        KategoriSec();
    }

    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit");
        Intent intent = new Intent(getApplicationContext(),MyPostsActivity.class);
        startActivity(intent);
    }

    public void kategoriDizi()
    {
        kategoriler[0]="Kategori Seçin";
        for (int i=0; i<SelectKategoriActivity.Categories.length;i++)
        {
            kategoriler[i+1] = SelectKategoriActivity.Categories[i];
        }
    }

    public void defineViews()
    {
        updateHeader = findViewById(R.id.updateHeader);
        updateContent = findViewById(R.id.updateContent);
        updateShare = findViewById(R.id.btnUpdate);
        updateImage = findViewById(R.id.updateImage);
        updateDate = findViewById(R.id.updateDate);
        updateTime = findViewById(R.id.updateTime);
        kategori = findViewById(R.id.updatePostKategori);
    }

    public void doldur()
    {
        StringTokenizer zaman = new StringTokenizer(time, ":");
        String hour = zaman.nextToken();
        String minute = zaman.nextToken();

        StringTokenizer tarih = new StringTokenizer(date, "/");
        String day = tarih.nextToken();
        String month = tarih.nextToken();
        String year = tarih.nextToken();
        updateDate.updateDate(Integer.valueOf(year),Integer.valueOf(month),Integer.valueOf(day));
        updateHeader.setText(head);
        updateContent.setText(main);
        updateTime.setHour(Integer.valueOf(hour));
        updateTime.setMinute(Integer.valueOf(minute));
        kategori.setSelection(gelenKategori);
        String url ="http://firatkampus.atwebpages.com/"+image;
        Picasso.with(getApplicationContext()).load(url).resize(120,90).into(updateImage);
    }

    public void update()
    {
        updateShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                int sendUserid = userid;
                int sendId = id;
                String sendhead = updateHeader.getText().toString();
                String sendmain = updateContent.getText().toString();

                if (updateDate.getMonth()+1<10)
                {
                    month = "0"+(Integer.valueOf(updateDate.getMonth())+1);
                }
                else
                {
                    month = ""+(Integer.valueOf(updateDate.getMonth())+1);
                }

                if (updateDate.getDayOfMonth()<10)
                {
                    day = "0"+Integer.valueOf(updateDate.getDayOfMonth());
                }
                else
                {
                    day = ""+Integer.valueOf(updateDate.getDayOfMonth());
                }

                if (updateTime.getMinute()<10)
                {
                    minute = "0"+(Integer.valueOf(updateTime.getMinute()));
                }
                else
                {
                    minute = ""+(Integer.valueOf(updateTime.getMinute()));
                }

                if (updateTime.getHour()<10)
                {
                    hour = "0"+(Integer.valueOf(updateTime.getHour()));
                }
                else
                {
                    hour = ""+(Integer.valueOf(updateTime.getHour()));
                }

                String senddate = day+"/"+month+"/"+updateDate.getYear();
                String sendtime = hour+":"+minute;

                int kontrol = Kontrol(sendhead,sendmain,senddate,sendtime);
                if (kontrol==1)
                {
                    pd = new ProgressDialog(UpdateMyPost.this);
                    pd.setMessage("İşlem Gerçekleştiriliyor.Lütfen Bekleyiniz..");
                    pd.show();
                    String sendimage = imageToString();
                    Call<UpdatePojo> request = ManagerAll.getInstance().updatepost(sendId,sendUserid,sendhead,sendmain,kategoriId-1,senddate,sendtime,sendimage,image);
                    request.enqueue(new Callback<UpdatePojo>() {
                        @Override
                        public void onResponse(Call<UpdatePojo> call, Response<UpdatePojo> response) {
                            pd.cancel();
                        }

                        @Override
                        public void onFailure(Call<UpdatePojo> call, Throwable t) {
                            Toast.makeText(UpdateMyPost.this, "Etkinlik Güncellendi", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            pd.cancel();
                            startActivity(intent);
                        }
                    });
                }

            }
        });
    }

    public void selectImage()
    {
        updateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent , 47);
            }
        });

    }

    protected void onActivityResult(int requestCode,int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==47 && resultCode==RESULT_OK && data != null)
        {
            Uri path = data.getData();

            try
            {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                updateImage.setImageBitmap(bitmap);
                updateImage.setVisibility(View.VISIBLE);
                imgKontrol=true;
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public String imageToString()
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        String image = Base64.encodeToString(bytes,Base64.DEFAULT);
        return image;
    }

    public void Adapters()
    {
        ArrayAdapter<String> kategoriAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,kategoriler);
        kategoriAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        kategori.setAdapter(kategoriAdapter);

    }

    public void KategoriSec()
    {
        kategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                kategoriId = SelectKategoriActivity.kategoriIdArray[position-1]-1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    public int Kontrol(String head, String main, String date,String time)
    {
        if (head.equals("") || main.equals("") || date.equals("") || time.equals(""))
        {
            Toast.makeText(this, "Lütfen Tüm Alanları Doldurun", Toast.LENGTH_SHORT).show();
            return 0;
        }
        else if (kategori.getSelectedItemPosition()==0)
        {
            Toast.makeText(this, "Lütfen Kategori Seçin", Toast.LENGTH_SHORT).show();
            return 0;
        }
        else if (imgKontrol == false)
        {
            Toast.makeText(this, "Fotoğrafı Değiştirmediniz", Toast.LENGTH_SHORT).show();
            return 0;
        }
        else
        {
            return 1;
        }
    }
}
