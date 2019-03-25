package com.rebersincar.kampusetkinlik.Models;

public class GuvenlikPojo{
	private String cevap;
	private String sonuc;
	private String soru;
	private boolean tf;

	public String getCevap(){
		return cevap;
	}

	public String getSonuc(){
		return sonuc;
	}

	public String getSoru(){
		return soru;
	}

	public boolean isTf() {
		return tf;
	}

	@Override
 	public String toString(){
		return 
			"GuvenlikPojo{" + 
			"cevap = '" + cevap + '\'' + 
			",sonuc = '" + sonuc + '\'' + 
			",soru = '" + soru + '\'' + 
			"}";
		}
}
