package com.rebersincar.kampusetkinlik.Models;

public class GuvenlikChangePojo{
	private boolean tf;
	private String sonuc;

	public boolean isTf(){
		return tf;
	}

	public String getSonuc(){
		return sonuc;
	}

	@Override
 	public String toString(){
		return 
			"GuvenlikChangePojo{" + 
			"tf = '" + tf + '\'' + 
			",sonuc = '" + sonuc + '\'' + 
			"}";
		}
}
