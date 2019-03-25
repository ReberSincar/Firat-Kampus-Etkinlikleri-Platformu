package com.rebersincar.kampusetkinlik.Activity.ForgetPassword;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rebersincar.kampusetkinlik.Activity.LoginActivity;
import com.rebersincar.kampusetkinlik.Models.GuvenlikPasswordPojo;
import com.rebersincar.kampusetkinlik.R;
import com.rebersincar.kampusetkinlik.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Password extends AppCompatActivity {

    EditText sifre1,sifre2;
    Button degistir;
    String mail;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        sifre1 = findViewById(R.id.sifreDegistir1);
        sifre2 = findViewById(R.id.sifreDegistir2);

        degistir = findViewById(R.id.forgetPassSifreBtn);

        Bundle extras = getIntent().getExtras();
        mail = extras.getString("mail");

        Degistir();
    }

    public void Degistir()
    {
        degistir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userSifre1 = sifre1.getText().toString();
                String userSifre2 = sifre2.getText().toString();

                if (userSifre1.length()==0 || userSifre2.length()==0)
                {
                    Toast.makeText(Password.this, "Lütfen Tüm Alanları Doldurun", Toast.LENGTH_SHORT).show();
                }
                else if(userSifre1.length()<6 || userSifre2.length()<6)
                {
                    Toast.makeText(Password.this, "Şifreniz 6 Karakterden Küçük Olamaz", Toast.LENGTH_SHORT).show();
                }
                else if(!(userSifre1.equals(userSifre2)))
                {
                    Toast.makeText(Password.this, "Girdğiniz Şifreler Uyuşmuyor", Toast.LENGTH_SHORT).show();
                }
                else if (userSifre1.equals(userSifre2))
                {
                    pd = new ProgressDialog(Password.this);
                    pd.setMessage("Şifreniz Değiştiriliyor.Lütfen Bekleyiniz..");
                    pd.show();
                    Call<GuvenlikPasswordPojo> requestPassword = ManagerAll.getInstance().GuvenlikPassword(mail,userSifre1);
                    requestPassword.enqueue(new Callback<GuvenlikPasswordPojo>() {
                        @Override
                        public void onResponse(Call<GuvenlikPasswordPojo> call, Response<GuvenlikPasswordPojo> response) {
                            if (response.body().isTf())
                            {
                                Toast.makeText(Password.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                pd.cancel();
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(Password.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                                pd.cancel();
                            }
                        }

                        @Override
                        public void onFailure(Call<GuvenlikPasswordPojo> call, Throwable t) {
                            Toast.makeText(Password.this, "Fail", Toast.LENGTH_SHORT).show();
                            pd.cancel();
                        }
                    });
                }
            }
        });
    }
}
