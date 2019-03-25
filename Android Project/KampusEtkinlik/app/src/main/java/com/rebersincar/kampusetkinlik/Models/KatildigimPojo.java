package com.rebersincar.kampusetkinlik.Models;

public class KatildigimPojo{
	private String head;
	private String date;
	private String image;
	private String katilim;
	private boolean tf;
	private String sonuc;
	private String main;
	private int kategori;
	private String id;
	private String time;
	private String userid;
	private String puan;
	private String begenme;

	public String getHead(){
		return head;
	}

	public String getDate(){
		return date;
	}

	public String getImage(){
		return image;
	}

	public String getKatilim(){
		return katilim;
	}

	public boolean isTf(){
		return tf;
	}

	public String getSonuc(){
		return sonuc;
	}

	public String getMain(){
		return main;
	}

	public int getKategori(){
		return kategori;
	}

	public String getId(){
		return id;
	}

	public String getTime(){
		return time;
	}

	public String getUserid(){
		return userid;
	}

	public String getBegenme() {
		return begenme;
	}

	public String getPuan()
	{
		return puan;
	}

	@Override
 	public String toString(){
		return 
			"KatildigimPojo{" + 
			"head = '" + head + '\'' + 
			",date = '" + date + '\'' + 
			",image = '" + image + '\'' + 
			",katilim = '" + katilim + '\'' + 
			",tf = '" + tf + '\'' + 
			",sonuc = '" + sonuc + '\'' + 
			",main = '" + main + '\'' + 
			",kategori = '" + kategori + '\'' + 
			",id = '" + id + '\'' + 
			",time = '" + time + '\'' + 
			",userid = '" + userid + '\'' + 
			"}";
		}
}
