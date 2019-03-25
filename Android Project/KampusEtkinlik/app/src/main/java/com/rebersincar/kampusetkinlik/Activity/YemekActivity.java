package com.rebersincar.kampusetkinlik.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.rebersincar.kampusetkinlik.Models.YemekPojo;
import com.rebersincar.kampusetkinlik.R;
import com.rebersincar.kampusetkinlik.RestApi.ManagerAll;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YemekActivity extends AppCompatActivity {

    ProgressDialog pd;
    TextView yemek1,yemek2,yemek3,yemek4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yemek);

        Define();
        pd = new ProgressDialog(YemekActivity.this);
        pd.setMessage("İşleminiz Gerçekleştiriliyor.Lütfen Bekleyiniz..");
        pd.show();
        YemekCek();
    }

    public void Define()
    {
        yemek1 = findViewById(R.id.yemek1);
        yemek2 = findViewById(R.id.yemek2);
        yemek3 = findViewById(R.id.yemek3);
        yemek4 = findViewById(R.id.yemek4);
    }
    public void YemekCek()
    {
        Call<YemekPojo> requestYemek = ManagerAll.getInstance().Yemek("yemek");
        requestYemek.enqueue(new Callback<YemekPojo>() {
            @Override
            public void onResponse(Call<YemekPojo> call, Response<YemekPojo> response) {
                if (response.isSuccessful())
                {
                    Log.i("yemekler", response.body().toString());
                    yemek1.setText(response.body().getYemek1());
                    yemek2.setText(response.body().getYemek2());
                    yemek3.setText(response.body().getYemek3());
                    yemek4.setText(response.body().getYemek4());
                    pd.cancel();
                }
                else
                {
                    Toast.makeText(YemekActivity.this, "True", Toast.LENGTH_SHORT).show();
                    pd.cancel();
                }

            }

            @Override
            public void onFailure(Call<YemekPojo> call, Throwable t) {
                {
                    Toast.makeText(YemekActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    pd.cancel();
                }

            }
        });
    }
}
