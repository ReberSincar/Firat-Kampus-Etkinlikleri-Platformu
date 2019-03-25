package com.rebersincar.kampusetkinlik.Activity.UserSettings;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rebersincar.kampusetkinlik.Activity.LoginActivity;
import com.rebersincar.kampusetkinlik.Models.KullaniciBilgileriPojo;
import com.rebersincar.kampusetkinlik.Models.PasswordPojo;
import com.rebersincar.kampusetkinlik.R;
import com.rebersincar.kampusetkinlik.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParolaChangeActivity extends AppCompatActivity {

    EditText newPass,newPass2,oldPass;
    Button degistirBtn;
    ProgressDialog pd;
    int userId;
    String userPassword,newPassword,newPassword2,oldPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parola_change);

        newPass = findViewById(R.id.newPassword);
        newPass2 = findViewById(R.id.newPassword2);
        oldPass = findViewById(R.id.oldPassword);
        degistirBtn = findViewById(R.id.passwordBtn);

        userId = LoginActivity.sharedPreferences.getInt("uye_id",0);

        Click();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),UsersSettingsActivity.class);
        startActivity(intent);
    }

    public void Click()
    {
        degistirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPassword = oldPass.getText().toString();
                newPassword = newPass.getText().toString();
                newPassword2 = newPass2.getText().toString();

                if(oldPassword.equals("") || newPassword.equals("") || newPassword2.equals(""))
                {
                    Toast.makeText(ParolaChangeActivity.this, "Tüm Alanları Doldurun", Toast.LENGTH_SHORT).show();
                }
                else if(oldPassword.length()<6 && newPassword.length()<6 && newPassword2.length()<6)
                {
                    Toast.makeText(ParolaChangeActivity.this, "Şifreleriniz 6 Karakterden Daha Az Olamaz", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Call<KullaniciBilgileriPojo> requestKontrol = ManagerAll.getInstance().kullaniciBilgileri(userId);
                    requestKontrol.enqueue(new Callback<KullaniciBilgileriPojo>() {
                        @Override
                        public void onResponse(Call<KullaniciBilgileriPojo> call, Response<KullaniciBilgileriPojo> response) {

                            if (response.body().isTf())
                            {
                                userPassword = response.body().getPassword();

                                if (userPassword.equals(oldPassword))
                                {
                                    if(newPassword.equals(newPassword2))
                                    {
                                        Degistir(newPassword);
                                    }
                                    else
                                    {
                                        Toast.makeText(ParolaChangeActivity.this, "Yeni Şifreleriniz Uyuşmuyor", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else
                                {
                                    Toast.makeText(ParolaChangeActivity.this, "Geçerli Şifreniz Yalnış", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<KullaniciBilgileriPojo> call, Throwable t) {
                            Toast.makeText(ParolaChangeActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }


    public void Degistir(String password)
    {
        pd = new ProgressDialog(ParolaChangeActivity.this);
        pd.setMessage("İşlem Gerçekleştiriliyor.Lütfen Bekleyiniz..");
        pd.show();

        Call<PasswordPojo> requestPw = ManagerAll.getInstance().sifredegistir(userId,password);
        requestPw.enqueue(new Callback<PasswordPojo>() {
            @Override
            public void onResponse(Call<PasswordPojo> call, Response<PasswordPojo> response) {

                if (response.body().isTf())
                {
                    Toast.makeText(ParolaChangeActivity.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),UsersSettingsActivity.class);
                    pd.cancel();
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(ParolaChangeActivity.this, "Bir Hata Oluştu", Toast.LENGTH_SHORT).show();
                    pd.cancel();
                }



            }

            @Override
            public void onFailure(Call<PasswordPojo> call, Throwable t) {
                Toast.makeText(ParolaChangeActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                pd.cancel();
            }
        });
    }
}
