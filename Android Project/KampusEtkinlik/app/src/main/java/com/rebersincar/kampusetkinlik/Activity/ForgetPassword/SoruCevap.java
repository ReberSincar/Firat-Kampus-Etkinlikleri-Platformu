package com.rebersincar.kampusetkinlik.Activity.ForgetPassword;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rebersincar.kampusetkinlik.Models.GuvenlikPasswordPojo;
import com.rebersincar.kampusetkinlik.R;

import retrofit2.Call;

public class SoruCevap extends AppCompatActivity {

    TextView soruTextViev;
    EditText cevapEditText;
    Button ilerleBtn;
    String soru,cevap,mail;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soru_cevap);

        soruTextViev = findViewById(R.id.guvenlikSoru);
        cevapEditText = findViewById(R.id.guvenlikCevap);
        ilerleBtn = findViewById(R.id.forgetPassSoruBtn);

        Bundle extras = getIntent().getExtras();
        soru = extras.getString("soru");
        cevap = extras.getString("cevap");
        mail = extras.getString("mail");

        soruTextViev.setText(soru);

        Ilerle();
    }

    public void Ilerle()
    {
        ilerleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userCevap = cevapEditText.getText().toString().toLowerCase();
                if (userCevap.length()==0)
                {
                    Toast.makeText(SoruCevap.this, "Cevap Girmediniz", Toast.LENGTH_SHORT).show();
                }
                else if (cevap.equals(userCevap))
                {
                    pd = new ProgressDialog(SoruCevap.this);
                    pd.setMessage("İşleminiz Gerçekleştiriliyor.Lütfen Bekleyiniz..");
                    pd.show();
                    Intent intent = new Intent(getApplicationContext(),Password.class);
                    intent.putExtra("mail",mail);
                    pd.cancel();
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(SoruCevap.this, "Cevabınız Yanlış", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
