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
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestApi {
    @FormUrlEncoded
    @POST("/login.php")
    Call<LoginPojo> control(@Field("mail") String mail,
                            @Field("sifre") String sifre);

    @FormUrlEncoded
    @POST("/register.php")
    Call<RegisterPojo> register(@Field("ad") String ad,
                                @Field("soyad") String soyad,
                                @Field("email") String email,
                                @Field("soru") String soru,
                                @Field("cevap") String cevap,
                                @Field("sifre") String sifre);

    @FormUrlEncoded
    @POST("/addpost.php")
    Call<PostPojo> addPost(@Field("user_id") int user_id,
                            @Field("head") String head,
                            @Field("main") String main,
                            @Field("kategori") int kategori,
                            @Field("image") String image,
                           @Field("date") String date,
                           @Field("time") String time);

    @GET("/myposts.php")
    Call<List<MyActivitysPojo>> myActivitys(@Query("userid") int user_id);


    @GET("/deletepost.php")
    Call<DeletePojo> deletepost(@Query("id") int id);

    @FormUrlEncoded
    @POST("/updatepost.php")
    Call<UpdatePojo> updatepost(@Field("id") int id,
                                @Field("user_id") int userid,
                                @Field("head") String head,
                                @Field("main") String main,
                                @Field("kategori") int kategori,
                                @Field("date") String date,
                                @Field("time") String time,
                                @Field("image") String image,
                                @Field("oldimage") String oldimage);
    @FormUrlEncoded
    @POST("/join.php")
    Call<JoinPojo> addjoin(@Field("user_id") int user_id,
                           @Field("etkinlik_id") int etkinlik_id);

    @GET("/kontroljoin.php")
    Call<KontroJoin> kontroljoin(@Query("userid") int user_id,
                                 @Query("etkinlikid") int etkinlik_id);

    /*@FormUrlEncoded
    @POST("/likes.php")
    Call<LikePojo> addlike(@Field("user_id") int user_id,
                           @Field("etkinlik_id") int etkinlik_id);*/

    @GET("/kontrollikes.php")
    Call<KontrolLike> kontrollike(@Query("userid") int user_id,
                                  @Query("etkinlikid") int etkinlik_id);

    @GET("/kategori.php")
    Call<List<KategoriPojo>> kategorisec(@Query("kategoriid") int kategori_id);

    @GET("/posts.php")
    Call<List<PostsPojo>> posts();

    @FormUrlEncoded
    @POST("/bilgi.php")
    Call<KullaniciBilgileriPojo> kullaniciBilgileri(@Field("userid") int userid);

    @FormUrlEncoded
    @POST("/passwordchange.php")
    Call<PasswordPojo> sifredegistir (@Field("userid") int userId,
                                      @Field("password") String password);

    @FormUrlEncoded
    @POST("/bilgichange.php")
    Call<BilgiChangePojo> bilgidegistir (@Field("userid") int userId,
                                         @Field("ad") String ad,
                                         @Field("soyad") String soyad,
                                         @Field("mail") String mail);

    @FormUrlEncoded
    @POST("/today.php")
    Call<List<TodayPojo>> today(@Field("today") String tarih);

    @FormUrlEncoded
    @POST("/month.php")
    Call<List<TodayPojo>> month(@Field("month") String tarih);

    @FormUrlEncoded
    @POST("/week.php")
    Call<List<WeekPojo>> week(@Field("week") String tarih);

    @FormUrlEncoded
    @POST("/basvuru.php")
    Call<BasvuruPojo> basvuru(@Field("userid") int userid);

    @FormUrlEncoded
    @POST("/guvenlik.php")
    Call<GuvenlikPojo> guvenlik(@Field("mail") String mail);

    @FormUrlEncoded
    @POST("/guvenlik_password.php")
    Call<GuvenlikPasswordPojo> guvenlikpassword(@Field("mail") String mail,@Field("sifre") String sifre);

    @FormUrlEncoded
    @POST("/guvenlikchange.php")
    Call<GuvenlikChangePojo> guvenlikchange(@Field("soru") String soru, @Field("cevap") String cevap,@Field("userid") int userid);

    @FormUrlEncoded
    @POST("/katildiklarim.php")
    Call<List<KatildigimPojo>> katildigim(@Field("userid") int userid);

    @FormUrlEncoded
    @POST("/begenvazgec.php")
    Call<LikePojo> begenvazgec(@Field("userid") int user_id,
                                 @Field("etkinlikid") int etkinlik_id);

    @FormUrlEncoded
    @POST("/joinvazgec.php")
    Call<JoinPojo> joinvazgec(@Field("userid") int user_id,
                               @Field("etkinlikid") int etkinlik_id);

    @GET("/addlike.php")
    Call<AddLikePojo> AddLike(@Query("userid") int user_id,
                                  @Query("etkinlikid") int etkinlik_id);

    @FormUrlEncoded
    @POST("/bildirim2.php")
    Call<Notification> bildirim(@Field("baslik") String head);

    @FormUrlEncoded
    @POST("/yemek.php")
    Call<YemekPojo> yemek(@Field("yemek") String yemek);
}