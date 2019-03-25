package com.rebersincar.kampusetkinlik.Activity.UserSettings;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rebersincar.kampusetkinlik.Activity.LoginActivity;
import com.rebersincar.kampusetkinlik.Models.GuvenlikChangePojo;
import com.rebersincar.kampusetkinlik.Models.KullaniciBilgileriPojo;
import com.rebersincar.kampusetkinlik.R;
import com.rebersincar.kampusetkinlik.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuvenlikActivity extends AppCompatActivity {

    EditText sifre,cevap;
    Spinner guvenlikSoru;
    Button degistir;
    String gercekParola;
    ProgressDialog pd;

    String[] Sorular={
            "En sevdiğiniz arkadaşınız ismi nedir?",
            "Doğduğunuz şehir neresi ?",
            "İlk aşkınızın ismi nedir?",
            "Annenizin kızlık soyadı nedir?",
            "İlk okul öğretmeninizin ismi nedir?",
            "İlk evcil hayvanınızın ismi nedir?"};

    int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guvenlik);

        sifre = findViewById(R.id.guvenlikChangesifre);
        cevap = findViewById(R.id.guvenlikChangeCevap);
        guvenlikSoru = findViewById(R.id.guvenlikChangeSoru);
        degistir = findViewById(R.id.btnGuvenlikChange);
        userId = LoginActivity.sharedPreferences.getInt("uye_id",0);

        Adapter();
        BilgiAl();
        Degistir();
    }

    public void BilgiAl()
    {
        Call<KullaniciBilgileriPojo> requestBilgi = ManagerAll.getInstance().kullaniciBilgileri(userId);
        requestBilgi.enqueue(new Callback<KullaniciBilgileriPojo>() {
            @Override
            public void onResponse(Call<KullaniciBilgileriPojo> call, Response<KullaniciBilgileriPojo> response) {
                if (response.body().isTf())
                {
                    gercekParola = response.body().getSifre();
                }
            }

            @Override
            public void onFailure(Call<KullaniciBilgileriPojo> call, Throwable t) {
                Toast.makeText(GuvenlikActivity.this, "Fail Bilgi AL", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void Degistir()
    {
        degistir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userCevap = cevap.getText().toString().toLowerCase();
                String userSifre = sifre.getText().toString();
                if (userCevap.length()==0)
                {
                    Toast.makeText(GuvenlikActivity.this, "Cevabı Girmediniz", Toast.LENGTH_SHORT).show();
                }
                else if (userSifre.equals(gercekParola))
                {
                    pd = new ProgressDialog(GuvenlikActivity.this);
                    pd.setMessage("İşlem Gerçekleştiriliyor.Lütfen Bekleyiniz..");
                    pd.show();
                    Call<GuvenlikChangePojo> requestChange = ManagerAll.getInstance().GuvenlikChange(guvenlikSoru.getSelectedItem().toString(),userCevap,userId);
                    requestChange.enqueue(new Callback<GuvenlikChangePojo>() {
                        @Override
                        public void onResponse(Call<GuvenlikChangePojo> call, Response<GuvenlikChangePojo> response) {
                            if (response.body().isTf())
                            {
                                Toast.makeText(GuvenlikActivity.this, "Güvenlik Bilgilerini Güncellendi", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),UsersSettingsActivity.class);
                                pd.cancel();
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<GuvenlikChangePojo> call, Throwable t) {
                            Toast.makeText(GuvenlikActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                            pd.cancel();
                        }
                    });
                }
                else
                    Toast.makeText(GuvenlikActivity.this, "Şifrenizi Yanlış Girdiniz", Toast.LENGTH_SHORT).show();
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
