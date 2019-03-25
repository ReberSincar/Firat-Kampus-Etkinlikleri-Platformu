package com.rebersincar.kampusetkinlik.RestApi;


import com.rebersincar.kampusetkinlik.Models.AddLikePojo;
import com.rebersincar.kampusetkinlik.Models.BasvuruPojo;
import com.rebersincar.kampusetkinlik.Models.BilgiChangePojo;
import com.rebersincar.kampusetkinlik.Models.DeletePojo;
import com.rebersincar.kampusetkinlik.Models.GuvenlikChangePojo;
import com.rebersincar.kampusetkinlik.Models.GuvenlikPasswordPojo;
import com.rebersincar.kampusetkinlik.Models.GuvenlikPojo;
import com.rebersincar.kampusetkinlik.Models.JoinPojo;
import com.rebersincar.kampusetkinlik.Models.KategoriPojo;
import com.rebersincar.kampusetkinlik.Models.KatildigimPojo;
import com.rebersincar.kampusetkinlik.Models.KontroJoin;
import com.rebersincar.kampusetkinlik.Models.KontrolLike;
import com.rebersincar.kampusetkinlik.Models.KullaniciBilgileriPojo;
import com.rebersincar.kampusetkinlik.Models.LikePojo;
import com.rebersincar.kampusetkinlik.Models.LoginPojo;
import com.rebersincar.kampusetkinlik.Models.MyActivitysPojo;
import com.rebersincar.kampusetkinlik.Models.Notification;
import com.rebersincar.kampusetkinlik.Models.PasswordPojo;
import com.rebersincar.kampusetkinlik.Models.PostPojo;
import com.rebersincar.kampusetkinlik.Models.PostsPojo;
import com.rebersincar.kampusetkinlik.Models.RegisterPojo;
import com.rebersincar.kampusetkinlik.Models.TodayPojo;
import com.rebersincar.kampusetkinlik.Models.UpdatePojo;
import com.rebersincar.kampusetkinlik.Models.WeekPojo;
import com.rebersincar.kampusetkinlik.Models.YemekPojo;

import java.util.List;

import retrofit2.Call;

public class ManagerAll extends BaseManager {
    private static ManagerAll ourInstance = new ManagerAll();
    public static synchronized ManagerAll getInstance()
    {
        return ourInstance;
    }
    public Call<LoginPojo> login (String mail, String sifre)
    {
        Call<LoginPojo> call = getRestApiClient().control(mail,sifre);
        return call;
    }

    public Call<RegisterPojo> register (String ad, String soyad, String email, String soru, String cevap, String sifre)
    {
        Call<RegisterPojo> call = getRestApiClient().register(ad,soyad,email,soru,cevap,sifre);
        return call;
    }

    public Call<PostPojo> addPost (int user_id, String head, String main, int kategori, String image,String date,String time)
    {
        Call<PostPojo> call = getRestApiClient().addPost(user_id,head,main,kategori,image,date,time);
        return call;
    }

    public Call<List<MyActivitysPojo>> myActivitys(int user_id)
    {
        Call<List<MyActivitysPojo>> call = getRestApiClient().myActivitys(user_id);
        return call;
    }

    public Call<DeletePojo> deletepost (int etkinlik_id) {
        Call<DeletePojo> call = getRestApiClient().deletepost(etkinlik_id);
        return call;
    }

    public Call<UpdatePojo> updatepost (int id,int userid,String head,String main,int kategori,String image,String oldimage,String date,String time) {
        Call<UpdatePojo> call = getRestApiClient().updatepost(id,userid,head,main,kategori,image,oldimage,date,time);
        return call;
    }

    public Call<JoinPojo> addjoin (int user_id,int etkinlik_id) {
        Call<JoinPojo> call = getRestApiClient().addjoin(user_id,etkinlik_id);
        return call;
    }

    public Call<KontroJoin> kontroljoin (int user_id, int etkinlik_id) {
        Call<KontroJoin> call = getRestApiClient().kontroljoin(user_id,etkinlik_id);
        return call;
    }

