package com.rebersincar.kampusetkinlik.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.onesignal.OneSignal;
import com.rebersincar.kampusetkinlik.Activity.AdminActivitys.AddPostActivity;
import com.rebersincar.kampusetkinlik.Activity.AdminActivitys.MyPostsActivity;
import com.rebersincar.kampusetkinlik.Activity.UserSettings.UsersSettingsActivity;
import com.rebersincar.kampusetkinlik.Adapters.PostsAdapter;
import com.rebersincar.kampusetkinlik.Models.BasvuruPojo;
import com.rebersincar.kampusetkinlik.Models.PostsPojo;
import com.rebersincar.kampusetkinlik.R;
import com.rebersincar.kampusetkinlik.RestApi.ManagerAll;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView navHeaderMail;
    String navMail;
    SharedPreferences.Editor editor;
    ListView listView;
    PostsAdapter postsAdapter;
    List<PostsPojo> listPojo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        listView = findViewById(R.id.postList);

        navHeaderMail = findViewById(R.id.navHeaderMail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        LoginActivity.sharedPreferences = getApplicationContext().getSharedPreferences("giris",0);
        navMail = LoginActivity.sharedPreferences.getString("uye_mail",null);
        View headerView = navigationView.getHeaderView(0);
        navHeaderMail = headerView.findViewById(R.id.navHeaderMail);
        navHeaderMail.setText(navMail);

        listActivitys();
        post();


    }

    @Override
    protected void onResume() {
        super.onResume();
        listActivitys();

    }
    public void post()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listPojo.get(position).getHead()==null)
                {
                    listView.setClickable(false);
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(),PostActivity.class);
                    intent.putExtra("head",listPojo.get(position).getHead());
                    intent.putExtra("date",listPojo.get(position).getDate());
                    intent.putExtra("id",listPojo.get(position).getId());
                    intent.putExtra("image",listPojo.get(position).getImage());
                    intent.putExtra("main",listPojo.get(position).getMain());
                    intent.putExtra("time",listPojo.get(position).getTime());
                    intent.putExtra("katilim",listPojo.get(position).getKatilim());
                    intent.putExtra("kategori",listPojo.get(position).getKategori());
                    intent.putExtra("puan",listPojo.get(position).getPuan());
                    intent.putExtra("begenme",listPojo.get(position).getBegenme());
                    startActivity(intent);
                }

            }
        });
    }

    public void listActivitys()
    {
        listPojo = new ArrayList<>();
        Call<List<PostsPojo>> request = ManagerAll.getInstance().posts();
        request.enqueue(new Callback<List<PostsPojo>>() {
            @Override
            public void onResponse(Call<List<PostsPojo>> call, Response<List<PostsPojo>> response) {
                if (response.isSuccessful())
                {
                    listPojo = response.body();
                    postsAdapter = new PostsAdapter(listPojo,getApplicationContext());
                    listView.setAdapter(postsAdapter);
                }

            }

            @Override
            public void onFailure(Call<List<PostsPojo>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Çıkmak istediğinize emin misiniz?")
                .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }).setNegativeButton("Hayır", null).show();
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        String admin = LoginActivity.sharedPreferences.getString("uye_admin","0");
        boolean durum=false;
        if (admin.equals("1"))
        {
            durum = true;
        }

        return durum;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_addpost) {
                Intent intent = new Intent(getApplicationContext(),AddPostActivity.class);
                startActivity(intent);
                return true;
        }
        else if(id== R.id.action_myposts)
        {
                Intent intent = new Intent(getApplicationContext(),MyPostsActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.kategoriler) {
            Intent intent = new Intent(getApplicationContext(),SelectKategoriActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.today)
        {
            Intent intent = new Intent(getApplicationContext(),TodayActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.thisWeek)
        {
            Intent intent = new Intent(getApplicationContext(),WeekActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.thisMonth)
        {
            Intent intent = new Intent(getApplicationContext(),MonthActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.katildiklarim)
        {
            Intent intent = new Intent(getApplicationContext(),KatildigimActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.adminBasvuru)
        {
            int userid = LoginActivity.sharedPreferences.getInt("uye_id",0);
            Call<BasvuruPojo> requestBasvuru = ManagerAll.getInstance().Basvuru(userid);
            requestBasvuru.enqueue(new Callback<BasvuruPojo>() {
                @Override
                public void onResponse(Call<BasvuruPojo> call, Response<BasvuruPojo> response) {
                    if (response.body().isTf())
                    {
                        Toast.makeText(MainActivity.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<BasvuruPojo> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if (id == R.id.userSettings)
        {
            Intent intent = new Intent(getApplicationContext(),UsersSettingsActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.logOut)
        {

            editor = LoginActivity.sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.yemekhane)
        {
            Intent intent = new Intent(getApplicationContext(),YemekActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.obs)
        {

            /*Uri webpage = Uri.parse("https://obs.firat.edu.tr");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);*/
            Intent webIntent = new Intent(getApplicationContext(),WebViewActivity.class);
            webIntent.putExtra("link","https://obs.firat.edu.tr");
            webIntent.putExtra("head","Öğrenci Bilgi Sistemi");
            startActivity(webIntent);
        }
        else if (id == R.id.firat)
        {
            /*Uri webpage = Uri.parse("http://www.firat.edu.tr/tr");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);*/
            Intent webIntent = new Intent(getApplicationContext(),WebViewActivity.class);
            webIntent.putExtra("link","http://www.firat.edu.tr/tr");
            webIntent.putExtra("head","Fırat Üniversitesi AnaSayfa");
            startActivity(webIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
