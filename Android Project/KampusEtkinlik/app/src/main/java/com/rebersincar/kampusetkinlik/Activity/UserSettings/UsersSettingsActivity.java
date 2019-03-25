package com.rebersincar.kampusetkinlik.Activity.UserSettings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rebersincar.kampusetkinlik.Activity.MainActivity;
import com.rebersincar.kampusetkinlik.R;

public class UsersSettingsActivity extends AppCompatActivity {

    Button parola,bilgi,guvenlik;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_settings);

        parola = findViewById(R.id.userSettingPassBtn);
        bilgi = findViewById(R.id.userSettingsBilgiBtn);
        guvenlik = findViewById(R.id.userSettingsGuvenlikBtn);
        Yonlendir();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    public void Yonlendir()
    {
        parola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ParolaChangeActivity.class);
                startActivity(intent);
            }
        });

        bilgi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BilgiChangeActivity.class);
                startActivity(intent);
            }
        });
        guvenlik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),GuvenlikActivity.class);
                startActivity(intent);
            }
        });
    }
}
