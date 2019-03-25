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

import com.rebersincar.kampusetkinlik.Activity.LoginActivity;
import com.rebersincar.kampusetkinlik.Activity.MainActivity;
import com.rebersincar.kampusetkinlik.Activity.SelectKategoriActivity;
import com.rebersincar.kampusetkinlik.Models.Notification;
import com.rebersincar.kampusetkinlik.Models.PostPojo;
import com.rebersincar.kampusetkinlik.R;
import com.rebersincar.kampusetkinlik.RestApi.ManagerAll;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPostActivity extends AppCompatActivity {

    EditText postHeader,postContent;
    Button postShare;
    ImageView postImage;
    DatePicker postDate;
    TimePicker postTime;
    Spinner kategori;
    Bitmap bitmap;
    ProgressDialog pd;

    int kategoriId;
    boolean imgKontrol=false;
    String month,day,minute,hour;

    String[] kategoriler = new String[SelectKategoriActivity.Categories.length+1];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        defineViews();
        clickShare();
        selectImage();
        kategoriDizi();
        Adapters();
        KategoriSec();
    }

    public void kategoriDizi()
    {
        kategoriler[0]="Kategori Seçin";
        for (int i=0; i<SelectKategoriActivity.Categories.length;i++)
        {
            kategoriler[i+1] = SelectKategoriActivity.Categories[i];
        }

    }

    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit");
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
    public void clickShare()
    {
        postShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd = new ProgressDialog(AddPostActivity.this);
                pd.setMessage("İşlem Gerçekleştiriliyor.Lütfen Bekleyiniz..");
                pd.show();
                sharePost();
            }
        });
    }
    public void sharePost() {
        int user_id = LoginActivity.sharedPreferences.getInt("uye_id",-1);
        final String head = postHeader.getText().toString();
        String main = postContent.getText().toString();
        if (postDate.getMonth()+1<10)
        {
            month = "0"+(Integer.valueOf(postDate.getMonth())+1);
        }
        else
        {
            month = ""+(Integer.valueOf(postDate.getMonth())+1);
        }

        if (postDate.getDayOfMonth()<10)
        {
            day = "0"+Integer.valueOf(postDate.getDayOfMonth());
        }
        else
        {
            day = ""+Integer.valueOf(postDate.getDayOfMonth());
        }

        if (postTime.getMinute()<10)
        {
            minute = "0"+(Integer.valueOf(postTime.getMinute()));
        }
        else
        {
            minute = ""+(Integer.valueOf(postTime.getMinute()));
        }

        if (postTime.getHour()<10)
        {
            hour = "0"+(Integer.valueOf(postTime.getHour()));
        }
        else
        {
            hour = ""+(Integer.valueOf(postTime.getHour()));
        }

        String date = day+"/"+month+"/"+postDate.getYear();
        String time = hour+":"+minute;
        int kontrol = Kontrol(head,main,date,time);

        if (kontrol == 1)
        {
            String image = imageToString();
            final Call<PostPojo> request = ManagerAll.getInstance().addPost(user_id,head,main,kategoriId-1,image,date,time);
            request.enqueue(new Callback<PostPojo>() {
                @Override
                public void onResponse(Call<PostPojo> call, Response<PostPojo> response) {
                    if (response.isSuccessful())
                    {
                        if(response.body().isTf())
                        {
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            Toast.makeText(AddPostActivity.this, "Etkinliğiniz Yayınlanmıştır.", Toast.LENGTH_LONG).show();
                            Bildirim();
                            pd.cancel();
                            startActivity(intent);
                        }
                    }

                }

                @Override
                public void onFailure(Call<PostPojo> call, Throwable t) {
                    Toast.makeText(AddPostActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    pd.cancel();
                }
            });
        }
        else
            pd.cancel();
    }

    public void defineViews()
    {
        postHeader = findViewById(R.id.postHeader);
        postContent = findViewById(R.id.postContent);
        postShare = findViewById(R.id.btnShare);
        postImage = findViewById(R.id.postImage);
        postDate = findViewById(R.id.contentDate);
        postTime = findViewById(R.id.contentTime);
        kategori = findViewById(R.id.addPostKategori);
    }
    public void selectImage()
    {
       postImage.setOnClickListener(new View.OnClickListener() {
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
                postImage.setImageBitmap(bitmap);
                postImage.setVisibility(View.VISIBLE);
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
                kategoriId = position+1;
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
            Toast.makeText(this, "Lütfen Fotoğraf Seçin", Toast.LENGTH_SHORT).show();
            return 0;
        }
        else
        {
            return 1;
        }
    }
    public void Bildirim()
    {
        Call<Notification> requestNotification = ManagerAll.getInstance().Bildirim(postHeader.getText().toString().toUpperCase());
        requestNotification.enqueue(new Callback<Notification>() {
            @Override
            public void onResponse(Call<Notification> call, Response<Notification> response) {
                if (response.isSuccessful())
                {
                    Toast.makeText(AddPostActivity.this, "Bildirim", Toast.LENGTH_SHORT).show();
                    Log.d("bildirimm","girdi");
                }
                else
                {
                    Log.d("bildirimm","response false");
                }

            }

            @Override
            public void onFailure(Call<Notification> call, Throwable t) {
            }
        });
    }
}