    /*public Call<LikePojo> AddLike (int user_id,int etkinlik_id) {
        Call<LikePojo> call = getRestApiClient().addlike(user_id,etkinlik_id);
        return call;
    }*/


    public Call<KontrolLike> kontrollike (int user_id, int etkinlik_id) {
        Call<KontrolLike> call = getRestApiClient().kontrollike(user_id,etkinlik_id);
        return call;
    }

    public Call<List<KategoriPojo>> KategoriSec(int kategori_id) {
        Call<List<KategoriPojo>> call = getRestApiClient().kategorisec(kategori_id);
        return call;
    }

    public Call<List<PostsPojo>> posts()
    {
        Call<List<PostsPojo>> call = getRestApiClient().posts();
        return call;
    }

    public Call<KullaniciBilgileriPojo> kullaniciBilgileri(int userid)
    {
        Call<KullaniciBilgileriPojo> call =getRestApiClient().kullaniciBilgileri(userid);
        return call;
    }

    public Call<PasswordPojo> sifredegistir (int userId, String password)
    {
        Call<PasswordPojo> call = getRestApiClient().sifredegistir(userId,password);
        return call;
    }

    public Call<BilgiChangePojo> bilgidegistir (int userId, String ad, String soyad, String mail)
    {
        Call<BilgiChangePojo> call = getRestApiClient().bilgidegistir(userId,ad,soyad,mail);
        return call;
    }

    public Call<List<TodayPojo>> Today(String tarih)
    {
        Call<List<TodayPojo>> call = getRestApiClient().today(tarih);
        return call;
    }

    public Call<List<TodayPojo>> Month(String tarih)
    {
        Call<List<TodayPojo>> call = getRestApiClient().month(tarih);
        return call;
    }

    public Call<List<WeekPojo>> Week(String tarih)
    {
        Call<List<WeekPojo>> call = getRestApiClient().week(tarih);
        return call;
    }

    public Call<BasvuruPojo> Basvuru(int userid)
    {
        Call<BasvuruPojo> call = getRestApiClient().basvuru(userid);
        return call;
    }

    public Call<GuvenlikPojo> Guvenlik(String mail)
    {
        Call<GuvenlikPojo> call = getRestApiClient().guvenlik(mail);
        return call;
    }

    public Call<GuvenlikPasswordPojo> GuvenlikPassword(String mail,String sifre)
    {
        Call<GuvenlikPasswordPojo> call = getRestApiClient().guvenlikpassword(mail,sifre);
        return call;
    }

    public Call<GuvenlikChangePojo> GuvenlikChange(String soru, String cevap, int userid)
    {
        Call<GuvenlikChangePojo> call = getRestApiClient().guvenlikchange(soru,cevap,userid);
        return call;
    }

    public Call<List<KatildigimPojo>> Katildigim(int userid)
    {
        Call<List<KatildigimPojo>> call = getRestApiClient().katildigim(userid);
        return call;
    }

    public Call<LikePojo> BegenVazgec (int user_id, int etkinlik_id) {
        Call<LikePojo> call = getRestApiClient().begenvazgec(user_id,etkinlik_id);
        return call;
    }

    public Call<JoinPojo> JoinVazgec (int user_id, int etkinlik_id) {
        Call<JoinPojo> call = getRestApiClient().joinvazgec(user_id,etkinlik_id);
        return call;
    }

    public Call<AddLikePojo> AddLike (int user_id, int etkinlik_id) {
        Call<AddLikePojo> call = getRestApiClient().AddLike(user_id,etkinlik_id);
        return call;
    }

    public Call<Notification> Bildirim (String head)
    {
        Call<Notification> call = getRestApiClientNotification().bildirim(head);
        return call;
    }

    public Call<YemekPojo> Yemek (String yemek)
    {
        Call<YemekPojo> call = getRestApiClientNotification().yemek(yemek);
        return call;
    }

}
