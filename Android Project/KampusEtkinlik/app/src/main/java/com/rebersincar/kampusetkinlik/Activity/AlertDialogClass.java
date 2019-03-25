package com.rebersincar.kampusetkinlik.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.rebersincar.kampusetkinlik.Activity.AdminActivitys.UpdateMyPost;
import com.rebersincar.kampusetkinlik.Models.DeletePojo;
import com.rebersincar.kampusetkinlik.R;
import com.rebersincar.kampusetkinlik.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlertDialogClass {

    LayoutInflater layoutInflater;
    View view;
    AlertDialog dialog;
    Context context;
    Activity activity;
    ListView list;
    int id,userid,kategori;
    String head,main,date,time,image;

    public AlertDialogClass(Context context,final Activity activity)
    {
        this.context = context;
        this.activity = activity;
    }

    public AlertDialogClass(Context context,final Activity activity,int id,int userid ,String head,String main,String date,String time,String image,int kategori)
    {
        this.context = context;
        this.activity = activity;
        this.id=id;
        this.userid=userid;
        this.head=head;
        this.main=main;
        this.date=date;
        this.image=image;
        this.time=time;
        this.kategori=kategori;
        build();
    }

    public void build() {

        layoutInflater = activity.getLayoutInflater();
        view = layoutInflater.inflate(R.layout.alertlayout, null);
        list = view.findViewById(R.id.myposts);
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setView(view);
        alert.setCancelable(true);
        dialog = alert.create();
        dialog.show();


        ImageButton btnEdit = view.findViewById(R.id.edit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,UpdateMyPost.class);
                intent.putExtra("head",head);
                intent.putExtra("main",main);
                intent.putExtra("date",date);
                intent.putExtra("id",id);
                intent.putExtra("userid",userid);
                intent.putExtra("time",time);
                intent.putExtra("image",image);
                intent.putExtra("kategori",kategori);
                dialog.cancel();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        ImageButton btnDel = view.findViewById(R.id.delete);
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<DeletePojo> request = ManagerAll.getInstance().deletepost(id);
                request.enqueue(new Callback<DeletePojo>() {
                    @Override
                    public void onResponse(Call<DeletePojo> call, Response<DeletePojo> response) {
                        Toast.makeText(activity, "Etkinlik Kaldirildi", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                        Intent intent = new Intent(context,MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<DeletePojo> call, Throwable t) {

                    }
                });
            }
        });
    }
}
