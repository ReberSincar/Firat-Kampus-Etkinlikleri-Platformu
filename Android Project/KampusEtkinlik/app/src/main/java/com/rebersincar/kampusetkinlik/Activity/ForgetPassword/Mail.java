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
import com.rebersincar.kampusetkinlik.Models.GuvenlikPojo;
import com.rebersincar.kampusetkinlik.R;
import com.rebersincar.kampusetkinlik.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Mail extends AppCompatActivity {

    EditText mail;
    Button button;
    ProgressDialog pd;
    String soru,cevap,mailAdres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

        mail = findViewById(R.id.forgetPassMail);
        button = findViewById(R.id.forgetPassMailBtn);

        SoruGetir();
    }

    public void SoruGetir()
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mailAdres = mail.getText().toString();
                if (mailAdres.length()==0)
                {
                    Toast.makeText(Mail.this, "Mail Adresinizi Girmediniz", Toast.LENGTH_SHORT).show();
                }
                else if (!mailAdres.contains("@firat.edu.tr"))
                {
                    Toast.makeText(Mail.this, "Mail Adresiniz @firat.edu.tr Uzantılı Olmalı", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    pd = new ProgressDialog(Mail.this);
                    pd.setMessage("İşleminiz Gerçekleştiriliyor.Lütfen Bekleyiniz..");
                    pd.show();
                    Call<GuvenlikPojo> requestGuvenlik = ManagerAll.getInstance().Guvenlik(mailAdres);
                    requestGuvenlik.enqueue(new Callback<GuvenlikPojo>() {
                        @Override
                        public void onResponse(Call<GuvenlikPojo> call, Response<GuvenlikPojo> response) {
                            if (response.body().isTf())
                            {
                                soru = response.body().getSoru();
                                cevap = response.body().getCevap();

                                Intent intent = new Intent(getApplicationContext(),SoruCevap.class);
                                intent.putExtra("soru",soru);
                                intent.putExtra("cevap",cevap);
                                intent.putExtra("mail",mailAdres);
                                pd.cancel();
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(Mail.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                                pd.cancel();
                            }
                        }

                        @Override
                        public void onFailure(Call<GuvenlikPojo> call, Throwable t) {
                            Toast.makeText(Mail.this, "Fail", Toast.LENGTH_SHORT).show();
                            pd.cancel();
                        }
                    });
                }
            }
        });
    }
}
