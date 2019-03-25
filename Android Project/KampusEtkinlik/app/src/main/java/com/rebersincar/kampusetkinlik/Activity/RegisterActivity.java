package com.rebersincar.kampusetkinlik.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rebersincar.kampusetkinlik.Models.RegisterPojo;
import com.rebersincar.kampusetkinlik.R;
import com.rebersincar.kampusetkinlik.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    TextView toLogin;
    EditText ETad,ETsoyad,ETmail,ETsifre,ETsifre2,guvenlikCevap;
    Button btnRegister;
    Spinner guvenlikSoru;
    ProgressDialog pd;

    String[] Sorular={
            "En sevdiğiniz arkadaşınız ismi nedir?",
            "Doğduğunuz şehir neresi ?",
            "İlk aşkınızın ismi nedir?",
            "Annenizin kızlık soyadı nedir?",
            "İlk okul öğretmeninizin ismi nedir?",
            "İlk evcil hayvanınızın ismi nedir"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        define();
        login();
        Adapter();
        clickRegister();
    }

    public void define()
    {
        toLogin = findViewById(R.id.toLogin);
        ETad = findViewById(R.id.ad);
        ETsoyad = findViewById(R.id.soyad);
        ETmail = findViewById(R.id.mail);
        ETsifre = findViewById(R.id.sifre);
        ETsifre2 = findViewById(R.id.sifre2);
        guvenlikCevap = findViewById(R.id.registerGuvenlikCevap);
        guvenlikSoru = findViewById(R.id.registerGuvenlikSorusu);
        btnRegister = findViewById(R.id.btnRegister);
    }
    public void login()
    {
        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void clickRegister()
    {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ad = ETad.getText().toString();
                String soyad = ETsoyad.getText().toString();
                String mail = ETmail.getText().toString();
                String sifre = ETsifre.getText().toString();
                String sifre2 = ETsifre2.getText().toString();
                String soru = guvenlikSoru.getSelectedItem().toString();
                String cevap = guvenlikCevap.getText().toString().toLowerCase();

                if(mail.length()==0 || ad.length()==0 || soyad.length()==0 || sifre.length()==0 || sifre2.length()==0)
                    Toast.makeText(RegisterActivity.this, "Tüm alanları doldurun.", Toast.LENGTH_LONG).show();
                else if(!mail.contains("@firat.edu.tr"))
                    Toast.makeText(RegisterActivity.this, "Mail adresiniz mail formatına uymuyor.Mail adresiniz @firat.edu.tr uzantılı olmalıdır ", Toast.LENGTH_LONG).show();
                else if(sifre.length()<6)
                    Toast.makeText(RegisterActivity.this, "Şifreniz en az 6 karakterden oluşmalı.", Toast.LENGTH_LONG).show();
                else if(!sifre2.equals(sifre))
                    Toast.makeText(RegisterActivity.this, "Girdiğiniz şifreler uyuşmuyor", Toast.LENGTH_LONG).show();
                else
                {
                    pd = new ProgressDialog(RegisterActivity.this);
                    pd.setMessage("Hesap Oluşturuluyor.Lütfen Bekleyiniz..");
                    pd.show();
                    Register(ad,soyad,mail,soru,cevap,sifre);
                }

            }
        });
    }
    public void Register(String ad, String soyad, String email, String soru, String cevap, String sifre)
    {
        Call<RegisterPojo> request = ManagerAll.getInstance().register(ad,soyad,email,soru,cevap,sifre);
        request.enqueue(new Callback<RegisterPojo>() {
            @Override
            public void onResponse(Call<RegisterPojo> call, Response<RegisterPojo> response) {
                if (response.body().isTf())
                {
                    Toast.makeText(RegisterActivity.this, response.body().getResult(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    pd.cancel();
                    startActivity(intent);
                }
                else
                    Toast.makeText(RegisterActivity.this, response.body().getResult(), Toast.LENGTH_SHORT).show();
                    pd.cancel();

            }

            @Override
            public void onFailure(Call<RegisterPojo> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                pd.cancel();
            }
        });
    }

    public void Adapter()
    {
        ArrayAdapter<String> guvenlikAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,Sorular);
        guvenlikAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        guvenlikSoru.setAdapter(guvenlikAdapter);

    }
}
