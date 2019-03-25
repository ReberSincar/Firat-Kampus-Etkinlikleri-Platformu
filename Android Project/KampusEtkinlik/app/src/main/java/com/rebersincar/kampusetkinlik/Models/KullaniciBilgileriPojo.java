package com.rebersincar.kampusetkinlik.Models;

public class KullaniciBilgileriPojo{
	private String password;
	private boolean tf;
	private String ad;
	private String mail;
	private String sifre;
	private String soyad;

	public String getPassword(){
		return password;
	}

	public boolean isTf(){
		return tf;
	}

	public String getAd(){
		return ad;
	}

	public String getMail(){
		return mail;
	}

	public String getSifre(){
		return sifre;
	}

	public String getSoyad(){
		return soyad;
	}

	@Override
 	public String toString(){
		return 
			"KullaniciBilgileriPojo{" + 
			"password = '" + password + '\'' + 
			",tf = '" + tf + '\'' + 
			",ad = '" + ad + '\'' + 
			",mail = '" + mail + '\'' + 
			",sifre = '" + sifre + '\'' + 
			",soyad = '" + soyad + '\'' + 
			"}";
		}
}
