package com.rebersincar.kampusetkinlik.Models;

public class MyActivitysPojo{
	private String head;
	private String date;
	private String image;
	private boolean tf;
	private String sonuc;
	private String main;
	private int kategori;
	private int id;
	private String time;
	private int userid;

	public void setHead(String head){
		this.head = head;
	}

	public String getHead(){
		return head;
	}

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setSonuc(String sonuc){
		this.sonuc = sonuc;
	}

	public String getSonuc(){
		return sonuc;
	}

	public void setMain(String main){
		this.main = main;
	}

	public String getMain(){
		return main;
	}

	public int getId(){
		return id;
	}

	public int getKategori(){
		return kategori;
	}

	public void setTime(String time){
		this.time = time;
	}

	public String getTime(){
		return time;
	}


	public int getUserid(){
		return userid;
	}

	@Override
	public String toString(){
		return
				"Response{" +
						"head = '" + head + '\'' +
						",date = '" + date + '\'' +
						",image = '" + image + '\'' +
						",tf = '" + tf + '\'' +
						",sonuc = '" + sonuc + '\'' +
						",main = '" + main + '\'' +
						",id = '" + id + '\'' +
						",kategori = '" + kategori + '\'' +
						",time = '" + time + '\'' +
						",userid = '" + userid + '\'' +
						"}";
	}
}
